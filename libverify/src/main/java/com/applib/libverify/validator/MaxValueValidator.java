package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class MaxValueValidator extends AbstractValidator {

    private int mMaxValue;

    public MaxValueValidator(int value) {
        mMaxValue = value;
        // TODO: Fix string
        mErrorMessage = "This field must be less than "+mMaxValue;
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_max_value, mMaxValue);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = "This field must be less than "+mMaxValue;
//            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_max_value, mMaxValue);
            return d <= mMaxValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = "This field must be less than "+mMaxValue;
//            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_max_value);
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
