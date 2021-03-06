package cn.com.kingtop;

/**
 * 配置文件信息
 * 
 * @author jiangjiaxin
 * @date 2017-10-11 下午2:42:09
 */
public class ConfigurationInfo {

	private String username;

	private String password;

	private String url;

	private String driverClass;

	private String tableName;
	
	private String completeTableName;

	private String outPath;

	private String classPath;

	private String basePath;

	/** @return the username */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** @return the password */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** @return the url */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** @return the driverClass */
	public String getDriverClass() {
		return driverClass;
	}

	/**
	 * @param driverClass
	 *            the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/** @return the tableName */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** @return the outPath */
	public String getOutPath() {
		return outPath;
	}

	/**
	 * @param outPath
	 *            the outPath to set
	 */
	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getCompleteTableName() {
		return completeTableName;
	}

	public void setCompleteTableName(String completeTableName) {
		this.completeTableName = completeTableName;
	}
	
	

}
