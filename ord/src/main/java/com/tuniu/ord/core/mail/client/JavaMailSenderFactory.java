package com.tuniu.ord.core.mail.client;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.core.init.SystemInitParameter;

/**
 * @author fangzhongwei
 * 
 */
public final class JavaMailSenderFactory {

    private static JavaMailSender sender = null;

    private JavaMailSenderFactory() {
    }

    public static JavaMailSender getSender() {
        if (null == sender) {
            synchronized (JavaMailSenderFactory.class) {
                if (null == sender) {
                    sender = createSender();
                }
            }
        }
        return sender;
    }

    private static JavaMailSender createSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        init(sender);
        return sender;
    }

    private static void init(JavaMailSenderImpl sender) {
        sender.setHost(SystemInitParameter.mailHost);
        sender.setUsername(SystemInitParameter.mailUserName);
        sender.setPassword(SystemInitParameter.mailPassword);
        sender.setDefaultEncoding(Constants.CHARACTER_DEFAULT_ENCODING);
        sender.setProtocol(SystemInitParameter.mailProtocol);
        sender.setPort(SystemInitParameter.mailPort);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", Boolean.TRUE);
        javaMailProperties.put("mail.smtp.host", SystemInitParameter.mailRobot);
        javaMailProperties.put("mail.smtp.timeout", SystemInitParameter.mailTimeout);
        sender.setJavaMailProperties(javaMailProperties);
    }

}
