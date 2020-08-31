package com.example.dante;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.dante.Utils;

import java.io.IOException;
import java.util.Properties;

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

public class JavaMailAPI extends AsyncTask<Void,Void,Void>  {

    //Add those line in dependencies
    //implementation files('libs/activation.jar')
    //implementation files('libs/additionnal.jar')
    //implementation files('libs/mail.jar')

    //Need INTERNET permission

    //Variables
    private Context mContext;
    private Session mSession;

    String f;
    private String mEmail;
    private String mSubject;
    private String mMessage;
    private String[] attachfiles;

    private ProgressDialog mProgressDialog;

    //Constructor
    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String mMessage, String[] attachFiles) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
        this.attachfiles = attachFiles;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email
        mProgressDialog = ProgressDialog.show(mContext,"Sending message", "Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismiss progress dialog when message successfully send
        mProgressDialog.dismiss();

        //Show success toast
        Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(mSession);

            //Setting sender address
            mm.setFrom(new InternetAddress(Utils.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            //Adding subject
            mm.setSubject(mSubject);
            //Adding message
            mm.setText(mMessage);




            //Sending email


            Multipart multipart = new MimeMultipart();

            if (attachfiles != null && attachfiles.length > 0) {
                for (String filePath : attachfiles) {
                    MimeBodyPart attachPart = new MimeBodyPart();

                    try {
                        attachPart.attachFile(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    multipart.addBodyPart(attachPart);

                }
            }
            // Send the complete message parts
            mm.setContent(multipart);

            // Send message;
            Transport.send(mm);


//
//            Multipart multipart = new MimeMultipart();
//
//            multipart.addBodyPart(messageBodyPart);
//
//
//            DataSource source = new FileDataSource(filePath);
//
//            messageBodyPart.setDataHandler(new DataHandler(source));
//
//            messageBodyPart.setFileName(filePath);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}