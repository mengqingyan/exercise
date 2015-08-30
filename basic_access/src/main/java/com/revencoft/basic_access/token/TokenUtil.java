/**
 * 
 */
package com.revencoft.basic_access.token;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author mqy
 *
 */
public class TokenUtil {
	
	private static final Random RANDOM = new Random();
	public static final String DEFAULT_TOKEN_NAME = "token";
	public static final String SESSION_TOKEN_PREFIX = "session";
	
	public static String generateGUID() {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }

	public static String setToken(HttpSession session) {
		return setToken(session, DEFAULT_TOKEN_NAME);
	}

	public static String setToken(HttpSession session, String tokenName) {
		String token = generateGUID();
		setSessionToken(session, tokenName, token);
		return token;
	}

	/**
	 * @param session
	 * @param tokenName
	 * @param token
	 *            void
	 */
	private static void setSessionToken(HttpSession session, String tokenName,
			String token) {
		session.setAttribute(buildSessionTokenName(tokenName), token);
	}
	
	public static String buildSessionTokenName(String tokenName) {
		return SESSION_TOKEN_PREFIX + "." + tokenName;
	}

	/**
	 * @param session
	 * @return
	 * boolean
	 */
	public static boolean validToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
			if(session == null) {
				return false;
			}
			String sessionTokenName = buildSessionTokenName(DEFAULT_TOKEN_NAME);
			Object tokenObj = session.getAttribute(sessionTokenName);
			if(tokenObj == null) {
				return false;
			}
			session.removeAttribute(sessionTokenName);
			
			return tokenObj.equals(getToken(request));
	}
	
	public static String getToken(HttpServletRequest request){
		
		Map<?, ?> params = request.getParameterMap();
		if(!params.containsKey(DEFAULT_TOKEN_NAME)) {
			return null;
		}
		String[] tokens = (String[]) params.get(DEFAULT_TOKEN_NAME);
		
		if(tokens == null || tokens.length<1) {
			return null;
		}
		return tokens[0];
	}

}
