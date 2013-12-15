
package dk.itu.ssase.hb.listener;

import dk.itu.ssase.hb.beans.LoginBean;
import dk.itu.ssase.hb.beans.StudentBean;
import dk.itu.ssase.hb.model.UserSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author cly
 */
public class AuthPhaseListener implements PhaseListener {
    
    private static final String USER_LOGIN_OUTCOME = "login";
    private static final String[] UNPROTECTED_VIEWS = new String[] {
            "/login.xhtml",
            "/signup.xhtml",
            "/userList.xhtml",
            "/profile.xhtml",
            "/alienprofile.xhtml"
        };
    
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
       
        boolean authorized = false;
        if (userExists(context)) {
            // allow processing of the requested view
            authorized = true;
        } else {            
            // send the user to the login view
            if (requestingSecureView(context)) {                
                redirectToLogin(context);
            } else {
                authorized=true;
            }
        }
        if(authorized) {
            return;
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Access denied user");   
            redirectToLogin(context);
        }
    }
    
    public void redirectToLogin(FacesContext context) {
                context.getExternalContext().setResponseStatus(403);
                //context.responseComplete();              
                context.getApplication().
                        getNavigationHandler().handleNavigation(context, 
                                                                null, 
                                                                USER_LOGIN_OUTCOME);
                context.renderResponse();
        
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();  
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(LoginBean.USER_SESSION_KEY));
    }
    
    private boolean userAdmin(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        UserSession user = (UserSession) extContext.getSessionMap().get(LoginBean.USER_SESSION_KEY);
        return user.isAdmin();
    }
    
    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();       
        String path = extContext.getRequestPathInfo();

        for (String unprotected : UNPROTECTED_VIEWS) {
            if(path.equals(unprotected))
                return false;
        }
        return (true);       
    }    
}

