package com.applib.libverify.validator;

public class RangeValueValidator extends AbstractValidator {

    private int mMinValue, mMaxValue;

    public RangeValueValidator(int minValue, int maxValue) {
        if(minValue > maxValue)
            throw new IllegalArgumentException("The max value has to be superior or equal to the min value");

        mMinValue = minValue;
        mMaxValue = maxValue;
        mErrorMessage = "This field must be between "+mMinValue+" and "+mMinValue;
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = "This field must be between "+mMinValue+" and "+mMinValue;
            return d >= mMinValue && d <= mMaxValue;
        }
        catch(NumberFormatException nfe) {
            mErrorMessage = "This field must be between "+mMinValue+" and "+mMinValue;
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
