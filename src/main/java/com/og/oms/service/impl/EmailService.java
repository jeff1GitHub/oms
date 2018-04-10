package com.og.oms.service.impl;

import com.og.oms.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.email.sender}")
    private String sender;

    @Override
    public void sendSimpleEmail(String target, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);//发送者.
        message.setSubject(title);//邮件主题.
        message.setText(content);//邮件内容.
        message.setTo(target);//接收者.
        mailSender.send(message);//发送邮件
    }

    @Override
    public void sendAttachmentsEmail(String target, String title, String content) throws MessagingException {
        //        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        //        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //
        //        //基本设置.
        //        helper.setFrom(sender);//发送者.
        //        helper.setTo(target);//接收者.
        //        helper.setSubject(title);//邮件主题.
        //        helper.setText(content);//邮件内容.
        //
        //        //org.springframework.core.io.FileSystemResource下的:
        //        //附件1,获取文件对象.
        //        FileSystemResource file1 = new FileSystemResource(new File("D:/test/head/head1.jpg"));
        //        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        //        helper.addAttachment("头像1.jpg", file1);
        //        //附件2
        //        FileSystemResource file2 = new FileSystemResource(new File("D:/test/head/head2.jpg"));
        //        helper.addAttachment("头像2.jpg", file2);
        //
        //        mailSender.send(mimeMessage);
    }

    @Override
    public void sendInlineMail(String target, String title, String content) throws MessagingException {
        //        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //        //基本设置.
        //        helper.setFrom(sender);//发送者.
        //        helper.setTo(target);//接收者.
        //        helper.setSubject(title);//邮件主题.
        //        helper.setText(content);// 邮件内容
        //        // 邮件内容，第二个参数指定发送的是HTML格式
        //        //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        //        helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);
        //
        //        FileSystemResource file = new FileSystemResource(new File("D:/test/head/head1.jpg"));
        //        helper.addInline("head", file);
        //
        //        mailSender.send(mimeMessage);
    }
}
