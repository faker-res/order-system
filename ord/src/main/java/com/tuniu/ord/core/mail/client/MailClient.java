package com.tuniu.ord.core.mail.client;

import java.util.List;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;

public final class MailClient {

    private static final Logger LOG = LoggerFactory.getLogger(MailClient.class);

    private static final JavaMailSender SENDER = JavaMailSenderFactory.getSender();

    private MailClient() {
    }

    public static void send(MailVo mailVo) {
        MimeMessage mimeMsg = SENDER.createMimeMessage();
        try {
            mimeMsg.setFrom(new InternetAddress(mailVo.getFrom()));
            mimeMsg.setRecipients(RecipientType.TO, getAddress(mailVo.getToList()));
            if (!CollectionUtils.isEmpty(mailVo.getCcList())) {
                mimeMsg.setRecipients(RecipientType.CC, getAddress(mailVo.getCcList()));
            }
            mimeMsg.setSubject(mailVo.getTitle());
            mimeMsg.setContent(mailVo.getContent(), mailVo.getType().getValue());
        } catch (MessagingException e) {
            LOG.error("Send email failed.", e);
        }

        send(mimeMsg);
    }

    public static void send(MimeMessage mimeMsg) {
        try {
            SENDER.send(mimeMsg);
        } catch (Exception e) {
            LOG.error("Send email failed.", e);
        }
    }

    private static Address[] getAddress(List<String> addressList) throws AddressException {
        Address[] toAddress = new Address[addressList.size()];
        for (int i = 0; i < addressList.size(); i++) {
            toAddress[i] = new InternetAddress(addressList.get(i));
        }
        return toAddress;
    }

}
