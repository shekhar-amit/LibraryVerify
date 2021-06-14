package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    public MinLengthValidator(int length) {
        if(length < 0)
            throw new IllegalArgumentException("You put a negative min length (" + length +")");
        mLength = length;
        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_min_length, mLength);
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mLength;
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
