package cn.lin.fileencryption;



import com.sun.mail.smtp.SMTPAddressSucceededException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendEmail{
	public static void main(String[] args)   {
		//sendPicEmail();
		try {

//			sendFileEmail("wxl3412@163.com","179825800@qq.com","15884283609.+");
			sendFileEmail("wangxiaolin@detaoma.com","zhangjianhua@detaoma.com","15884283609.+");
		} catch (SMTPAddressSucceededException e){

			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("asdasdasd >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}catch (Exception e1){
			e1.printStackTrace();
		}
	}

	private synchronized static void sendPicEmail(String from,String to,String password) throws MessagingException {
		Properties props = new Properties();//不设置任何配置，发送时需要
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");//请求认证，与JavaMail的实现有关
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, to);
		msg.setSubject("JavaMail发送的邮件");

		//邮件主体内容:组装过程
		//文本部分
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("aaa<img src='cid:mm'/>aaa", "text/html");

		//图片部分
		MimeBodyPart imagePart = new MimeBodyPart();
		//搞数据进来需要用到JAF的API
		DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\7040MT\\Desktop\\i000_003.jpg"));//自动探测文件的MIME类型
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
		String[] split = from.split("@");
		ts.connect(split[0], password);
		ts.sendMessage(msg, msg.getAllRecipients());
	}

	private synchronized static void sendFileEmail( String from,String to,String password) throws Exception {
		Properties props = new Properties();//不设置任何配置，发送时需要
		props.setProperty("mail.transport.protocol", "smtp");
//		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.host", "smtp.263.net");
		props.setProperty("mail.smtp.auth", "true");//请求认证，与JavaMail的实现有关
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, to);
		msg.setSubject("JavaMail发送的邮件3");

		//邮件主体内容:组装过程
		//文本部分
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("美女aaa<img src='cid:mm'/>aaa", "text/html;charset=UTF-8");

		//图片部分
		MimeBodyPart imagePart = new MimeBodyPart();
		//搞数据进来需要用到JAF的API
		DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\7040MT\\Desktop\\i000_003.jpg"));//自动探测文件的MIME类型
		imagePart.setDataHandler(dh);
		imagePart.setContentID("mm");

		//附件部分
		MimeBodyPart attachmentPart = new MimeBodyPart();
		DataHandler dh1 = new DataHandler(new FileDataSource("C:\\Users\\7040MT\\Desktop\\auto-master.zip"));//自动探测文件的MIME类型
		String name = dh1.getName();
		System.out.println(name);
		attachmentPart.setDataHandler(dh1);
		//手工设置附件的名称
		attachmentPart.setFileName(MimeUtility.encodeText(name));

		//描述关系：
		MimeMultipart mmpart = new MimeMultipart();
		mmpart.addBodyPart(textPart);
		mmpart.addBodyPart(imagePart);
		mmpart.setSubType("related");//有关系的


		//把文本图片当做一个部分
		MimeBodyPart textImagePart = new MimeBodyPart();
		textImagePart.setContent(mmpart);

		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(textImagePart);
		multipart.addBodyPart(attachmentPart);
		multipart.setSubType("mixed");//复杂关系


		msg.setContent(multipart);

		msg.saveChanges();
//		msg.writeTo(new FileOutputStream("d:/3.eml"));
		//发送邮件
		Transport ts = session.getTransport();
//		String[] split = from.split("@");

		ts.connect(from, password);
		ts.sendMessage(msg, msg.getAllRecipients());
	}
}