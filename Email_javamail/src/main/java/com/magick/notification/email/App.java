/*
* Copyright (c) 2013 magick, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of magick.
*  ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with  magick.
*/
package com.magick.notification.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 
 * @author Deepali
 * 
 * This class is responsible for sending the email to specified email id using sendgrid as carrier
 *
 */
public class App {

	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
    private static final String SMTP_AUTH_SendGrid_USER = "sendgrid username";
    private static final String SMTP_AUTH_SendGrid_PWD  = "sendgrid password";
	public static void main(String[] args) throws MessagingException {
		// TODO Auto-generated method stub
		new App().smtpCheck();
	}
	/**
	 * stmpCheck - It is used to set the message,authentication, multimime, creating and closing the connection
	 * @throws MessagingException
	 */
	private void smtpCheck() throws MessagingException {
		
		// setting the properties for smtp in order to enable tls and authentication
		Properties properties=new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		
		 Authenticator auth = new SMTPAuthentication();
		 
	     Session session = Session.getDefaultInstance(properties, auth);
	     Transport trans = session.getTransport();

	        MimeMessage message = new MimeMessage(session);
	        // creating multipart object inorder to send multiple type of text
	        Multipart multipart = new MimeMultipart();
	        
	        //simple plain text
	        BodyPart part1 = new MimeBodyPart();
	        part1.setDescription("description about the multipart 1st part");
	        part1.setText("thanx for reading the multipart 1st part message");
	        
	        //text html formate
	        BodyPart part2 = new MimeBodyPart();
	        part1.setDescription("description about the multipart 2st part");
	        part2.setContent("<h3>thanx for reading the multipart 1st part message</h3>", "text/html");
	        
	        multipart.addBodyPart(part1);
	        multipart.addBodyPart(part2);

	        // setting the content, from,to subject and date when message has been sent
	        message.setContent(multipart);
	        message.setFrom(new InternetAddress("from@example.com"));
	        message.setSubject("Subject of code");
	        message.setSentDate(new Date());
	        message.setText("this is text for just for check");
	        message.addRecipient(Message.RecipientType.TO,
	             new InternetAddress("to6@example.com"));
	        message.addRecipient(Message.RecipientType.BCC,
		             new InternetAddress("Bcc@example.com"));
	        message.addRecipient(Message.RecipientType.CC,
		             new InternetAddress("CC@example.com"));
	        
	        //Transport object is used to make the connection and sending the message to the specified recipient
	        trans.connect();
	        trans.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        trans.close();

		
	}
/**
 * 
 * @author Deepali
 * This class is responsible for authenticating the username and password by using built in class Authenticator
 *
 */
	
	private class SMTPAuthentication extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_SendGrid_USER;
           String password = SMTP_AUTH_SendGrid_PWD;
           return new PasswordAuthentication(username, password);
        }
        
    }
}
