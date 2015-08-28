package com.revencoft.basic_access.param;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.revencoft.basic_access.validation.QParamCheck;

/**
 * 构建where条件对象，并对加入的字段值（column）进行sql注入校验
 * @author mengqingyan
 * @version
 */
public class QueryCondition {
	
	private static final Logger log = Logger.getLogger(QueryCondition.class);
	
	public static final String SEARCH_PREFIX = "search_";
	private static final String SEPERATOR = "_";
	
	@QParamCheck
	private String column;
	private Operation operation;
	
	private String value;

	public QueryCondition(String column, Operation operation, String value) {
		super();
//		if(!SqlValidator.validateSql(column)) {
//			String message = String.format("column[%s] 字段有sql注入风险！", column);
//			log.error(message);
//			throw new SqlInjectionException(message);
//		}
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
		
		String column = extractColumn(condStr);
		
		return new QueryCondition(column,
				Operation.valueOf(operation), value);
	}
	
	public static String extractColumn(String condStr) {
		return StringUtils.substringAfterLast(condStr, SEPERATOR);
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
	
	
	
	public void setValue(String value) {
		this.value = value;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public void setOperation(Operation operation) {
		this.operation = operation;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result
				+ ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryCondition other = (QueryCondition) obj;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (operation != other.operation)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
