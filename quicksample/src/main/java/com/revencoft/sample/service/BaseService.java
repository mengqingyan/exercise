/**
 * 
 */
package com.revencoft.sample.service;

import java.io.Serializable;
import java.util.List;

import com.revencoft.sample.support.CustomQueryParams;

/**
 * @author mengqingyan
 * @version 
 */
public interface BaseService<T> {

	/**
	 * 根据params获取实体类
	 * @param params
	 * @return
	 */
	List<T> getEntityByQParams(CustomQueryParams params);
	
	/**
	 * 根据params 获取实体数量
	 * @param params
	 * @return
	 */
	int getEntityCountByQParams(CustomQueryParams params);
	
	
	void saveEntity(T entity);
	
	void deleteEntityById(Serializable id);
	
	void deleteEntityByQParams(CustomQueryParams params);
}
