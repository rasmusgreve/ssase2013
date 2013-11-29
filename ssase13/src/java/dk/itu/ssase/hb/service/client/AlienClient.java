/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.itu.ssase.hb.dto.alien.UserListDTO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


/**
 *
 * @author cly-vs
 */
public class AlienClient {
    
    
    public void synchronizeWithDatabase() {
        Gson gson = new GsonBuilder().create();
        try {
            URL url = new URL("https://192.237.201.172/ssase13");
            
            HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection();
            urlcon.setSSLSocketFactory(createKeystore());
            urlcon.setHostnameVerifier(new HostnameVerifier()
            {
                public boolean verify(String hostname, SSLSession session)
                {
                    // ip address of the service URL(like.23.28.244.244)
                    if (hostname.equals("192.237.201.172"))
                        return true;
                    return false;
                }
            });
            
            StringBuffer body = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            while(reader.ready()) {
                 body.append(reader.readLine());
            }
            UserListDTO userList = gson.fromJson(body.toString(),UserListDTO.class);
            
        } catch (IOException ex) {
            Logger.getLogger(AlienClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public SSLSocketFactory createKeystore() {
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
