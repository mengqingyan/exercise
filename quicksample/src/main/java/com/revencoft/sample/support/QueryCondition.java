package com.revencoft.sample.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.revencoft.sample.exception.SqlInjectionException;
import com.revencoft.sample.utils.SqlValidator;

/**
 * 构建where条件对象，并对加入的字段值（column）进行sql注入校验
 * @author mengqingyan
 * @version
 */
public class QueryCondition {
	
	private static final Logger log = Logger.getLogger(QueryCondition.class);
	
	public static final String SEARCH_PREFIX = "search_";
	private static final String SEPERATOR = "_";
	
	private final String column;
	private final Operation operation;
	
	private final String value;

	public QueryCondition(String column, Operation operation, String value) {
		super();
		this.column = column;
		this.operation = operation;
		this.value = value;
	}
	
	
	public static QueryCondition buildCondition(String condition, String value) {
		if (!condition.startsWith(SEARCH_PREFIX)) {
			return null;
		}
		String condStr = StringUtils.substringAfter(condition,
				SEARCH_PREFIX);
		return buildConditionWithOutPrefix(condStr, value);
	}
	
	public static QueryCondition buildConditionWithOutPrefix(String condStr, String value) {

		String operation = StringUtils.substringBefore(condStr, SEPERATOR);
		
		String column = StringUtils.substringAfter(condStr, SEPERATOR);
		
		if(!SqlValidator.validateSql(column)) {
			String message = String.format("column[%s] 字段有sql注入风险！", column);
			log.error(message);
			throw new SqlInjectionException(message);
		}
		
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
		return SEARCH_PREFIX + operation + "_" + column + "="
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
