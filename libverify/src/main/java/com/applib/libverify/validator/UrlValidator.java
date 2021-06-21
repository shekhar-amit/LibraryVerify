package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.Patterns;
import com.applib.libverify.ResourceTable;

public class UrlValidator extends AbstractValidator {

    public UrlValidator() {
        // TODO: Fix string
        mErrorMessage = "This url is not valid";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_url);
    }

    @Override
    public boolean isValid(String value) {

        return Patterns.WEB_URL.matcher(value).matches();
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }
}
