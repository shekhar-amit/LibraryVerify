package com.applib.libverify.validator;

import com.applib.libverify.Patterns;

public class IPValidator extends AbstractValidator {

    public IPValidator() {
        mErrorMessage = "The IP address is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.IP_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
