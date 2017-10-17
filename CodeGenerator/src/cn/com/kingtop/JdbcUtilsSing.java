package cn.com.kingtop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类
 * @author jiangjiaxin
 * @date 2017-10-11 上午11:40:20
 */
public class JdbcUtilsSing {

	private ConfigurationInfo configurationInfo;
	
	private static JdbcUtilsSing instance = null;
	
	 /**
	  *
	  * @author jiangjiaxin
	  * @date 2017-10-11 下午2:39:26
	  */
	public JdbcUtilsSing(ConfigurationInfo configurationInfo) {
		try {
			Class.forName(configurationInfo.getDriverClass());
			this.configurationInfo = configurationInfo;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}

	public static JdbcUtilsSing getInstance(ConfigurationInfo configurationInfo) {  
        if (instance == null) {  
            synchronized (JdbcUtilsSing.class) {  
                if (instance == null) {  
                    instance = new JdbcUtilsSing(configurationInfo);  
                }  
            }  
        }  
        return instance;  
    } 
	
	public Connection getConnection() throws SQLException {  
        return DriverManager.getConnection(configurationInfo.getUrl(), configurationInfo.getUsername(), configurationInfo.getPassword());  
    }
	
	//关闭连接  
    public void close(Object o){
        if (o == null){    
            return;    
        }    
        if (o instanceof ResultSet){    
            try {    
                ((ResultSet)o).close();    
            } catch (SQLException e) {    
                e.printStackTrace();    
            }    
        } else if(o instanceof Statement){    
            try {    
                ((Statement)o).close();    
            } catch (SQLException e) {    
                e.printStackTrace();    
            }    
        } else if (o instanceof Connection){    
            Connection c = (Connection)o;    
            try {    
                if (!c.isClosed()){    
                    c.close();    
                }    
            } catch (SQLException e) {    
                e.printStackTrace();    
            }    
        }      
    }

}
