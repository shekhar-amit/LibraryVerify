package com.applib.libverify.validator;

public class NumericValidator extends AbstractValidator {

    public NumericValidator() {
        mErrorMessage = "This is not a number";
    }

    @Override
    public boolean isValid(String value) {
        return isNumeric(value);
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    protected boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
