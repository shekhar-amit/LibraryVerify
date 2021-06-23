package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class RangeLengthValidator extends AbstractValidator {

    private int mMaxLength, mMinLength;

    public RangeLengthValidator(int minLength, int maxLength) {
        if(minLength < 0)
            throw new IllegalArgumentException("You put a negative min length (" + minLength +")");
        if(maxLength < 0)
            throw new IllegalArgumentException("You put a negative max length (" + maxLength +")");
        if(minLength > maxLength)
            throw new IllegalArgumentException("The max length has to be superior or equal to the min length");

        mMaxLength = maxLength;
        mMinLength = minLength;
        // TODO: Fix string
        mErrorMessage = "This field must contain between "+mMinLength+" and "+mMaxLength+" characters";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_range_length, mMinLength, mMaxLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mMinLength && value.length() <= mMaxLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}