package com.revencoft.basic_access.param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.revencoft.basic_access.ControllerParamPostProcess;
import com.revencoft.basic_access.format.QueryConvert;
import com.revencoft.basic_access.validation.QParamCheck;


/**
 * 自定义查询参数，用于拼接where语句；根据CustomQueryParams来查询、更改或删除实体
 * 时，若无需where条件，请将该类的空实例传入。
 * @author mengqingyan
 * @version
 * @see com.revencoft.sample.service.BaseService#deleteEntityByQParams(CustomQueryParams)
 * @see com.revencoft.sample.service.BaseService#getEntityByQParams(CustomQueryParams)
 * @see com.revencoft.sample.service.BaseService#getEntityCountByQParams(CustomQueryParams)
 */
public class CustomQueryParams extends DataTableQueryParams implements ControllerParamPostProcess{

	
	@QueryConvert
	private List<QueryCondition> qconditions;
	
	/**
	 * 封装查询参数：字段--值;<br/>
	 * 可用于封装update的set参数
	 */
	@QParamCheck
	private Map<String, String> searchParams;
	
	public void addQueryCondition(QueryCondition condition) {
		if (qconditions == null) {
			qconditions = new ArrayList<QueryCondition>();
		}
		this.qconditions.add(condition);
	}
	
	public boolean removeQueryCondition(QueryCondition condition) {
		if(qconditions == null) {
			return false;
		}
		return qconditions.remove(condition);
	}
	
	public void clearConditions() {
		if(qconditions != null) {
			qconditions.clear();
		}
		if(searchParams != null) {
			searchParams.clear();
		}
	}

	public void addQueryCondition(String condition, Object value) {
		QueryCondition qCondition = QueryCondition.buildCondition(condition, value.toString());
		if(qCondition != null) {
			addQueryCondition(qCondition);
		}
	}

	public void setQconditions(List<QueryCondition> qconditions) {
		this.qconditions = qconditions;
	}

	/**
	 * 
	 * @return 查询条件集合，若无，则返回空集合
	 */
	public List<QueryCondition> getQconditions() {
		if(qconditions == null) {
			return Collections.emptyList();
		}
		return qconditions;
	}

	public Map<String, String> getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(Map<String, String> searchParams) {
		this.searchParams = searchParams;
	}

	@Override
	public void postProcessParam() {
		pack();
	}
	
	private void pack() {
		
		if (searchParams == null || searchParams.isEmpty()) {
			return;
		}
		Set<Entry<String, String>> paramsSet = searchParams.entrySet();
		for (Iterator<Entry<String, String>> iterator = paramsSet.iterator(); iterator
				.hasNext();) {
			Entry<String, String> entry = iterator.next();
			addQueryCondition(entry.getKey(), entry.getValue());
		}
		//release the params in map
		searchParams = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((qconditions == null) ? 0 : qconditions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomQueryParams other = (CustomQueryParams) obj;
		if (qconditions == null) {
			if (other.qconditions != null)
				return false;
		} else if (!qconditions.equals(other.qconditions))
			return false;
		return true;
	}
	
	
	
}
