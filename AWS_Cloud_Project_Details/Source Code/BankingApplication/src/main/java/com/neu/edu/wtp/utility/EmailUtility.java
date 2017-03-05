package com.neu.edu.wtp.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.neu.edu.wtp.pojo.Transaction;

public class EmailUtility {

	public boolean sendEmail(String toEmail,Transaction transaction) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("aed.project.application@gmail.com", "AedProjectMail@2015"));
		email.setSSLOnConnect(true);
		
		StringBuffer subject=new StringBuffer();
		subject.append("Transaction Id : "+ transaction.getTransactionId() + " Transaction made on your account.");
		
		StringBuffer content=new StringBuffer();
		content.append("Dear Customer,\n\n");
		content.append("An amount of $"+transaction.getAmount()+" has been debited from your account.\nYou can view statement online for further details\n");
		content.append("In case your did not intiate the transaction,please  report immediately to the nearest bank.\n\n");
		content.append("Thank you for your patronage.\nNortheastern Bank");
		try{
			email.setFrom("aed.project.application@gmail.com");
			email.setSubject(subject.toString());
			email.setMsg(content.toString());
			email.addTo(toEmail);//8572509273@tmomail.com
			email.send();
			
		}catch(EmailException ee){
			ee.printStackTrace();
			return false;
		}
		return true;
	}
}
