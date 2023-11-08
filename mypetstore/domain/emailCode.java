package csu.web.mypetstore.domain;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class emailCode {
    private String eid;
    private String title;
    private String content;
    private Integer state;
    private String sendTime;
    private String senderId;
    private String receiverId;
    public String getEid(){
        return this.eid;
    }
    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
    public String getSendTime(){
        return this.sendTime;
    }
    public String getSenderId(){
        return this.senderId;
    }
    public String getReceiverId(){
        return this.receiverId;
    }
    public void setEid(String eid){
        this.eid=eid;
    }
    public void setState(Integer state){
        this.state=state;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){
        this.content=content;
    }
    public void setSendTime(String sendtime){
        this.sendTime=sendtime;
    }
    public void setSenderId(String id){
        this.senderId=id;
    }
    public void setReceiverId(String receiverId){
        this.receiverId=receiverId;
    }
    /*
    public void sendTrueEmail(emailCode email){
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        //smtp登陆的账号、密码 ；需开启smtp登陆
        props.put("mail.user", "xxxxxxx@qq.com");
        // 访问SMTP服务时需要提供的密码,不是邮箱登陆密码，一般都有独立smtp的登陆密码
        props.put("mail.password", "xxxxxxxxxx");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try {
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            // 设置收件人
            InternetAddress to = new InternetAddress(email.getAddress());
            message.setRecipient(RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(email.getTitle());
            // 设置邮件的内容体
            message.setContent(email.getContent(), "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
*/
    }

