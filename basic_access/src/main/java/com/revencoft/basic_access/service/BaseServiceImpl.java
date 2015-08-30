/**
 * 
 */
package com.revencoft.basic_access.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.revencoft.basic_access.dao.BaseDao;
import com.revencoft.basic_access.param.CustomQueryParams;
import com.revencoft.basic_access.utils.CollectionUtil;


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
		return getBaseDao().queryByQParams(params);
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public int getEntityCountByQParams(CustomQueryParams params) {
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
	

	@Override
	public void updateEntityByQParams(CustomQueryParams params) {
		checkQueryParams(params);
		CollectionUtil.checkNotEmptyMap(params.getSearchParams(), "searchParams for update can't be null!");
		getBaseDao().updateByQParams(params);
	}
	
	
	protected abstract BaseDao<T> getBaseDao();

}
