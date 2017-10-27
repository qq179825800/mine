/*
package cn.lin.code.util;

import cn.getgrid.common.api.entity.root;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

*/
/**
 * @说明：发送短信功能
 *
 * @author 张春雷
 *
 * @2016年11月22日 下午5:54:52
 *//*

public class SendSmsUtil {

	final static Logger logger = LoggerFactory.getLogger(SendSmsUtil.class);
	private final static String FILE = "/mxtong_user.properties";

	*/
/**
	 * @说明：
	 * @author 张春雷
	 * @param cellPhone ：发送对象手机号
	 * @param sendContent ： 发送内容 :必须以【德稻全球创新网络】结尾
	 * @param sendTime ：定时发送时间，为空则立即发送
	 * @return
	 * @return boolean
	 * @2016年11月22日 下午5:55:25
	 *//*

	public static boolean sendSmsCode(String cellPhone, String sendContent, String sendTime) {

		logger.info("----------sendSmsCode----------");

		try {
			if(sendTime==null||sendTime.length()==0||new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sendTime).before(new Date())){
				sendTime = "";
			}

			// 读取配置文件
			//Properties properties = PropertiesUtil.getProperties(FILE);
			// 调用短信第三方接口
			//String url = properties.getProperty("Url") + properties.getProperty("Method");
			
			String url = "http://www.mxtong.net.cn/gateway/Services.asmx/DirectSend";

			// 接口参数
			JSONObject createMap = new JSONObject();
			createMap.put("UserID", "965866");
			createMap.put("Account", "admin");
			createMap.put("Password", "DUWBT2");
			createMap.put("Phones", cellPhone);
			createMap.put("Content", sendContent);
			createMap.put("SendTime", sendTime);
			createMap.put("SendType", "1");
			createMap.put("PostFixNumber", "");
			String xmlStr = MyHttpClientUtil.post(url, createMap, null);
			logger.info("发送短信结果" + xmlStr);

			xmlStr = xmlStr
					.replace(
							"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"JobSendedDescription\"",
							"");
			xmlStr = xmlStr.toLowerCase();
			xmlStr = xmlStr.substring(0, xmlStr.length());
			logger.info(xmlStr);

			JAXBContext context = JAXBContext.newInstance(root.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			root r = (root) unmarshaller.unmarshal(new StringReader(xmlStr));
			String retCode = r.getRetcode();

			if (retCode.equals("sucess")) {
				return true;
			} else if (retCode.equals("faild")) {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
*/
