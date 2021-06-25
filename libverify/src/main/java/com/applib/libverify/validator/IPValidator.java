package com.applib.libverify.validator;

import ohos.utils.CommonPattern;

public class IPValidator extends AbstractValidator {

    public IPValidator() {
        mErrorMessage = "The IP address is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return CommonPattern.getIpAddress().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
