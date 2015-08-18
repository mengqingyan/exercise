/**
 * 
 */
package com.revencoft.sample.common;

import java.io.Serializable;

import org.junit.Test;

/**
 * @author mengqingyan
 * @version 
 */
public class TestIntSerializable {

	@Test
	public void testInt2Serial() {
		int id = 3;
		deleteEntityById(id);
	}
	
	void deleteEntityById(Serializable id) {
		System.out.println("id: " + id);
	}
}
