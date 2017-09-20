package cn.lin.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(MsgUtil.class);
	private static final String FILE_PATH = "/msg.properties";
	private static InputStream in = null;
	private static Properties prop = new Properties(); 
	
	static {
		try {
			in = MsgUtil.class.getResourceAsStream(FILE_PATH);
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getContent(String key) {
		return prop.getProperty(key);
	}
	
	public static String fillStringByArgs(String str,String[] arr){
        Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
        
        while(m.find()){
            str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
        }
        
        return str;
    }
	
	public static void main(String[] args) {
		String str = MsgUtil.getContent("msg_pc_generate_order_content");
		logger.info(str);
		
		String []arr = new String[]{"1111", "22222"};
		String retStr = fillStringByArgs(str, arr);
		logger.info(retStr);
	}
	 
}