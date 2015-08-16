package com.revencoft.sample.support;

import java.util.ArrayList;
import java.util.List;

import com.revencoft.sample.support.formatter.QueryConvert;

public class CustomQueryParams extends DataTableQueryParams {

	
	@QueryConvert
	private List<QueryCondition> qconditions;

	public void addQueryCondition(QueryCondition condition) {
		if (qconditions == null) {
			qconditions = new ArrayList<QueryCondition>();
		}
		this.qconditions.add(condition);
	}

	public void addQueryCondition(String condition, Object value) {
		QueryCondition qCondition = QueryCondition.buildCondition(condition, value);
		if(qCondition != null) {
			addQueryCondition(qCondition);
		}
	}

	public void setQconditions(List<QueryCondition> qconditions) {
		this.qconditions = qconditions;
	}

	public List<QueryCondition> getQconditions() {
		return qconditions;
	}
	
	
	
}
