package main.project.flightApplication.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import main.project.flightApplication.Entity.Passenger;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailController {
    
    public void configureEmailProperties(String email) {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.transport.protocol", "smtps");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ensf480group26@gmail.com","bykd buyz isrm ipmb");
                }
        });
        

            Message message = new MimeMessage(session); 
            try {
                // Set the email content
                message.setSubject("Your Flight's Booked! Here's your Ticket");
                message.setText("Thank you for booking with us! Here's your ticket");

                // Attach the ticket file
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File("ticket.txt"));

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(attachmentPart);

                message.setContent(multipart);

                // Set recipient email address
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

                // Send the email
                Transport.send(message);

                System.out.println("Email sent successfully!");
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }

        }

        public void cancellationEmail(String email){
            
        Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.transport.protocol", "smtps");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ensf480group26@gmail.com","bykd buyz isrm ipmb");
                }
        });
        

            Message message = new MimeMessage(session); 
            try {
                // Set the email content
                message.setSubject("We're sorry to see you go!");
                message.setText("Here's your cancellation confimation");

                // Attach the ticket file
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File("ticket.txt"));

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(attachmentPart);

                message.setContent(multipart);

                // Set recipient email address
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

                // Send the email
                Transport.send(message);

                System.out.println("Email sent successfully!");
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }

        }
}

