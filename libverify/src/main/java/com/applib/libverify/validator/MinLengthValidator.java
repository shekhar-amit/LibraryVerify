package com.applib.libverify.validator;


public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    public MinLengthValidator(int length) {
        if(length < 0)
            throw new IllegalArgumentException("You put a negative min length (" + length +")");
        mLength = length;
        mErrorMessage = "This field can contain " + mLength +" characters maximum";
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
