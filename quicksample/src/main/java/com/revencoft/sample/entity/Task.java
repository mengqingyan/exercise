/**
 * 
 */
package com.revencoft.sample.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.revencoft.basic_access.entity.IdEntity;

/**
 * @author mengqingyan
 * @version 
 */
public class Task extends IdEntity{

	private String title;
	
	private String description;
	
	private User user;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
