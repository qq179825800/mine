package cn.lin.code.util;/*package cn.getgrid.common.api.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;

import cn.getgrid.common.api.portal.configuration.UploadEnum;
import cn.getgrid.common.api.util.PropertiesUtil;
import cn.getgrid.common.api.util.TimeUtil;
import cn.getgrid.common.api.util.UUIDLongGenerator;

*//**
* @Title: 
* @Description: TODO(这里用一句话描述这个方法的作用)
* @param  设定文件
* @return  返回类型
* @throws
* @author ：黄小虎
* @date 2016年12月1日 上午9:44:44
 *//*
public class UploadUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

	*//**
	 * @param file
	 *            //文件对象
	 * @param filePath
	 *            //上传路径
	 * @param fileName
	 *            //文件名
	 * @return 文件名
	 *//*
	public static String fileUp(MultipartFile file, String filePath,String fileName) {
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName).replaceAll("-", "");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return fileName;
	}

	*//**
	 * 写文件到当前目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 *//*
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}

	*//**
	 * @说明: TODO
	 * @param uuId
	 * @param file
	 * @param businessType = 业务类型（服务、需求等）可为空
	 * @param uploadType = 上传图片类型，只对图片有效（原图，缩略图、展示图等）可为空
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月9日 上午11:51:36
	 *//*
	public static String uploadFile(Long uuId, MultipartFile file,String businessType, String uploadType) {
		String backStr = File.separator + businessType + File.separator + TimeUtil.getTimePath(); 	// 数据库存储图图片地址

		if(!StringUtils.isEmpty(uploadType)){
			backStr = backStr + File.separator + uploadType;
		}
		String fileRealPath = getUploadPropertiesKey()  + backStr;
		String extName = ""; // 扩展名格式：
		if (null != file) {
			File filePath = new File(fileRealPath); 	// 存放路径
			if (!filePath.exists()) {				// 目录不存在则直接创建
				filePath.mkdirs();
			}
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			// 上传文件
			String fileName = UploadUtil.fileUp(file, fileRealPath,uuId + "-" + (int) (Math.random() * 9000 + 1000) + extName);
			System.out.println(fileName);
			if (null != fileName && !"".equals(fileName)) {
				// 数据库图片存放地址
				backStr = backStr + File.separator + fileName;
			}
		}
		return backStr;
	}
	
	*//**
	 * @说明: 获取 多个文件的存储路径
	 * @param request
	 * @param businessType
	 * @param uploadType
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月9日 下午2:18:24
	 *//*
	public static HashMap<String , Object> upload(MultipartHttpServletRequest request,String businessType, String uploadType){
		
		HashMap<String , Object> map = new HashMap<String , Object>();
		MultipartFile file = null;
		for (Iterator<String> it = request.getFileNames(); it.hasNext();) { 
			String key = it.next();
			
			file = request.getFile(key);

			if(file.isEmpty()){
	        	continue;
	        }else{
	        	//进行上传图片操作
	        	String url = uploadFile(UUIDLongGenerator.generateId(), file,businessType,uploadType);
				map.put(key, url);
	        }
		}
		return map;
	}

	*//**
	 * 根据图片类型获取相对应的值
	 * @param propertiesUrl：配置文件路径
	 * @param imagPathUrl：服务器地址
	 * @param imgUrl：图片路径
	 * @param prefixImg：图片前缀地址
	 * @return
	 *//*
	public static String getPropertiesKey(){
		String propertiesUrl = UploadEnum.FILE_UPLOAD_PATH.getMessage();
		String filePathUrl = UploadEnum.READ_FILE_PATH.getMessage();
		String fileUrl = UploadEnum.READ_FILE_URL.getMessage();
		Properties properties = PropertiesUtil.getProperties(propertiesUrl);   	//读取配置文件路径
		//前缀地址
		String rootPath = PropertiesUtil.getProperty(properties, filePathUrl); 	//读取域名
		String urlPath = PropertiesUtil.getProperty(properties, fileUrl);   		//读取存放图片地址
		String prefixUrl = rootPath + urlPath;
		return prefixUrl.trim();
	}
	
	*//**
	 * 读取上传文件路径
	 * @return
	 *//*
	public static String getUploadPropertiesKey(){
		String propertiesUrl = UploadEnum.FILE_UPLOAD_PATH.getMessage();
		String filePathUrl = UploadEnum.UPLOAD_FILE_PATH.getMessage();
		String fileUrl = UploadEnum.UPLOAD_FILE_URL.getMessage();
		Properties properties = PropertiesUtil.getProperties(propertiesUrl);   //读取配置文件路径
		//前缀地址
		String rootPath = PropertiesUtil.getProperty(properties, filePathUrl); //读取域名
		String urlPath = PropertiesUtil.getProperty(properties, fileUrl);   //读取存放图片地址
		String prefixUrl = rootPath + urlPath;
		return prefixUrl.trim();
	}
	
	*//**
	 * @说明: 格式化路径为统一路径
	 * @param url
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月13日 下午3:48:26
	 *//*
	public static String formatUrl(String url){
		String location = url.replaceAll("\\\\","/");
		return location;
	}
	
	*//**
	 * @说明: 获取文件后缀
	 * @param fileName
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月9日 下午2:28:49
	 *//*
	public static String getExtName(String fileName){
		String extName = "";
		if (fileName.lastIndexOf(".") >= 0) {
			extName = fileName.substring(fileName.lastIndexOf("."));
		}
		return extName;
	}
	
	*//**
	 * @说明: 转化文件大小
	 * @param size
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月9日 下午2:30:11
	 *//*
	public static String formatFileSize(long size){
		DecimalFormat formater = new DecimalFormat("####.00");  
        if(size<1024){  
            return size+"bytes";  
        }else if(size<1024*1024){  
            float kbsize = size/1024f;    
            return formater.format(kbsize)+"KB";  
        }else if(size<1024*1024*1024){  
            float mbsize = size/1024f/1024f;    
            return formater.format(mbsize)+"MB";  
        }else if(size<1024*1024*1024*1024){  
            float gbsize = size/1024f/1024f/1024f;    
            return formater.format(gbsize)+"GB";  
        }else{  
            return "size: error";  
        }  
	}

	public static void main(String[] args) {

	}
}
*/