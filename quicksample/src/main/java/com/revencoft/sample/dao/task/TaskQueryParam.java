/**
 * 
 */
package com.revencoft.sample.dao.task;

public class TaskQueryParam {
	
	private final Long userId;
	private final String search_LIKE_title;
	private final Integer start;
	private final Integer pageSize;
	/**
	 * @param userId
	 * @param search_LIKE_title
	 * @param start
	 * @param pageSize
	 */
	public TaskQueryParam(Long userId, String search_LIKE_title, Integer start,
			Integer pageSize) {
		super();
		this.userId = userId;
		this.search_LIKE_title = search_LIKE_title;
		this.start = start;
		this.pageSize = pageSize;
	}
	public Long getUserId() {
		return userId;
	}
	public String getSearch_LIKE_title() {
		return search_LIKE_title;
	}
	public Integer getStart() {
		return start;
	}
	public Integer getPageSize() {
		return pageSize;
	}
}