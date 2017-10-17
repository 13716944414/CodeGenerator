package cn.com.kingtop;

import java.util.ArrayList;
import java.util.List;

/**
 * 表信息
 * @author jiangjiaxin
 * @date 2017-10-9 下午1:25:42
 */
public class TableInfo {
	
	/**
	 * 表名
	 */
	private String tableName;
	
	private List<ColumnsInfo> columnsInfoList = new ArrayList<ColumnsInfo>();

	/** @return the tableName */
	public String getTableName() {
		return tableName;
	}

	 /**  @param tableName the tableName to set  */ 
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** @return the columnsInfoList */
	public List<ColumnsInfo> getColumnsInfoList() {
		return columnsInfoList;
	}

	 /**  @param columnsInfoList the columnsInfoList to set  */ 
	public void setColumnsInfoList(List<ColumnsInfo> columnsInfoList) {
		this.columnsInfoList = columnsInfoList;
	}
	
}
