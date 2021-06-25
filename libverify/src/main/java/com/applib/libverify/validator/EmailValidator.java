package com.applib.libverify.validator;

import ohos.utils.CommonPattern;

public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
        mErrorMessage = "The email is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return CommonPattern.getEmailAddress().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() { return  mErrorMessage; }
}
