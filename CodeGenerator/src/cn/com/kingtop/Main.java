package cn.com.kingtop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jiangjiaxin
 * @date 2017-10-17 下午2:19:41
 */
public class Main {

	/**
	 *
	 * @param args
	 * @author jiangjiaxin
	 * @date 2017-10-17 下午2:19:41
	 */
	public static void main(String[] args) {
		try {
			getProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static void getProperties() throws IOException {  
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
    }
}
