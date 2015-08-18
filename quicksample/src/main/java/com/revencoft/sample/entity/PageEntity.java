/**
 * 
 */
package com.revencoft.sample.entity;

import java.util.List;

/**
 * @author mengqingyan
 * @version 
 */
public class PageEntity<T> {

	private final int count;//数据总和
	private final List<T> entities;
	/**
	 * @param count
	 * @param entities
	 */
	public PageEntity(int count, List<T> entities) {
		super();
		this.count = count;
		this.entities = entities;
	}
	
	public int getCount() {
		return count;
	}
	public List<T> getEntities() {
		return entities;
	}
	
	
}
