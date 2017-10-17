package cn.com.kingtop;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据整理
 * @author jiangjiaxin
 * @date 2017-10-11 下午2:07:32
 */
public class TableInfoManage {

	private List<String> tableNames = null;
	private JdbcUtilsSing jdbcUtilsSing = null;
	private Connection conn = null;
	
	 /**
	  *
	  * @author jiangjiaxin
	  * @date 2017-10-11 下午2:08:33
	  */
	public TableInfoManage(ConfigurationInfo configurationInfo) {
		jdbcUtilsSing = JdbcUtilsSing.getInstance(configurationInfo);
		this.tableNames = this.getTablesName(configurationInfo.getTableName());
	}
	
	public List<TableInfo> getTableInfoList(){
		List<TableInfo> tableInfoList = new ArrayList<TableInfo>();
		TableInfo tableInfo = null;
		for(String tmpTableName : tableNames){
			tableInfo = getTableInfo(tmpTableName);
			tableInfoList.add(tableInfo);
		}
		return tableInfoList;
	}
	
	/**
	 * 获得表中所有列信息
	 *
	 * @param tableName 表名
	 * @author jiangjiaxin
	 * @date 2017-10-9 下午1:19:05
	 */
	public TableInfo getTableInfo(String thisTableName) {
		TableInfo tableInfo = new TableInfo();
		tableInfo.setTableName(thisTableName);
		List<ColumnsInfo> columnsInfoList = new ArrayList<ColumnsInfo>();
		ResultSet rs = null;
		try {
			conn = jdbcUtilsSing.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			rs =dbmd.getColumns(null,"%", thisTableName,"%");
			while(rs.next()){
				ColumnsInfo columnsInfo = new ColumnsInfo();
				columnsInfo.setColumnName(rs.getString("COLUMN_NAME"));
				columnsInfo.setSqlColumnType(rs.getString("TYPE_NAME"));
				columnsInfo.setColumnSize(rs.getInt("COLUMN_SIZE"));
				columnsInfo.setDigits(rs.getInt("DECIMAL_DIGITS"));
				columnsInfo.setNullable(rs.getInt("NULLABLE"));
				columnsInfo.setColumnRemarks(rs.getString("REMARKS"));
				mysqlTypeConversion(columnsInfo);
				columnsInfo.setFunctionName(Generator.strFormat(columnsInfo.getColumnName(), "0"));
				columnsInfo.setAttributeName(Generator.strFormat(columnsInfo.getColumnName(), "1"));
				columnsInfoList.add(columnsInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtilsSing.close(rs);
			jdbcUtilsSing.close(conn);
		}
		tableInfo.setColumnsInfoList(columnsInfoList);
		return tableInfo;
	}
	
	/**
	 * 获得表名称
	 *
	 * @return 表名集合
	 * @author jiangjiaxin
	 * @date 2017-10-9 上午11:44:42
	 */
	private List<String> getTablesName(String tableName){
		List<String> tableNames = new ArrayList<String>();
		ResultSet tableRet = null;
		try {
			conn = jdbcUtilsSing.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			tableRet = dbmd.getTables(null, "%", tableName, new String[]{"TABLE"});
			while (tableRet.next()){
				tableNames.add(tableRet.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtilsSing.close(tableRet);
			jdbcUtilsSing.close(conn);
		}
		return tableNames;
	}
	
	/**
     * 将Mysql数据库字段类型转换成java类型
     *
     * @author jiangjiaxin
     * @date 2017-10-10 下午3:00:12
     */
	public static void mysqlTypeConversion(ColumnsInfo columnsInfo) {
		if ("BIT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("byte[]");
		} else if ("TINYINT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Integer");
		} else if ("SMALLINT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Integer");
		} else if ("MEDIUMINT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Integer");
		} else if ("INT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("int");
		} else if ("INTEGER".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Integer");
		} else if ("BIGINT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("long");
		} else if ("FLOAT".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("float");
		} else if ("DOUBLE".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("double");
		} else if ("DECIMAL".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("BigDecimal");
		} else if ("DATE".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Date");
		} else if ("DATETIME".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Timestamp");
		} else if ("TIMESTAMP".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Timestamp");
		} else if ("TIME".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Time");
		} else if ("YEAR".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("Date");
		} else if ("BINARY".equals(columnsInfo.getSqlColumnType())
				|| "MEDIUMBLOB".equals(columnsInfo.getSqlColumnType())
				|| "BLOB".equals(columnsInfo.getSqlColumnType())
				|| "VARBINARY".equals(columnsInfo.getSqlColumnType())
				|| "TINYBLOB".equals(columnsInfo.getSqlColumnType())
				|| "LONGBLOB".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("byte[]");
		} else if ("TINYTEXT".equals(columnsInfo.getSqlColumnType())
				|| "TEXT".equals(columnsInfo.getSqlColumnType())
				|| "MEDIUMTEXT".equals(columnsInfo.getSqlColumnType())
				|| "LONGTEXT".equals(columnsInfo.getSqlColumnType())
				|| "ENUM".equals(columnsInfo.getSqlColumnType())
				|| "SET".equals(columnsInfo.getSqlColumnType())
				|| "VARCHAR".equals(columnsInfo.getSqlColumnType())
				|| "CHAR".equals(columnsInfo.getSqlColumnType())) {
			columnsInfo.setJavaColumnType("String");
		}
	}
}
