package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public abstract class AbstractValidator {

    String mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_field);

    public abstract boolean isValid(String value);

    public abstract String getmErrorMessage();

    public void setmErrorMessage(String message) { mErrorMessage = message; }

}
