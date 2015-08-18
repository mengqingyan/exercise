/**
 * 
 */
package com.revencoft.sample.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.revencoft.sample.dao.BaseDao;
import com.revencoft.sample.support.CustomQueryParams;

/**
 * @author mengqingyan
 * @version 
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	
	
	@Override
	@Transactional(readOnly=true)
	public List<T> getEntityByQParams(CustomQueryParams params) {
		return getBaseDao().queryByQParams(params);
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public int getEntityCountByQParams(CustomQueryParams params) {
		return getBaseDao().queryCountByQParams(params);
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
		getBaseDao().deleteByQParams(params);
	}



	protected abstract BaseDao<T> getBaseDao();

}
