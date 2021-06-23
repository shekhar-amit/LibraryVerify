package com.applib.libverify.validator;


public abstract class AbstractValidator {

    String mErrorMessage = "The field is not valid";

    public abstract boolean isValid(String value);

    public abstract String getErrorMessage();

    public void setErrorMessage(String message) { mErrorMessage = message; }

}
