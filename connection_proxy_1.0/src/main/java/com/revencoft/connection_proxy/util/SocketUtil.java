package com.revencoft.connection_proxy.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.revencoft.connection_proxy.reader.Reader;
import com.revencoft.connection_proxy.reader.ResponseReader;
import com.revencoft.connection_proxy.request.Request;
import com.revencoft.connection_proxy.response.Response;

/**
 * 
 * @author mengqingyan
 *
 */
public class SocketUtil {
	
	private static final Logger log = Logger.getLogger(SocketUtil.class);

	private static final int JOIN_POINT_SIZE = 4;
	
	private static int BUFFER_SIZE = 4*1024;
	
	public static void copy(InputStream in, OutputStream out, int contentLength) throws IOException {
		if(contentLength == 0) {
			return;
		}
		int bufferSize = BUFFER_SIZE;
		if(contentLength != -1) {
			bufferSize = contentLength;
		}
		byte[] buffer = new byte[bufferSize];
		int total = 0, hasRead = 0;
		int tryCount = 3;
		
		while(true) {
			try {
				hasRead = in.read(buffer);
				if(hasRead > 0) {
					out.write(buffer, 0, hasRead);
					log.debug(bytes2String(buffer, 0, hasRead));
					
					if(contentLength != -1) {
						total += hasRead;
						if(total == contentLength) {
							break;
						}
					}
				} else if(hasRead < 0){
					break;
				}
			} catch (IOException e) {
				tryCount -- ;
				if(tryCount == 0) {
					log.debug("try over but failed!" + e);
					throw e;
				}
			}
		}
		
	}
	
	public static void copyChunked(ResponseReader responseReader, OutputStream cOut) throws IOException {
		
		while(true) {
			String contentLength = responseReader.readLine();
			cOut.write(contentLength.getBytes());
			int length = ResponseReader.parseStrInt(contentLength, 16);
			if(length == 0) {
				cOut.write(ResponseReader.RET_NEXT_LINE.getBytes());
				break;
			}
			copy(responseReader.getIn(), cOut, length +2);
		}
	}
	
	public static void copyClientReq(InputStream src, OutputStream dest) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		byte[] joinPoint = new byte[JOIN_POINT_SIZE];
		int hasRead = 0;
		int tryCount = 3;
		while(true) {
			try {
				hasRead = src.read(buffer);
				if(hasRead > 0) {
					dest.write(buffer, 0, hasRead);
					log.debug(bytes2String(buffer, 0, hasRead));
					if(hasRead >= JOIN_POINT_SIZE) {
						appendJoinPointByte(joinPoint, buffer, hasRead - JOIN_POINT_SIZE, JOIN_POINT_SIZE);
					} else {
						appendJoinPointByte(joinPoint, buffer, 0, hasRead);
					}
					if(checkEnd(joinPoint)) {
						break;
					}
					
				} else if(hasRead < 0){
					break;
				}
			} catch (IOException e) {
				tryCount -- ;
				if(tryCount == 0) {
					log.error("try over but failed!" + e);
					throw e;
				}
			}
		}
	}
	
	public static void copyServerResponse(InputStream src, OutputStream dest) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int hasRead = 0;
		int tryCount = 3;
		while(true) {
			try {
				hasRead = src.read(buffer);
				if(hasRead > 0) {
					dest.write(buffer, 0, hasRead);
					dest.flush();
					log.debug(bytes2String(buffer, 0, hasRead));
				} else if(hasRead < 0){
					break;
				}
			} catch (IOException e) {
				tryCount -- ;
				if(tryCount == 0) {
					log.error("try over but failed!" + e);
					throw e;
				}
			}
		}
	}
	
	/**
	 * @param joinPoint
	 * @return
	 */
	private static boolean checkEnd(byte[] joinPoint) {
		if (joinPoint[0] == Reader.RETURN
				&& joinPoint[1] == Reader.NEXT_LINE
				&& joinPoint[2] == Reader.RETURN
				&& joinPoint[3] == Reader.NEXT_LINE) {
			return true;
		}
		return false;
	}

	/**
	 * @param joinPoint
	 * @param buffer
	 * @param beginIndex
	 * @param length
	 */
	private static void appendJoinPointByte(byte[] joinPoint, byte[] buffer, int beginIndex, int length) {
		int joinLength = joinPoint.length;
		if(joinLength < length) {
			return;
		}
		
		for(int i = 0; i < joinLength - length; i++) {
			joinPoint[i] = joinPoint[i + length];
		}
		
		for(int i = joinLength - length, j = 0; i < joinLength; i ++, j++) {
			joinPoint[i] = buffer[beginIndex + j];
		}
	}

	private static String bytes2String(byte[] bytes, int offset, int length) {
		return new String(bytes, offset, length);
	}
	
	public static void writeClientResponseLines(OutputStream cOut,
			Map<String, String> responseHead) throws IOException {
		
		
		cOut.write(responseHead.get(ResponseReader.STATUS_LINE).getBytes());
		
		Set<Entry<String, String>> set = responseHead.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		try {
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				if(!ResponseReader.STATUS_LINE.equals(entry.getKey())) {
					cOut.write((entry.getKey() + ": " + entry.getValue()).getBytes());
				}
			}
			cOut.write("\r\n".getBytes());
		} catch (IOException e) {
			log.error("responseHead: \n" + responseHead, e);
		}
		
	}

	public static void writeServerReqLines(OutputStream sOut, List<String> reqLines,
			String wrapedHeadLine) throws IOException {
		sOut.write(wrapedHeadLine.getBytes());
		int size = reqLines.size();
		if(size > 1) {
			for(int i = 1; i < size; i++) {
				sOut.write(reqLines.get(i).getBytes());
			}
		}
	}
	
	public static String wrapHeadLine(String headLine, String host) {
		
		return StringUtils.replace(headLine, "http://" + host, "");
	}

	public static String getMethod(String headLine) {
		return StringUtils.substringBefore(headLine, " ");
	}

	public static int getPort(String headLine) {
		String afterPotocal = StringUtils.substringAfter(headLine, ":");
		if(afterPotocal.indexOf(":") < 0) {
			return 80;
		} else {
			String portStr = StringUtils.substringBetween(afterPotocal, ":", "/");
			int port;
			try {
				port = Integer.parseInt(portStr);
			} catch (NumberFormatException e) {
				return 80;
			}
			return port;
		}
	}

	public static String getHost(String headLine) {
		
		String afterPotocal = StringUtils.substringAfter(headLine, ":");
		
		String hsotPort = StringUtils.substringBetween(afterPotocal, "//", "/");
		if(hsotPort.indexOf(":") < 0) {
			return hsotPort;
		} else {
			return StringUtils.substringBefore(hsotPort, ":");
		}
	}
	
	public static List<String> getReqLines(InputStream cIn) {
		Scanner scanner = new Scanner(cIn);
		List<String> res = new ArrayList<String>();
		try {
			String temp = null;
			while(scanner.hasNextLine()) {
				temp = scanner.nextLine();
				if(StringUtils.isBlank(temp)) {
					break;
				}
				res.add(temp + "\r\n");
			}
			res.add("\r\n");
			return res;
		} finally {
//			scanner.close();
		}
	}
	
	public static void closeSocket(Socket... sockets) {
		if(sockets != null) {
			for (Socket socket : sockets) {
				if(socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}
	
	public static void closeStream(Closeable... closeables) {
		if(closeables != null) {
			for (Closeable closeable : closeables) {
				if(closeable != null) {
					try {
						closeable.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	/**
	 * @param sOut
	 * @param req
	 * @throws IOException 
	 */
	public static void transferClientReq2Server(OutputStream sOut, Request req) throws IOException {
		sOut.write(req.getHeadBytes());
		byte[] datas = req.getBodyBytes();
		if(datas.length > 0) {
			sOut.write(datas);
		}
	}

	/**
	 * @param sOut
	 * @param req
	 * @throws IOException 
	 */
	public static void response2Client(OutputStream cOut, Request req) throws IOException {
		if(Request.CONNECT.equals(req.getMethod())) {
			cOut.write(Response.PROXY_CONNECT_OK.getBytes());
			cOut.write(Response.RET_NEXT_LINE.getBytes());
		}
	}
}
