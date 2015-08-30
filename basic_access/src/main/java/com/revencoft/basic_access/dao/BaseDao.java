/**
 * 
 */
package com.revencoft.basic_access.dao;

import java.io.Serializable;
import java.util.List;

import com.revencoft.basic_access.param.CustomQueryParams;


/**
 * @author mengqingyan
 * @version 
 */
public interface BaseDao<T> {

	/**
	 * @param params
	 * @return
	 */
	List<T> queryByQParams(CustomQueryParams params);

	/**
	 * @param params
	 * @return
	 */
	int queryCountByQParams(CustomQueryParams params);
	
	void save(T entity);

	/**
	 * @param id
	 */
	void deleteById(Serializable id);

	/**
	 * @param params
	 */
	void deleteByQParams(CustomQueryParams params);
	
	/**
	 * @param params
	 */
	void updateByQParams(CustomQueryParams params);

}
