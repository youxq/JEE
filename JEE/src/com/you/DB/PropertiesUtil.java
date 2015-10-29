package com.you.DB;

import java.io.IOException;
import java.util.Properties;

/**
 * 这个类主要是属性文件读取方法类，所有的方法设为静态方法
 * 好处是将一些配置信息与程序进行分离，方便修改，降低了耦合性
 * 注意将文件放在src根目录下
 * @author YOU
 *
 */
public class PropertiesUtil {

	private static Properties prop = new Properties();

	
	/**
	 * 加载文件，file放在src根目录下
	 * @param fileName
	 * @return
	 */
	public static boolean loadFile(String fileName){
		try{
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 获取属性
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key){
		return prop.getProperty(key);
	}
}
