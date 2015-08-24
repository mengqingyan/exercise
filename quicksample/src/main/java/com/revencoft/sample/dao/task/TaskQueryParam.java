/**
 * 
 */
package com.revencoft.sample.dao.task;

import com.revencoft.sample.support.query.DataTableQueryParams;

public class TaskQueryParam extends DataTableQueryParams{
	
	private final Long userId;
	private final String search_LIKE_title;
	private int start = -1;
	private int pageSize = -1;
	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @param start
	 * @param pageSize
	 */
	public TaskQueryParam(Long userId, String search_LIKE_title) {
		super();
		this.userId = userId;
		this.search_LIKE_title = search_LIKE_title;
		
	}
	public Long getUserId() {
		return userId;
	}
	public String getSearch_LIKE_title() {
		return search_LIKE_title;
	}
	public int getStart() {
		return start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setStart(int start) {
		if(start > -1)
			this.start = start;
	}
	public void setPageSize(int pageSize) {
		if(pageSize > -1)
			this.pageSize = pageSize;
	}
	
	
}