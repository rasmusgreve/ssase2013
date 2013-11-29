/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author cly-vs
 */
public class StringInputValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String)value;
        
        UIInput inputField = (UIInput) component;
        if(input==null||!input.matches("\\w+")) {
            inputField.setValid(false);
            FacesMessage message = new FacesMessage("No special characters allowed");
            context.addMessage(component.getClientId(), message);
        } else
            inputField.setValid(true);
    }
    
}
