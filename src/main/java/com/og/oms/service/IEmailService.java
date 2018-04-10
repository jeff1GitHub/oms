package com.og.oms.service;

import javax.mail.MessagingException;

/**
 *
 * @author jeff
 */
public interface IEmailService {

    /**
     * 发送简单邮件
     *
     * @param target  接收人
     * @param title   邮件标题
     * @param content 邮件内容
     */
    void sendSimpleEmail(String target, String title, String content);


    /**
     * 测试发送附件.(这里发送图片.)
     *
     * @param target
     * @param title
     * @param content
     * @throws MessagingException
     */
    void sendAttachmentsEmail(String target, String title, String content) throws MessagingException;

    /**
     * 邮件中使用静态资源.
     *
     * @throws Exception
     */
    void sendInlineMail(String target, String title, String content) throws MessagingException;
}
