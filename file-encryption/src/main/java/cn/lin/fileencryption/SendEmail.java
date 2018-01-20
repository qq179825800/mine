package cn.lin.fileencryption;



import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendEmail{
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();//不设置任何配置，发送时需要
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");//请求认证，与JavaMail的实现有关
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("wxl3412@163.com"));
		msg.setRecipients(Message.RecipientType.TO, "wxl3412@163.com");
		msg.setSubject("JavaMail发送的邮件");

		//邮件主体内容:组装过程
		//文本部分
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("aaa<img src='cid:mm'/>aaa", "text/html");

		//图片部分
		MimeBodyPart imagePart = new MimeBodyPart();
		//搞数据进来需要用到JAF的API
		DataHandler dh = new DataHandler(new FileDataSource("D:\\github\\mine\\file-encryption\\src\\1.jpg"));//自动探测文件的MIME类型
		imagePart.setDataHandler(dh);
		imagePart.setContentID("mm");

		//描述关系：
		MimeMultipart mmpart = new MimeMultipart();
		mmpart.addBodyPart(textPart);
		mmpart.addBodyPart(imagePart);
		mmpart.setSubType("related");//有关系的

		msg.setContent(mmpart);

		msg.saveChanges();
//		msg.writeTo(new FileOutputStream("d:/2.eml"));
		//发送邮件
		Transport ts = session.getTransport();
		ts.connect("wxl3412", "15884283609.+");
		ts.sendMessage(msg, msg.getAllRecipients());
	}
}