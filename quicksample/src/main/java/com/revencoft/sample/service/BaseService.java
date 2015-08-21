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
	 * 根据params获取实体类;
	 * 若不拼接查询条件，请传入new CustomQueryParams()
	 * @param params
	 * @return
	 */
	List<T> getEntityByQParams(CustomQueryParams params);
	
	/**
	 * 根据params 获取实体数量;
	 * 若不拼接查询条件，请传入new CustomQueryParams()
	 * @param params
	 * @return
	 */
	int getEntityCountByQParams(CustomQueryParams params);
	
	
	void saveEntity(T entity);
	
	void deleteEntityById(Serializable id);
	
	/**
	 * 根据params 删除实体
	 * 若不拼接查询条件，请传入new CustomQueryParams()
	 * @param params
	 */
	void deleteEntityByQParams(CustomQueryParams params);
}
