/**
 * 
 */
package com.revencoft.sample.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.revencoft.sample.dao.BaseDao;
import com.revencoft.sample.support.query.CustomQueryParams;

/**
 * @author mengqingyan
 * @version 
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected final Logger log = Logger.getLogger(getClass());
	
	@Override
	@Transactional(readOnly=true)
	public List<T> getEntityByQParams(CustomQueryParams params) {
		checkQueryParams(params);
		return getBaseDao().queryByQParams(params);
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public int getEntityCountByQParams(CustomQueryParams params) {
		checkQueryParams(params);
		return getBaseDao().queryCountByQParams(params);
	}



	/**
	 * @param params
	 */
	protected void checkQueryParams(CustomQueryParams params) {
		Validate.notNull(params, "The CustomQueryParams must not be null");
	}



	@Override
	public void saveEntity(T entity) {
		getBaseDao().save(entity);
	}



	@Override
	public void deleteEntityById(Serializable id) {
		getBaseDao().deleteById(id);
	}


	@Override
	public void deleteEntityByQParams(CustomQueryParams params) {
		checkQueryParams(params);
		getBaseDao().deleteByQParams(params);
	}



	protected abstract BaseDao<T> getBaseDao();

}
