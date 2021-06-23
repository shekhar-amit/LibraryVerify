package com.applib.libverify.validator;

import com.applib.libverify.Patterns;

public class PhoneNumberValidator extends AbstractValidator {

    public PhoneNumberValidator() {
        mErrorMessage = "This phone number is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.PHONE.matcher(value).matches();
    }


    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
