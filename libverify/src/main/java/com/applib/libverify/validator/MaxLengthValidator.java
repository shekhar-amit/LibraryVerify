package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class MaxLengthValidator extends AbstractValidator {

    private int mLength;

    public MaxLengthValidator(int length) {
        if(length < 0)
            throw new IllegalArgumentException("You put a negative max length (" + length +")");
        if(length == 0)
            throw new IllegalArgumentException("Max length cannot be equal to zero");
        mLength = length;
        // TODO: Fix string
        mErrorMessage = "This field can contain " + mLength +" characters maximum";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_max_length, mLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() <= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}