package com.revencoft.sample.support;

import org.junit.Assert;
import org.junit.Test;

import com.revencoft.sample.support.QueryCondition.Operation;

public class TestQueryCondition {

	@Test
	public void testEnum() {
		Operation eq = QueryCondition.Operation.valueOf("eq");
		Assert.assertEquals(eq, Operation.eq);
	}
}