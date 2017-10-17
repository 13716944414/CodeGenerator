package cn.com.kingtop;

/**
 * 列信息
 * @author jiangjiaxin
 * @date 2017-10-9 下午2:10:40
 */
public class ColumnsInfo {

	/**
	 * 列名
	 */
	private String columnName;
	
	/**
	 * java属性名称
	 */
	private String attributeName;
	
	/**
	 * 方法名称
	 */
	private String functionName;
	
	/**
	 * 数据库字段类型
	 */
	private String sqlColumnType;
	
	/**
	 * java字段类型
	 */
	private String javaColumnType;
	
	/**
	 * 字段长度
	 */
	private int columnSize;
	
	/**
	 * 小数位数
	 */
	private int digits;
	
	/**
	 * 列注释
	 */
	private String columnRemarks;
	
	/**
	 * 是否为空: 1:可以为null 0:不可为null
	 */
	private int nullable;

	/** @return the columnName */
	public String getColumnName() {
		return columnName;
	}

	/**  @param columnName the columnName to set  */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/** @return the columnSize */
	public int getColumnSize() {
		return columnSize;
	}

	/**  @param columnSize the columnSize to set  */
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	/** @return the digits */
	public int getDigits() {
		return digits;
	}

	/**  @param digits the digits to set  */
	public void setDigits(int digits) {
		this.digits = digits;
	}

	/** @return the columnRemarks */
	public String getColumnRemarks() {
		return columnRemarks;
	}

	/**  @param columnRemarks the columnRemarks to set  */
	public void setColumnRemarks(String columnRemarks) {
		this.columnRemarks = columnRemarks;
	}

	/** @return the nullable */
	public int getNullable() {
		return nullable;
	}

	/**  @param nullable the nullable to set  */
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	/** @return the javaColumnType */
	public String getJavaColumnType() {
		return javaColumnType;
	}

	 /**  @param javaColumnType the javaColumnType to set  */ 
	public void setJavaColumnType(String javaColumnType) {
		this.javaColumnType = javaColumnType;
	}

	/** @return the sqlColumnType */
	public String getSqlColumnType() {
		return sqlColumnType;
	}

	 /**  @param sqlColumnType the sqlColumnType to set  */ 
	public void setSqlColumnType(String sqlColumnType) {
		this.sqlColumnType = sqlColumnType;
	}

	/** @return the attributeName */
	public String getAttributeName() {
		return attributeName;
	}

	 /**  @param attributeName the attributeName to set  */ 
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/** @return the functionName */
	public String getFunctionName() {
		return functionName;
	}

	 /**  @param functionName the functionName to set  */ 
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
