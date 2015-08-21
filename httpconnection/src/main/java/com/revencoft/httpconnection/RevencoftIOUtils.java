/**
 * 
 */
package com.revencoft.httpconnection;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author mengqingyan
 * @version 
 */
public abstract class RevencoftIOUtils {
	
	private static final String DEFAULT_FILE_DOWNLOAD_DIR = System.getProperty("user.dir");
	/**
	 * @param in
	 * @param fileName
	 * @param fileLen 文件长度
	 * @throws IOException 
	 */
	public static void downLoadFile(InputStream in, String fileName, long fileLen) throws IOException {
		
		Path path = Paths.get(DEFAULT_FILE_DOWNLOAD_DIR + fileName);
		FileChannel fileChannel = null;
		try {
			fileChannel = FileChannel.open(path, StandardOpenOption.WRITE,
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			
			ReadableByteChannel readableByteChannel = Channels.newChannel(in);
			ByteBuffer dst = ByteBuffer.allocate(2048);
			dst.clear();
			int hasRead = 0;
			long total = 0;
			fileLen = fileLen / 100;
			while((hasRead = readableByteChannel.read(dst)) != -1 ) {
				dst.flip();
				fileChannel.write(dst);
				dst.clear();
				if(fileLen != 0) {
					total += hasRead;
					System.out.println(String.format("has completed:  %3s %%", total / fileLen));
				}
			}
		} finally {
			if(fileChannel != null) {
				try {
					fileChannel.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * @param closeables
	 */
	public static void closeStream(Closeable... closeables) {
		if(closeables == null) {
			return;
		}
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
