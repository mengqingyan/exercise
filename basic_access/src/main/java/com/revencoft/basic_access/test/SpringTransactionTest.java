/**
 * 
 */
package com.revencoft.basic_access.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author mengqingyan
 * @version 
 */
@DirtiesContext
public abstract class SpringTransactionTest extends AbstractTransactionalJUnit4SpringContextTests{

	
	protected DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
	
	
}
