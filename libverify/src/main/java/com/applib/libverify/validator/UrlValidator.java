package com.applib.libverify.validator;

import com.applib.libverify.Patterns;

public class UrlValidator extends AbstractValidator {

    public UrlValidator() {
        mErrorMessage = "This url is not valid";
    }

    @Override
    public boolean isValid(String value) {

        return Patterns.WEB_URL.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
