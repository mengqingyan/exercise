package com.revencoft.sample.support;

import org.junit.Assert;
import org.junit.Test;

import com.revencoft.basic_access.param.QueryCondition;
import com.revencoft.basic_access.param.QueryCondition.Operation;

public class TestQueryCondition {

	@Test
	public void testEnum() {
		Operation eq = QueryCondition.Operation.valueOf("eq");
		Assert.assertEquals(eq, Operation.eq);
	}
}
