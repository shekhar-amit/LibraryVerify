package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class RequiredValidator extends AbstractValidator {

    private boolean mRequired;

    public RequiredValidator(boolean required) {
        mRequired = required;
        // TODO: Fix string
        mErrorMessage = "This field is required";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_field_required);
    }

    @Override
    public boolean isValid(String value) {
        if(mRequired)
            if (value == null || value.length() == 0) {
                return false;
            } else {
                return true;
            }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String message) {
        mRequired = true;
        mErrorMessage = message;
    }
}