package com.revencoft.sample.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.revencoft.sample.support.formatter.QueryConvert;

public class CustomQueryParams extends DataTableQueryParams {

	
	@QueryConvert
	private List<QueryCondition> qconditions;
	
	private Map<String, String> searchParams;
	
	private boolean isConverted = false;

	public void addQueryCondition(QueryCondition condition) {
		if (qconditions == null) {
			qconditions = new ArrayList<QueryCondition>();
		}
		this.qconditions.add(condition);
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
		
		if(isConverted) {
			return qconditions;
		}
		
		if (searchParams == null || searchParams.isEmpty()) {
			if(qconditions == null) {
				return Collections.emptyList();
			} else {
				return qconditions;
			}
		}
		Set<Entry<String, String>> paramsSet = searchParams.entrySet();
		for (Iterator<Entry<String, String>> iterator = paramsSet.iterator(); iterator
				.hasNext();) {
			Entry<String, String> entry = iterator.next();
			addQueryCondition(entry.getKey(), entry.getValue());
		}
		isConverted = true;
		return qconditions;
	}

	public Map<String, String> getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(Map<String, String> searchParams) {
		this.searchParams = searchParams;
	}
	
	
	
}
