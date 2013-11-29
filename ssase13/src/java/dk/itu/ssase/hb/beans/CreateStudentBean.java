/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

import dk.itu.ssase.hb.beans.model.Student;
import dk.itu.ssase.hb.util.PasswordUtil;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author cly
 */
public class CreateStudentBean {

    private Student studentInput;
    private static final String RECAPTCHA_PRIVATE_KEY = "6LcgNeoSAAAAAKHE_wko4VWRFTHz-izejV6VsIun";

    public CreateStudentBean() {
        studentInput = new Student();
    }

    public String createUser() {
        Session session = StudentHibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
             
            Student studentToSave = getStudentInput();

            if (!validateCaptcha())
            {
                FacesContext.getCurrentInstance().addMessage("createuser:handle", new FacesMessage("CAPTCHA invalid"));
                if(tx!=null)
                    tx.rollback();
                session.close();
                return "fail";
            }
            
            String salt = PasswordUtil.generateSalt();
            studentToSave.setSalt(salt);
            studentToSave.setPassword(PasswordUtil.hashPassword(getStudentInput().getPassword(), salt));
       
            if(studentToSave.getIsadmin()==null)
                studentToSave.setIsadmin(Boolean.FALSE);
            
            studentToSave.setIssuspended(Boolean.FALSE);
            session.save(studentToSave);
            tx.commit();          
            session.close();      
        } catch(ConstraintViolationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "User exists " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("createuser:handle", new FacesMessage("Handle exists"));
            if(tx!=null)
                tx.rollback();
            session.close();
            return "fail";
        
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Query failed with exception " + ex.getMessage());
            if(tx!=null)
                tx.rollback();
            session.close();
            return "fail";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User created"));
        return "success";
    }
    
    private boolean validateCaptcha() throws IOException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String ip = httpServletRequest.getRemoteAddr();
        String challenge = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recaptcha_challenge_field");
        String response = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recaptcha_response_field");

        URL url = new URL("http://www.google.com/recaptcha/api/verify");
        URLConnection urlcon = url.openConnection();
        urlcon.setDoInput(true);
        urlcon.setDoOutput(true);
        urlcon.setUseCaches(false);

        DataOutputStream dos = new DataOutputStream(urlcon.getOutputStream());
        String content = "privatekey=" + RECAPTCHA_PRIVATE_KEY +
                         "&remoteip=" + ip +
                         "&challenge=" + URLEncoder.encode(challenge, "UTF-8") +
                         "&response=" + URLEncoder.encode(response, "UTF-8");
        dos.writeBytes(content);
        dos.flush();
        dos.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        String result = br.readLine().toLowerCase();
        br.close();
        
        return result.startsWith("true");
    }
    
    public void validateInputString(FacesContext context, UIComponent toValidate, Object value) {
        String input = (String)value;
        
        UIInput inputField = (UIInput) toValidate;
        if(input==null||!input.matches("\\w+")) {
            inputField.setValid(false);
            FacesMessage message = new FacesMessage("No special characters allowed");
            context.addMessage(toValidate.getClientId(), message);
        } else
            inputField.setValid(true);
    }
    
    /**
     * @return the studentInput
     */
    public Student getStudentInput() {
        return studentInput;
    }

    /**
     * @param studentInput the studentInput to set
     */
    public void setStudentInput(Student studentInput) {
        this.studentInput = studentInput;
    }
}
