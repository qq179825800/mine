package cn.lin.code.util;

import cn.getgrid.common.api.configuration.UploadImgEnum;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 动态操作Properties文件
 * 
 * @version 1.0
 * @author lxf
 * 
 * 2016年8月15日 下午4:02:02
 */
public class PropertiesUtil {

	public final static String file = "/worklog_user.properties";
	public final static String SMS_CODE = "/sms_code.properties";
	public final static String NOTIFY_URL = "/notify_url.properties";

	/**
	 * 返回　Properties
	 * @param fileName
	 * @return
	 */
    public static Properties getProperties(String fileName){  
        Properties prop = new Properties();
        String savePath = PropertiesUtil.class.getResource(fileName).getPath();
        InputStream is;
		try {
			is = new BufferedInputStream(new FileInputStream(savePath));
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return prop;
    }


	/**
	 * 获取属性值
	 * @param key：根据key获取value
	 * @return
	 */
	public static String getProperty(Properties prop,String key) {

		return prop.getProperty(key);

	}

	/**
	 * 
	 * 将文件加载到内存中，在内存中修改key对应的value值，再将文件保存
	 * 没有key值存在时，会新增
	 */
	public static void setProper(Properties prop,String key, String value, String file) {

		try {

			prop.setProperty(key, value);
			String savePath = PropertiesUtil.class.getResource(file).getPath();
			FileOutputStream fos = new FileOutputStream(savePath);

			prop.store(fos,null);

			fos.close();
			fos.flush();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	
	
	/**
	 * 
	* @Title: setPropers 
	* @Description: 发送短信内存存放数据
	* @param @param prop
	* @param @param key
	* @param @param value
	* @param @param file    设定文件 
	* @return void    返回类型 
	* @throws
	 */
//	PropertiesUtil.setProper(PropertiesUtil.getProperties(PropertiesUtil.SMS_CODE), cellPhone, SendContent,PropertiesUtil.SMS_CODE,StartTime+"",EndTime+"",MsgType);
	
	public static void setPropers(Properties prop,String key, String code, String file,long startTime,long endTime,int MsgType) {

		try {

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", code);  //短信验证码
			map.put("startTime",startTime);
			map.put("endTime", endTime);
			map.put("msgType", MsgType);
			String ret = JSON.toJSONString(map);
			prop.setProperty(key + MsgType, ret);
			String savePath = PropertiesUtil.class.getResource(file).getPath();
			FileOutputStream fos = new FileOutputStream(savePath);

			prop.store(fos,null);

			fos.close();
			fos.flush();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	/**
	 * 获取支付宝url
	 * 
	 * @return
	 */
	public static String getNotifUrl(String notifyType){
		Properties pro = getProperties(NOTIFY_URL);
		 String url = pro.getProperty(notifyType);
		return url;
	}
	
	/**
	 * 根据图片类型获取相对应的值
	 * @param propertiesUrl：配置文件路径
	 * @param imagPathUrl：服务器地址
	 * @param imgUrl：图片路径
	 * @param prefixImg：图片前缀地址
	 * @return
	 */
	public static String getPropertiesKey(){
		String propertiesUrl = UploadImgEnum.IMGPRO.getMessage();
		String imagPathUrl = UploadImgEnum.READIMGPATH.getMessage();
		String imgUrl = UploadImgEnum.READIMGURL.getMessage();
		Properties properties = PropertiesUtil.getProperties(propertiesUrl);   //读取配置文件路径
		//图片前缀地址
		String imgPath = PropertiesUtil.getProperty(properties, imagPathUrl); //读取域名
		String imgUrlPath = PropertiesUtil.getProperty(properties, imgUrl);   //读取存放图片地址
		String prefixImgUrl = imgPath + imgUrlPath;
		prefixImgUrl = prefixImgUrl.trim();
		return prefixImgUrl;
	}
	/**
	 * 读取上传图片路径
	 * @return
	 */
	public static String getUploadPropertiesKey(){
		String propertiesUrl = UploadImgEnum.IMGPRO.getMessage();
		String imagPathUrl = UploadImgEnum.IMGPATH.getMessage();
		String imgUrl = UploadImgEnum.IMGURL.getMessage();
		Properties properties = PropertiesUtil.getProperties(propertiesUrl);   //读取配置文件路径
		//图片前缀地址
		String imgPath = PropertiesUtil.getProperty(properties, imagPathUrl); //读取域名
		String imgUrlPath = PropertiesUtil.getProperty(properties, imgUrl);   //读取存放图片地址
		String prefixImgUrl = imgPath + imgUrlPath;
		prefixImgUrl = prefixImgUrl.trim();
		return prefixImgUrl;
	}

	public static void main(String[] args) {

		/*Properties properties = getProperties(file);
		String property = PropertiesUtil.getProperty(properties, "mmmmm");
		if(StringUtils.isEmpty(property)){
			property = "0";
		}
		System.out.println(property);
		System.out.println("修改前key为110的value的值"
				+ PropertiesUtil.getProperty(properties,"110"));

		PropertiesUtil.setProper(properties,"110", "222",PropertiesUtil.SMS_CODE);

		System.out.println("修改后key为110的value的值"
				+ PropertiesUtil.getProperty(properties,"110"));
		
		PropertiesUtil.setProper(properties, "119", "119",PropertiesUtil.SMS_CODE);
		System.out.println("修改后key为119的value的值"
				+ PropertiesUtil.getProperty(properties,"119"));*/
//		Properties properties = PropertiesUtil.getProperties(UploadImgEnum.IMGPRO.getMessage());   //读取配置文件路径
		//图片前缀地址
		/*String imgPath = PropertiesUtil.getProperty(properties, UploadImgEnum.IMGPATH.getMessage());
		String imgUrl = PropertiesUtil.getProperty(properties, UploadImgEnum.IMGURL.getMessage());
		String shopImg = UploadImgEnum.SHOPIMGPATH.getMessage();*/
		/*String result = getPropertiesKey(UploadImgEnum.SHOPIMGPATH.getMessage());
	    System.out.println(result);*/
		
		  /*Date now = new Date();
		  long time = 2*60*1000;//2分钟
		  long fiveTime = 5*60*1000;   //5分钟
		  long tenTime = 10*60*1000;   //10分钟
		  Date StartTime = new Date(now.getTime() - time);//2分钟前的时间
		  Date EndTime = new Date(now.getTime() + tenTime);//10分钟后的时间
	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 123);  //短信验证码
		map.put("startTime",StartTime.getTime());
		map.put("endTime", EndTime.getTime());
		map.put("msgType", 1);
		String ret = JSON.toJSONString(map);
	    System.out.println(ret);
		
		JSONObject obj =  JSONObject.parseObject(ret);
		System.out.println(obj.get("code"));*/
		
	}

}
