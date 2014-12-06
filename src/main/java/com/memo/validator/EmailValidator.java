package com.memo.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.memo.interpolator.MessageInterpolator;
import com.memo.utils.Const;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(Const.EMAIL_PATTERN);

    @Override
    public void validate(FacesContext facesContext, UIComponent component,
            Object value) throws ValidatorException {
        try {
            String email = (String) value;
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                throw new ValidatorException(getMessage());
            }
        } catch (Exception e) {
            throw new ValidatorException(getMessage());
        }
    }

    private FacesMessage getMessage() {
        FacesMessage msg = new FacesMessage(MessageInterpolator.instance()
                .interpolate("email_validation_error"));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }

}
