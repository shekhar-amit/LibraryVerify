package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class RangeValueValidator extends AbstractValidator {

    private int mMinValue, mMaxValue;

    public RangeValueValidator(int minValue, int maxValue) {
        if(minValue > maxValue)
            throw new IllegalArgumentException("The max value has to be superior or equal to the min value");

        mMinValue = minValue;
        mMaxValue = maxValue;
        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_range_value, mMinValue, mMaxValue);
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_range_value, mMinValue, mMaxValue);
            return d >= mMinValue && d <= mMaxValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_number);
            return false;
        }
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
