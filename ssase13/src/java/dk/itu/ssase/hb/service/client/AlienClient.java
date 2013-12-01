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
import dk.itu.ssase.hb.util.StudentHibernateUtil;
import java.io.BufferedReader;
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
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author cly-vs
 */
public class AlienClient {
    public static final String API_PATH = "https://192.237.201.172/ssase13/api/";
    
    public void synchronizeWithDatabase() {
        ArrayList<UserDTO> aliens = new ArrayList<UserDTO>(10);
        HashMap<String, AlienUser> alienMap = new HashMap<String, AlienUser>();
        Session session = StudentHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String next = API_PATH + "users/0";
        while (!next.equals("")) {
            UserListDTO userList = getData(next, UserListDTO.class);
            try {
                tx = session.beginTransaction();
                for (String name : userList.usernames) {
                    UserDTO dto = getData(API_PATH + "user/" + name, UserDTO.class);
                    AlienUser user = new AlienUser();
                    user.setName(dto.name);
                    user.setCountry(dto.country);
                    user.setHobbies(dto.hobbies);
                    user.setProfile(dto.profile);
                    session.save(user);
                    aliens.add(dto);
                    alienMap.put(dto.name, user);
                }
                tx.commit();
                session.close();
            } catch (Exception ex) {
                if(tx!=null)
                    tx.rollback();
                session.close();
            } finally {
                tx = null;
            }
            next = userList.next;
        }
        // For all newly added aliens in the DTO list.
        for (UserDTO dto : aliens) {
            // Grab and remove the alien from the ORM map.
            AlienUser user = alienMap.get(dto.name);
            alienMap.remove(dto.name);
            // Get his friends as a list of usernames.
            ArrayList<String> friendList = getData(dto.friends, ArrayList.class);
            for (String friendName : friendList) {
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
                        session.close();
                    } catch (Exception ex) {
                        if(tx!=null)
                            tx.rollback();
                        session.close();
                    } finally {
                        tx = null;
                    }
                }
            }
        }
    }
    
    private <T> T getData(String path, Class<T> type) {
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
            while(reader.ready()) {
                 body.append(reader.readLine());
            }
            T data = gson.fromJson(body.toString(), type);
            return data;
        } catch (IOException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private SSLSocketFactory createKeystore() {
        FileInputStream keyStoreInput = null;
        try {
            keyStoreInput = new FileInputStream("src/conf/team10.jks");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
            try {
                keyStoreInput = new FileInputStream("team10.jks");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex1);
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
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
