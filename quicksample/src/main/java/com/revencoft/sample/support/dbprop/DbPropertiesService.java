/**
 * 
 */
package com.revencoft.sample.support.dbprop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import com.revencoft.sample.utils.Exceptions;

/**
 * @author mengqingyan
 * @version 
 */
@Service
public class DbPropertiesService {
	
	
	@Autowired
	private DataSource dataSource;

	/**
	 * @return 数据库中的所有表的所有字段，若无则返回空set对象
	 */
	public Set<String> getAllColumnNamesOfDb() {
		Connection conn = null;
		Set<String> columnsRes = new HashSet<String>();
		try {
			conn = dataSource.getConnection();
			
			DatabaseMetaData dbmd = conn.getMetaData();  
			// 表名列表  
			ResultSet rest = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (rest.next()) {
				String tableName = rest.getString("TABLE_NAME");
				ResultSet columns = dbmd.getColumns(null, null, tableName, null);
				while(columns.next()) {
					columnsRes.add(columns.getString("COLUMN_NAME"));
				}
			}
			return columnsRes;
		} catch (SQLException e) {
			throw Exceptions.unchecked(e);
		} finally {
			
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}


	
	
}
