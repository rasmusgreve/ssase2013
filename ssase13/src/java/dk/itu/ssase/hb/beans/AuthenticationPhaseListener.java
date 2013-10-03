/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.beans;

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
public class AuthenticationPhaseListener implements PhaseListener {
    
    /**
     * <p>The outcome to trigger navigation to the login page.</p>
     */
    private static final String USER_LOGIN_OUTCOME = "login";
       
    // ---------------------------------------------- Methods from PhaseListener

    /**
     * <p>Determines if the user is authenticated.  If not, direct the
     * user to the login view, otherwise all the user to continue to the
     * requested view.</p>
     *
     * <p>Implementation Note: We do this in the <code>afterPhase</code>
     * to make use of the <code>NavigationHandler</code>.</p>
     */
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
                context.getExternalContext().setResponseStatus(403);
                //context.responseComplete();              
                context.getApplication().
                        getNavigationHandler().handleNavigation(context, 
                                                                null, 
                                                                USER_LOGIN_OUTCOME);
                context.renderResponse();
            } else {
                authorized=true;
            }
        }
        if(authorized)
            return;
        else
            context.responseComplete();
    }

    /**
     * <p>This is a no-op.</p>
     */
    public void beforePhase(PhaseEvent event) {
        event.getFacesContext().getExternalContext();
    }

    /**
     * @return <code>PhaseId.RESTORE_VIEW</code>
     */
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    // --------------------------------------------------------- Private Methods       
    
    /**
     * 
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the user has been authenticated, otherwise
     *  <code>false</code>
     */
    private boolean userExists(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        return (extContext.getSessionMap().containsKey(StudentBean.USER_SESSION_KEY));
    }
    
    private boolean userAdmin(FacesContext context) {
        ExternalContext extContext = context.getExternalContext();
        UserSession user = (UserSession) extContext.getSessionMap().get(StudentBean.USER_SESSION_KEY);
        return user.isAdmin();
    }
    
    /**
     * <p>Determines if the requested view is one of the login pages which will
     * allow the user to access them without being authenticated.</p>
     *
     *
     * @param context the <code>FacesContext</code> for the current request
     * @return <code>true</code> if the requested view is allowed to be accessed
     *  without being authenticated, otherwise <code>false</code>
     */
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

