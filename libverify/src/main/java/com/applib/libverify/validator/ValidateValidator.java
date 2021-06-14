package com.applib.libverify.validator;

public class ValidateValidator extends AbstractValidator {

    public ValidateValidator() {
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
