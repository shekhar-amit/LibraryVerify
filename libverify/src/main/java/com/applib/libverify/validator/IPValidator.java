package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.Patterns;
import com.applib.libverify.ResourceTable;

public class IPValidator extends AbstractValidator {

    public IPValidator() {
        // TODO: Fix string
        mErrorMessage = "The IP address is not valid";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_ip);
    }

    @Override
    public boolean isValid(String value) {
        return Patterns.IP_ADDRESS.matcher(value).matches();
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
