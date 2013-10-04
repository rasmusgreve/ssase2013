
package dk.itu.ssase.hb.listener;

import dk.itu.ssase.hb.beans.StudentBean;
import dk.itu.ssase.hb.model.UserSession;
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
    
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
       
        boolean authorized = false;
        if (userExists(context)) {
            // allow processing of the requested view
            if (requestingAdminView(context)) {
                if(userAdmin(context)) {
                   authorized = true; 
                }                     
            } else 
                authorized = true;
        } else {            
            // send the user to the login view
            if (requestingSecureView(context)) {
                
                redirectToLogin(context);
            } else {
                authorized=true;
            }
        }
        if(authorized)
            return;
        else {
            //TODO write access denied
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

    public void beforePhase(PhaseEvent event) {
        // TODO check why session is removed?
        //event.getFacesContext().getExternalContext();
    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(StudentBean.USER_SESSION_KEY));
    }
    
    private boolean userAdmin(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        UserSession user = (UserSession) extContext.getSessionMap().get(StudentBean.USER_SESSION_KEY);
        return user.isAdmin();
    }
    
    private boolean requestingSecureView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();       
        String path = extContext.getRequestPathInfo();
        return (!"/login.xhtml".equals(path) );              
    }
    
    private boolean requestingAdminView(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();       
        String path = extContext.getRequestPathInfo();
        return ("/createUser.xhtml".equals(path) );              
    }
}
