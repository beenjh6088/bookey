package com.bookey.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailController {
	private static String fromID = "beenjh6088@gmail.com";
	private static String fromPW = "dshfgmijtuaqodgh";
	
	public static void sendEmail(String to) {
		// Setting a SMTP server information
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // Activate TLS
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Use TLS 1.2
		
    
    // Setting an Authentication
    Session session = Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(fromID, fromPW);
      }
    });
    session.setDebug(true);
    
		try {
      // writing an Email
      MimeMessage message = new MimeMessage(session);
      String subject = "[Bookey]The Verification for Email";
			String htmlContent = ""
					+ "<style>"
					+ "* {"
					+ "  margin: 0;"
					+ "  padding: 0;"
					+ "  box-sizing: border-box;"
					+ "}"
					+ ""
					+ "header, main {"
					+ "  width: 100%;"
					+ "}"
					+ "header {"
					+ "  height: 120px;"
					+ "  line-height: 120px;"
					+ "  font-size: 28px;"
					+ "  background-color: lightgreen;"
					+ "  text-align: center;"
					+ "}"
					+ "xmain {"
					+ "}"
					+ ".center {"
					+ "  width: 90%;"
					+ "  height: 100%;"
					+ "  margin: 0 auto;"
					+ "  position: relative;"
					+ "}"
					+ "xmain p {"
					+ "  margin: 16px 0;"
					+ "}"
					+ "xmain p:nth-of-type(3) {"
					+ "  color: red;"
					+ "}"
					+ ""
					+ "</style>"
					+ "<html>"
					+ "<body>"
					+ "<header>"
					+ "  <h1 style='background-color: lightgreen; font-size: 28px;'>"
					+ "    "+subject
					+ "  </h1>"
					+ "</header>"
					+ "<main>"
					+ "  <div style='background: linear-gradient(to right, #FBFBFB , #f7fff5);'>"
					+ "    <p style='margin: 16px 0; padding-left: 16px;'>Hey "+to+"</p>"
					+ "    <p style='margin: 16px 0; padding-left: 16px;'>We’re excited to welcome you to Bookey! Before you begin your journey, we need to verify your account. Please copy the below Number and paste to the blank in Join Form</p>"
					+ "    <p style='margin: 16px 0; padding-left: 16px; color: red;'>an Authentication Number : 1234567</p>"
					+ "    <p style='margin: 16px 0; padding-left: 16px;'>After verification, you’ll have access to all the amazing features on Bookey.<br>If you encounter any issues or have questions, our support team is here to help Simply reply to this email or reach out to us at 123-123-1234.</p>"
					+ "    <p style='margin: 16px 0; padding-left: 16px;'>Sincerely,<br>"
					+ "      Bookey</p>"
					+ "  </div>"
					+ "</main>"
					+ "</body>"
					+ "</html>"
					;
      message.setFrom(new InternetAddress(fromID));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      message.setContent(htmlContent, "text/html; charset=utf-8;");
      
      // Sending an email
      Transport.send(message);
      System.out.println("Email sent successfully!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
