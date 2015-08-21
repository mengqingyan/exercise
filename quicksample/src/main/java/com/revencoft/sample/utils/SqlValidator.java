/**
 * 
 */
package com.revencoft.sample.utils;

/**
 * @author mengqingyan
 * @version 
 */
@Deprecated
public abstract class SqlValidator {
	
	private static final String badStr = "'|and |exec |execute |insert |select |delete |update |count |" +
            "char |declare |sitename|net user|xp_cmdshell|;|or |-|+|,|like |create |drop |" +
            "table |from |grant |use |group_concat|column_name|" +
            "information_schema.columns|table_schema|union |where |order |by |count|*|" +
            "chr|mid|master|truncate |;|-|--|+|,|//|/|%|#|=";//过滤掉的sql关键字
	
	private static final String[] badStrs = badStr.split("\\|");
	
	
	/**
	 * 效验sql
	 * @param str
	 * @return true:通过；false:不通过
	 */
    public static boolean validateSql(String str) {
        str = str.toLowerCase();//统一转为小写
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return false;
            }
        }
        return true;
    }
}
