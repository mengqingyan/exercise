/**
 * 
 */
package com.revencoft.basic_access.token;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @author mqy
 *
 */
public class Token extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2858042014043569600L;
	
	private String name = "token";

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		HttpSession session = this.pageContext.getSession();
		if(session == null) {
			throw new JspException("can't get session.");
		}
		String token = "<input type=\"hidden\" name=\"" + name+ "\" value = '" + TokenUtil.setToken(session) + "'/>";
		try {
			out.println(token);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return super.doStartTag();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
