package com.hotel.email.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hotel.email.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	@Value("${email_address}")
	private String myEmailAccount;
	@Value("${email_key}")
	private String myEmailPassword;
	@Value("${email_host}")
	private String myEmailSMTPHost;
	@Value("${email_charset}")
	private String charset;
	@Value("${email_sendername}")
	private String sendername;

	@Override
	public boolean sendSimpleTextEmailTo(String to, String content, String subject) {
		try {
			Properties props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的
			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
			// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
			// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
			// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
			/*
			 * // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, //
			 * 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助, //
			 * QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看) final String smtpPort =
			 * "465"; props.setProperty("mail.smtp.port", smtpPort);
			 * props.setProperty("mail.smtp.socketFactory.class",
			 * "javax.net.ssl.SSLSocketFactory");
			 * props.setProperty("mail.smtp.socketFactory.fallback", "false");
			 * props.setProperty("mail.smtp.socketFactory.port", smtpPort);
			 */
			Session session = Session.getInstance(props);
			session.setDebug(true);

			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(session, myEmailAccount, to, subject, content);

			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();

			transport.connect(myEmailAccount, myEmailPassword);

			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
			// 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendHtmlTextEmailTo(String to, String html, String subject) {
		return sendSimpleTextEmailTo(to, html, subject);
	}

	private MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject,
			String content) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(sendMail, sendername, charset));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "用户", charset));

		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject(subject, charset);

		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
		message.setContent(content, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}
}
