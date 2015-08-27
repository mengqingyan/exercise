package com.revencoft.basic_access.format;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.revencoft.basic_access.param.QueryCondition;


public class QueryConvertAnnotationFormatterFactory implements
		AnnotationFormatterFactory<QueryConvert> {

	@Override
	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> types = new HashSet<Class<?>>(2);
		types.add(QueryCondition.class);
		return Collections.unmodifiableSet(types);
	}

	@Override
	public Printer<?> getPrinter(QueryConvert annotation, Class<?> fieldType) {
		return new QueryFormatter();
	}

	@Override
	public Parser<?> getParser(QueryConvert annotation, Class<?> fieldType) {
		return new QueryFormatter();
	}
	
	private static final class QueryFormatter implements Formatter<QueryCondition> {

		@Override
		public String print(QueryCondition object, Locale locale) {
			return object.toString();
		}

		@Override
		public QueryCondition parse(String text, Locale locale)
				throws ParseException {
			return QueryCondition.buildCondition(text);
		}
		
	}

}
