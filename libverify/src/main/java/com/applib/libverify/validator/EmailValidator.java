package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.Patterns;
import com.applib.libverify.ResourceTable;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;

public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
        // TODO: Fix string
        mErrorMessage = "The email is not valid";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_email);
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() { return  mErrorMessage; }
}
