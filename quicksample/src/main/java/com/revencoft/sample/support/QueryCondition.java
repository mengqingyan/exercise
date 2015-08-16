package com.revencoft.sample.support;

import org.apache.commons.lang3.StringUtils;

public class QueryCondition {
	
	private static final String SEARCH_PREFIX = "search_";
	private static final String SEPERATOR = "_";
	
	private final String column;
	private final Operation operation;
	
	private final Object value;

	public QueryCondition(String column, Operation operation, Object value) {
		super();
		this.column = column;
		this.operation = operation;
		this.value = value;
	}
	
	
	public static QueryCondition buildCondition(String condition, Object value) {
		if (!condition.startsWith(SEARCH_PREFIX)) {
			return null;
		}
		String condStr = StringUtils.substringAfter(condition,
				SEARCH_PREFIX);
		String operation = StringUtils.substringBefore(condStr, SEPERATOR);
		String column = StringUtils.substringAfter(condStr, SEPERATOR);
		return new QueryCondition(column,
				Operation.valueOf(operation), value);
	}
	
	public static QueryCondition buildCondition(String conditionQueryString) {
		if (!conditionQueryString.startsWith(SEARCH_PREFIX)) {
			return null;
		}
		String condition = StringUtils.substringBefore(conditionQueryString, "=");
		String value = StringUtils.substringAfter(conditionQueryString, "=");
		return buildCondition(condition, value);
	}

	public String getColumn() {
		return column;
	}

	public Operation getOperation() {
		return operation;
	}

	public Object getValue() {
		return value;
	}
	
	
	
	@Override
	public String toString() {
		return "search_" + operation + "_" + column + "="
				+ value;
	}



	public enum Operation {
			
			like("like"), 
			eq("="), 
			neq("!="), 
			gt(">"), 
			gte(">="), 
			lt("<"), 
			lte("<=");
			
			private final String operation_name;
			
			private Operation(String operation_name) {
				this.operation_name = operation_name;
			}
			
			@Override
			public String toString() {
				return operation_name;
			}
	}
	
}
