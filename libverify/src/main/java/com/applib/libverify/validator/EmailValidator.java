package com.applib.libverify.validator;

import com.applib.libverify.Patterns;

public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
        mErrorMessage = "The email is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() { return  mErrorMessage; }
}
