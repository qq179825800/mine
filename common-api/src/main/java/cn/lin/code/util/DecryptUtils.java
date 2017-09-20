package cn.lin.code.util;

import java.util.HashMap;

/**
 * @说明：密文操作
 *
 * @author 张春雷
 *
 * @2016年9月19日 下午2:02:58
 */
public class DecryptUtils {

	/**
	 * @说明：密文转换：将密文字符串转换为map键值对
	 * @author 张春雷
	 * @param ciphertext ：密文字符串
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String> ：密文键值对
	 * @2016年9月19日 下午2:04:16
	 */
	public static HashMap<String, String> Transformation (String ciphertext) throws Exception {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("error", "false");
		//base64解码
		byte[] decode = Base64Utils.decode(ciphertext);
		ciphertext = new String(decode);
		//分割一级字符串
		String[] parameters = ciphertext.split("&");
		if(parameters==null||parameters.length<1){
			map.put("error", "true");
			return map;
		}
		else{
			//分割二级字符串
			//String[] bizContent = null;
			for(int i=0;i<parameters.length;i++){
//				String[] bizContent = null;
//				bizContent = parameters[i].split("=");
				if(parameters[i].split("=").length!=2){
					map.put("error", "true");
					return map;
				}
				map.put(parameters[i].split("=")[0], parameters[i].split("=")[1]);
			}
		}
		return map;
	}
}
