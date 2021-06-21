package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class MinValueValidator extends AbstractValidator {

    private int mMinValue;

    public MinValueValidator(int value) {
        mMinValue = value;
        // TODO: Fix string
        mErrorMessage = "The email is not valid";
        mErrorMessage = "This field must be greater than "+mMinValue;
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_min_value, mMinValue);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = "This field must be greater than "+mMinValue;
//            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_min_value, mMinValue);
            return d >= mMinValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = "This field must be greater than "+mMinValue;
//            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_min_value);
            return false;
        }
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
