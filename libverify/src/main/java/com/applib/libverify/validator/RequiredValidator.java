package com.applib.libverify.validator;

public class RequiredValidator extends AbstractValidator {

    private boolean mRequired;

    public RequiredValidator(boolean required) {
        mRequired = required;
        mErrorMessage = "This field is required";
    }

    @Override
    public boolean isValid(String value) {
        if(mRequired)
            if (value == null || value.length() == 0) {
                return false;
            } else {
                return true;
            }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String message) {
        mRequired = true;
        mErrorMessage = message;
    }
}