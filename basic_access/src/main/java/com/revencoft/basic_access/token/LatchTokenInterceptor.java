/**
 * 
 */
package com.revencoft.basic_access.token;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.revencoft.basic_access.interceptor.RequestMethodHandlerInterceptor;
import com.revencoft.basic_access.listener.SessionContextRefreshor;


/**
 * 防重复提交
 * @author mqy
 * @version 1.2
 */
public class LatchTokenInterceptor extends RequestMethodHandlerInterceptor implements SessionContextRefreshor{
	
	private static final Logger log = Logger.getLogger(LatchTokenInterceptor.class);
	
	private String invalidTokenUrl = "/error/tokenerror.do";
	
	private static final String SESSION_MODE_VIEW = "sessionMV";
	
	private static final Map<HttpSessionHolder, CountDownLatch> latchCache = new ConcurrentHashMap<HttpSessionHolder, CountDownLatch>();

	private static final String IS_SESSION_INVALILD = "is_session_invalid";


	@Autowired
	private ViewResolver viewResolver;
	
	@Override
	public boolean doPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean isValid = false;
		HttpSession session = request.getSession(true);
		// 以session为锁，
		// 其他线程在此阻塞
		synchronized (session) {
			isValid = TokenUtil.validToken(request);
			if (!isValid) {
				log.debug("token is invalid!");
				//线程同步锁
				CountDownLatch latch = this.getInvalidTokenLatch(
						session, false);
				if (latch != null) {
					latch.await();
					processInvalidToken(request, response);
				} else {
					log.debug("controller(method) will not be executed,render the view directly.");
					//we must render the view again
					processInvalidToken(request, response);
				}
				return false;
			}

			this.bindCountDownLatchWithSession(session);
			return true;
		}

	}


	private void bindCountDownLatchWithSession(HttpSession session) {
		HttpSessionHolder sessionHolder = new HttpSessionHolder(session);
		
		if(session == null || latchCache.get(sessionHolder) != null) {
			return;
		}
		session.setAttribute(IS_SESSION_INVALILD, false);
		latchCache.put(sessionHolder, new CountDownLatch(1));
	}


	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 */
	private void processInvalidToken(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object mvObj = session.getAttribute(SESSION_MODE_VIEW);
			if (mvObj != null) {
				ModelAndView mv = (ModelAndView) mvObj;
				if (viewResolver != null) {
					View view;
					try {
						view = viewResolver.resolveViewName(mv.getViewName(),
								request.getLocale());
						view.render(mv.getModel(), request, response);
					} catch (Exception e) {
						sendRedirect(request, response);
					}
				} else {
					sendRedirect(request, response);
				}
			} else {
				sendRedirect(request, response);
			}
		} else {
			sendRedirect(request, response);
		}
	}
	
	private void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int index = uri.substring(1).indexOf("/") + 1;
		String path = uri.substring(0, index) + getInvalidTokenUrl();
		try {
			response.sendRedirect(path);
		} catch (IOException e1) {
		}
	}

	@Override
	public void doPostHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.setAttribute(SESSION_MODE_VIEW, modelAndView);
		}
		removeTokenLatch(session);
	}

	@Override
	protected void doAfterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null) {
			removeTokenLatch(request.getSession());
			throw ex;
		}
	}


	/**
	 * @param session
	 */
	private void removeTokenLatch(HttpSession session) {
		if(session == null) {
			return;
		}
		HttpSessionHolder sessionHolder = new HttpSessionHolder(session);
		CountDownLatch latch = latchCache.remove(sessionHolder);
		if(latch != null) {
			while(latch.getCount() > 0) {
				latch.countDown();
			}
		}
	}


	private CountDownLatch getInvalidTokenLatch(HttpSession session, boolean isSessionNeedValid) {
		if(session == null) {
			return null;
		}
		HttpSessionHolder sessionHolder = new HttpSessionHolder(session);
		if(!isSessionNeedValid) {
			return latchCache.get(sessionHolder);
		} else {
			if(isSessionInValid(session)) {
				return null;
			} else {
				return latchCache.get(sessionHolder);
			}
		}
	}


	/**
	 * session失效时，释放相应的CountDownLatch
	 */
	public void releaseInvalidSessionWithTokenLatch() {
		Set<Entry<HttpSessionHolder, CountDownLatch>> entrySet = latchCache.entrySet();
		Iterator<Entry<HttpSessionHolder, CountDownLatch>> it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry<HttpSessionHolder, CountDownLatch> entry = it.next();
			HttpSessionHolder sessionHolder = entry.getKey();
			if(isSessionInValid(sessionHolder.getSession())) {
				CountDownLatch tokenLock = entry.getValue();
				while(tokenLock.getCount() > 0) {
					tokenLock.countDown();
				}
				it.remove();
			}
		}
	}


	public String getInvalidTokenUrl() {
		return invalidTokenUrl;
	}

	public void setInvalidTokenUrl(String invalidTokenUrl) {
		this.invalidTokenUrl = invalidTokenUrl;
	}
	
	
	private boolean isSessionInValid(HttpSession session) {
		if(session == null) {
			return true;
		}
		Object isInvalidObj = null;
		try {
			isInvalidObj = session.getAttribute(IS_SESSION_INVALILD);
		} catch (IllegalStateException e) {
		}
		if(isInvalidObj != null) {
			boolean isInvalid = (Boolean) isInvalidObj;
			return isInvalid;
		}
		return true;
	}
	
	
	@Override
	public void refresh(HttpSession session) {
		removeTokenLatch(session);
	}
	
	
	private static final class HttpSessionHolder {
		
		private String sessionId;
		
		private HttpSession session;
		
		private HttpSessionHolder(HttpSession session) {
			this.session = session;
			this.sessionId = session.getId();
		}
		
		

		public HttpSession getSession() {
			return session;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((sessionId == null) ? 0 : sessionId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HttpSessionHolder other = (HttpSessionHolder) obj;
			if (sessionId == null) {
				if (other.sessionId != null)
					return false;
			} else if (!sessionId.equals(other.sessionId))
				return false;
			return true;
		}
		
		
	}




}
