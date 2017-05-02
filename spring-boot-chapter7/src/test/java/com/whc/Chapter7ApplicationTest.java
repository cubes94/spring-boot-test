package com.whc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter7ApplicationTest {

    public static Logger LOGGER = LoggerFactory.getLogger(Chapter7ApplicationTest.class);

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("572091543@qq.com");
        message.setTo("wuhaichao@baozhunniu.com");
        message.setSubject("subject");
        message.setText("text");

        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("572091543@qq.com");
        helper.setTo("wuhaichao@baozhunniu.com");
        helper.setSubject("subject");
        helper.setText("text");

        FileSystemResource file = new FileSystemResource(new File("test.jpg"));
        helper.addAttachment("attach1.jpg", file);
        helper.addAttachment("attach2.jpg", file);

        mailSender.send(mimeMessage);
    }

    @Test
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("572091543@qq.com");
        helper.setTo("wuhaichao@baozhunniu.com");
        helper.setSubject("subject");
        helper.setText("<html><body><img src=\"cid:test\" ></body></html>", true);
        FileSystemResource file = new FileSystemResource(new File("file/test.jpg"));
        helper.addInline("test", file);
        mailSender.send(mimeMessage);
    }

    @Test
    public void sendTemplateMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("572091543@qq.com");
        helper.setTo("wuhaichao@baozhunniu.com");
        helper.setSubject("subject");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "name");

        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File("file"));
        Template template = configuration.getTemplate("template.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }

}
