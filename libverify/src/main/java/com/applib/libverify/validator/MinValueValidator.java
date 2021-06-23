package com.applib.libverify.validator;

public class MinValueValidator extends AbstractValidator {

    private int mMinValue;

    public MinValueValidator(int value) {
        mMinValue = value;
        // TODO: Fix string
        mErrorMessage = "The email is not valid";
        mErrorMessage = "This field must be greater than "+mMinValue;
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = "This field must be greater than "+mMinValue;
            return d >= mMinValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = "This field must be greater than "+mMinValue;
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
