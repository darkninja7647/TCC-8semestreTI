/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import static javax.mail.Message.RecipientType.TO;

import javax.mail.MessagingException;

/**
 *
 * @author darkn
 */
//classe para a criacao de um e-mail
public class Email {

    private String to;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //metodo que esta encaminhando um e-mail;

    public void send() throws MessagingException {
        try {
            String from = "pfcpfc38@gmail.com";
            String password = "fywk snwx sdmy wdly";
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587"); // Porta do Gmail
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true"); // Habilitar STARTTLS
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Adicione esta linha para confiar no host

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso.");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar o email: " + e.getMessage());
        }
    }
    //metodo que irá enviar o e-mail da funcao esqueci minha senha 

    public void sendPasswordResetEmail(String email, String resetLink) throws MessagingException {
        setTo(email);
        setSubject("Recuperação de Senha");
        setBody("Clique no link abaixo para redefinir sua senha:\n" + resetLink);
        send();
    }

    // ----------------------------BUILDER-------------------------------------
    public static EmailBuilder getBuilder() {
        return new EmailBuilder();
    }

    public static class EmailBuilder {

        private Email e = new Email();

        public EmailBuilder comTo(String to) {
            e.to = to;
            return this;
        }

        public EmailBuilder comSubject(String subject) {
            e.subject = subject;
            return this;
        }

        public EmailBuilder comBody(String body) {
            e.body = body;
            return this;
        }

        public Email constroi() {
            return e;
        }

    }
}
