/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.itu.ssase.hb.beans.model.AlienRelation;
import dk.itu.ssase.hb.beans.model.AlienUser;
import dk.itu.ssase.hb.dto.alien.UserDTO;
import dk.itu.ssase.hb.dto.alien.UserListDTO;
import dk.itu.ssase.hb.util.RegexConstants;
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author cly-vs
 */
public class AlienClient {
    public static final String API_PATH = "https://192.237.201.172/ssase13/api/";
    public static final String REST_API_PATH = "https://192.237.201.172/ssase13/";
    
    public void synchronizeWithDatabase() {
        //Delete all the synchronized users
        Session deleteSession = StudentHibernateUtil.getSessionFactory().openSession();
                Transaction deltx = null;
                try {
                    deltx = deleteSession.beginTransaction();
                    SQLQuery deleteRelationQuery = deleteSession.createSQLQuery("DELETE FROM alien_relation WHERE 1=1;");
                    SQLQuery deleteUserQuery = deleteSession.createSQLQuery("DELETE FROM alien_user WHERE 1=1;");
                    deleteRelationQuery.executeUpdate();
                    deleteUserQuery.executeUpdate();
                    deltx.commit();
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Delete failed {0}", ex);
                    if(deltx!=null)
                        deltx.rollback();
                } finally {
                    deltx = null;
                    deleteSession.close();
                }
        
        
        
        ArrayList<UserDTO> aliens = new ArrayList<UserDTO>(10);
        HashMap<String, AlienUser> alienMap = new HashMap<String, AlienUser>();
        String next = API_PATH + "users/0";
        do  {
            UserListDTO userList = getData(next, UserListDTO.class);
            for (String name : userList.usernames) {
                Session session = StudentHibernateUtil.getSessionFactory().openSession();
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                        UserDTO dto = getData(API_PATH + "user/" + name, UserDTO.class);
                        AlienUser user = new AlienUser();
                        user.setUsername(name);
                        if (dto.name.matches(RegexConstants.WORDS_REGEX)) user.setName(dto.name); else throw new  Exception("name "+dto.name + " not allowed");
                        if (dto.country.matches(RegexConstants.WORDS_REGEX)) user.setCountry(dto.country); else Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Country invalid: {0}", dto.country);
                        if (dto.hobbies.matches(RegexConstants.WORDS_REGEX)) user.setHobbies(dto.hobbies); else Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Hobby invalid: {0}", dto.hobbies);
                        if (dto.profile.startsWith(REST_API_PATH)) user.setProfile(dto.profile); else Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Profile url invalid: {0}", dto.profile);
                        session.save(user);
                        aliens.add(dto);
                        alienMap.put(dto.name, user);
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "added {0}", dto.name);
                    tx.commit();
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Save of imported user failed {0}", ex);
                    if(tx!=null)
                        tx.rollback();
                } finally {
                    tx = null;
                    session.close();
                }
            }
            if(userList.next!=null)
                next = userList.next;
            else
                next = null;
        } while (next!=null&&!next.equals(""));
        // For all newly added aliens in the DTO list.
        for (UserDTO dto : aliens) {
            // Grab and remove the alien from the ORM map.
            AlienUser user = alienMap.get(dto.name);
            alienMap.remove(dto.name);
            // Get his friends as a list of usernames.
            ArrayList<String> friendList = getData(dto.friends, ArrayList.class);
            for (String friendName : friendList) {
                Session session = StudentHibernateUtil.getSessionFactory().openSession();
                Transaction tx = null;
                // If the friend exists in the ORM map, he has not already been processed,
                // and so we can safely add the bi-directional relationship.
                // If he is not in the ORM map, the relationship must already exist.
                AlienUser friend = alienMap.get(friendName);
                if (friend != null) {
                    try {
                        tx = session.beginTransaction();
                        AlienRelation relationship = new AlienRelation();
                        relationship.setAlienUserByAlien1(user);
                        relationship.setAlienUserByAlien2(friend);
                        session.save(relationship);
                        tx.commit();
                    } catch (Exception ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Save of imported firenship failed {0}", ex);
                        if(tx!=null)
                            tx.rollback();
                    } finally {
                        tx = null;
                    session.close();
                    }
                }
            }
            
        }
    }
    
    public <T> T getData(String path, Class<T> type) {
        Gson gson = new GsonBuilder().create();
        try {
            URL url = new URL(path);
            HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection();
            urlcon.setSSLSocketFactory(createKeystore());
            urlcon.setHostnameVerifier(new HostnameVerifier()
            {
                @Override
                public boolean verify(String hostname, SSLSession session)
                {
                    // ip address of the service URL(like.23.28.244.244)
                    if (hostname.equals("192.237.201.172"))
                        return true;
                    return false;
                }
            });
            StringBuilder body = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                 body.append(line);
            }
            T data = gson.fromJson(body.toString(), type);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private SSLSocketFactory createKeystore() {

        FileInputStream keyStoreInput = null;
        try {
            String filePath = getClass().getResource("/team10.jks").getPath();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Message {0}", filePath);
                keyStoreInput = new FileInputStream(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
            try {
                
            keyStoreInput = new FileInputStream("src/conf/team10.jks");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex1);
            }
        } 
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keyStoreInput, "team10".toCharArray());
            TrustManagerFactory tmf = 
              TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tmf.getTrustManagers(), null);
            return ctx.getSocketFactory();
        } catch (KeyStoreException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
