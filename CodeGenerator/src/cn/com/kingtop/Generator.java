package cn.com.kingtop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 入口类
 * @author jiangjiaxin
 * @date 2017-10-11 下午3:00:56
 */
public class Generator {

	/**
	 *
	 * @param args
	 * @author jiangjiaxin
	 * @date 2017-10-11 下午3:00:56
	 */
	public static void main(String[] args) {
		
		ConfigurationInfo configurationInfo = getConfigurationInfo();
		
		TableInfoManage tableInfoManage = new TableInfoManage(configurationInfo);
		List<TableInfo> tableInfoList = tableInfoManage.getTableInfoList();
		
		Map<String, Object> root = null;
		ManufactureFile mf = null;
		for(TableInfo temp_tableInfo : tableInfoList){
			mf = new ManufactureFile();
			root = new HashMap<String, Object>();
			root.put("tableName", strFormat(temp_tableInfo.getTableName(), "0"));
			root.put("columnInfoList", temp_tableInfo.getColumnsInfoList());
			root.put("classpath", configurationInfo.getClasspath());
			mf.manufactureType(root, configurationInfo);
		}
	}
	
	/**
	 * 获取配置文件信息
	 *
	 * @return
	 * @author jiangjiaxin
	 * @date 2017-10-11 下午3:02:44
	 */
	public static ConfigurationInfo getConfigurationInfo(){
		ConfigurationInfo configurationInfo = new ConfigurationInfo();
//		ResourceBundle resource = ResourceBundle.getBundle("generator");// generator为属性文件名，如果是放在src下，直接用generator即可
//		configurationInfo.setUsername(resource.getString("jdbc.username"));
//		configurationInfo.setPassword(resource.getString("jdbc.password"));
//		configurationInfo.setDriverClass(resource.getString("jdbc.driver"));
//		configurationInfo.setUrl(resource.getString("jdbc.url"));
//		configurationInfo.setTableName(resource.getString("tableName"));
//		configurationInfo.setOutPath(resource.getString("outPath"));
//		configurationInfo.setClasspath(resource.getString("classpath"));
		try {
			Properties properties = getProperties();
			configurationInfo.setUsername(properties.getProperty("jdbc.username"));
			configurationInfo.setPassword(properties.getProperty("jdbc.password"));
			configurationInfo.setDriverClass(properties.getProperty("jdbc.driver"));
			configurationInfo.setUrl(properties.getProperty("jdbc.url"));
			configurationInfo.setTableName(properties.getProperty("tableName"));
			configurationInfo.setOutPath(properties.getProperty("outPath"));
			configurationInfo.setClasspath(properties.getProperty("classpath"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configurationInfo;
	}
	
	private static Properties getProperties() throws IOException {  
        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/styspace/config.properties");
        String rootPath = System.getProperty("user.dir");
        InputStream inputStream = new FileInputStream(new File(rootPath + "\\generator.properties"));
        Properties properties = new Properties();  
        try{  
            properties.load(inputStream);  
        }catch (IOException ioE){  
            ioE.printStackTrace();  
        }finally{  
            inputStream.close();  
        }  
        System.out.println("name:"+properties.getProperty("jdbc.username")); 
        return properties;
    }
	
	/**
	 * 字符串格式化
	 *
	 * @param str
	 * @param type 0:格式化方法名，1:格式化属性名
	 * @return
	 * @author jiangjiaxin
	 * @date 2017-10-11 上午9:16:44
	 */
	public static String strFormat(String str, String type){
		if("0".equals(type)){
			str = captureText(str, 0);
		}
		int index = str.lastIndexOf("_");
		while(index != -1){
			if(index < str.length()-1){
				char c = str.charAt(index + 1);
				int i = (int)c;
				if((i >= 65 && i <= 90) || (i >= 97 && i <= 122)){
					
				}
				str = captureText(str, index+1);
			}
			str = replaceCharacter(str, index);
			index = str.lastIndexOf("_");
		}
		return str;
	}
	
	/**
	 * 将指定位置字符大写
	 *
	 * @param name
	 * @return
	 * @author jiangjiaxin
	 * @date 2017-10-10 下午4:33:07
	 */
	public static String captureText(String text, int index) {
		char[] cs = text.toCharArray();
		cs[index] -= 32;
		return String.valueOf(cs);
	}
	
	/**
	 * 替换指定位置字符
	 *
	 * @param text
	 * @param index
	 * @return
	 * @author jiangjiaxin
	 * @date 2017-10-10 下午5:27:13
	 */
	public static String replaceCharacter(String text, int index){
		StringBuilder sb = new StringBuilder(text);
        sb.replace(index, (index + 1), "");
        return sb.toString();
	}

}
