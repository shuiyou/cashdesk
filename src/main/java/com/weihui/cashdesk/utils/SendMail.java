package com.weihui.cashdesk.utils;

import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

public class SendMail {
    private Logger log = LogManager.getLogger(this.getClass());
    Vector<String> file = new Vector<String>();// 附件文件集合
    String filename = "";// 附件文件名
    
    public void attachfile(String fname) {
	file.addElement(fname);
    }

    /**
     *
     * @param userName
     *            发送邮箱账号 xxx@xxx.com形式
     * @param passWord
     *            发送邮件密码
     * @param smtpHost
     *            stmp服务器地址
     * @param smtpPort
     *            smtp服务器端口
     * @param from
     *            发件人地址
     * @param tos
     *            收件人地址
     * @param title
     *            标题
     * @param content
     *            内容
     */
    public void sendmessage(String userName, String passWord, String smtpHost, String smtpPort, String from, String tos,
	    String title, String content) {
	
	Properties smtpProper = setSmtp(smtpHost, smtpPort, userName, passWord);
	Auth authenticator = new Auth(userName, passWord);
	try {

	    // 创建session实例
	    Session session = Session.getInstance(smtpProper, authenticator);
	    MimeMessage message = new MimeMessage(session);
//	    session.setDebug(true);

	    // 设置from发件人邮箱地址
	    message.setFrom(new InternetAddress(from));
	    // 收件人to LIST
	    ArrayList<Address> toList = new ArrayList<Address>();
	    // 收件人字符串通过,号分隔收件人
	    String[] toArray = tos.split(",");
	    for (int i = 0; i < toArray.length; i++) {
		String str = toArray[i];
		toList.add(new InternetAddress(str));
	    }
	    // 将收件人列表转换为收件人数组
	    Address[] addresses = new Address[toList.size()];
	    addresses = toList.toArray(addresses);
	    // 设置to收件人地址
	    message.setRecipients(MimeMessage.RecipientType.TO, addresses);
	    MimeUtility.encodeWord(title, "UTF-8", "Q");
	    // 设置邮件标题
	    message.setSubject(title);
	    
	    Multipart mp = new MimeMultipart();
	    // 向Multipart添加正文
	    MimeBodyPart mbpContent = new MimeBodyPart();
	    mbpContent.setContent(content, "text/html;charset=gb2312");
	    // 向MimeMessage添加（Multipart代表正文）
	    mp.addBodyPart(mbpContent);
	    // 向Multipart添加附件
	    Enumeration<String> efile = file.elements();
	    while (efile.hasMoreElements()) {
		MimeBodyPart mbpFile = new MimeBodyPart();
		filename = efile.nextElement().toString();
		FileDataSource fds = new FileDataSource(filename);
		mbpFile.setDataHandler(new DataHandler(fds));
		String filename = new String(fds.getName().getBytes(), "ISO-8859-1");
		mbpFile.setFileName(filename);
		// 向MimeMessage添加（Multipart代表附件）
		mp.addBodyPart(mbpFile);

	    }
	    file.removeAllElements();

	    // 向Multipart添加MimeMessage
	    message.setContent(mp);
	    message.setSentDate(new Date());
	   
	    
	    // 设置邮件内容
//	    message.setContent(content, "text/html;charset=UTF-8");
	    message.saveChanges();
	    // Transport.send(message);
	    Transport transport = session.getTransport();
	    transport.connect(smtpHost, userName, passWord);
	    transport.sendMessage(message, addresses);
	    // log.info("发送邮件成功！");

	} catch (Exception e) {
	    // TODO: handle exception
	    log.error("发送邮件失败！");
	    e.printStackTrace();
	}

    }

    private Properties setSmtp(String smtpHost, String smtpPort, String userName, String passWord) {
	// 创建邮件配置文件
	Properties maiProperties = new Properties();
	// 配置smtp发件服务器
	maiProperties.put("mail.transport.protocol", "smtp");
	// 配置smtp服务器
	maiProperties.put("mail.smtp.host", smtpHost);
	// 配置smtp服务器端口
	maiProperties.put("mail.smtp.port", smtpPort);
	// 配置smtp用户名
	maiProperties.put("mail.user", userName);
	// 配置smtp身份验证
	maiProperties.put("mail.smtp.auth", "true");
	return maiProperties;

    }

}

// smtp 身份验证类
class Auth extends Authenticator {
    Properties pwdProperties;
    // 构造函数

    public Auth(String userName, String passWord) {
	pwdProperties = new Properties();
	try {
	    pwdProperties.put(userName, passWord);
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }

    // 必须实现 PasswordAuthentication 方法
    public PasswordAuthentication passwordAuthentication() {
	String userName = getDefaultUserName();
	// 当前用户在密码表里
	if (pwdProperties.containsKey(userName)) {
	    // 取出密码，封装好后返回
	    return new PasswordAuthentication(userName, pwdProperties.getProperty(userName).toString());

	} else {
	    // 如果当前用户不在密码表里就返回Null
	    System.out.println("当前用户不存在");
	    return null;

	}

    }

}
