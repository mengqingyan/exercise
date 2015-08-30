/**
 * 
 */
package com.revencoft.basic_access.service;

import java.io.Serializable;
import java.util.List;

import com.revencoft.basic_access.param.CustomQueryParams;


/**
 * @author mengqingyan
 * @version 
 */
public interface BaseService<T> {

	/**
	 * 根据params获取实体类;
	 * 若不拼接查询条件，请传入null
	 * @param params
	 * @return
	 */
	List<T> getEntityByQParams(CustomQueryParams params);
	
	/**
	 * 根据params 获取实体数量;
	 * 若不拼接查询条件，请传入null
	 * @param params
	 * @return
	 */
	int getEntityCountByQParams(CustomQueryParams params);
	
	
	void saveEntity(T entity);
	
	void deleteEntityById(Serializable id);
	
	/**
	 * 根据params 删除实体
	 * @param params
	 */
	void deleteEntityByQParams(CustomQueryParams params);
	
	/**
	 * 根据params 删除实体
	 * @param params
	 */
	void updateEntityByQParams(CustomQueryParams params);
}
