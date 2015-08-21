/**
 * 
 */
package com.revencoft.sample.support.validation;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revencoft.sample.support.CustomQueryParams;
import com.revencoft.sample.support.QueryCondition;
import com.revencoft.sample.support.dbprop.DbPropertiesService;

/**
 * 该类是单例;<br/>
 * 用于校验{@link CustomQueryParams}下的searchParams属性，预防sql注入风险
 * @author mengqingyan
 * @version
 */
public class QueryParamCheckValidator implements
		ConstraintValidator<QParamCheck, Map<String, String>> {
	private static final Logger log = Logger.getLogger(QueryParamCheckValidator.class);
	
	private static Set<String> dbColumns;
	
	@Autowired
	private DbPropertiesService dbPropService;
	
	
	

	@Override
	public void initialize(QParamCheck constraintAnnotation) {
		dbColumns = dbPropService.getAllColumnNamesOfDb();
	}


	@Override
	public boolean isValid(Map<String, String> value,
			ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		Set<String> searchedColumnsSet = value.keySet();
		for (Iterator<String> iterator = searchedColumnsSet.iterator(); iterator.hasNext();) {
			String searchedColumn = iterator.next();
			String column = QueryCondition.extractColumn(searchedColumn);
			if(StringUtils.isBlank(column) || !dbColumns.contains(column)) {
				return false;
			}
		}
		return true;
	}

}
