package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.Patterns;
import com.applib.libverify.ResourceTable;

public class PhoneNumberValidator extends AbstractValidator {

    public PhoneNumberValidator() {
        // TODO: Fix string
        mErrorMessage = "This phone number is not valid";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_phone_number);
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.PHONE.matcher(value).matches();
    }


    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
