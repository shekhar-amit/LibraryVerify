package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;

public class NumericValidator extends AbstractValidator {

    public NumericValidator() {
        // TODO: Fix string
        mErrorMessage = "This is not a number";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_invalid_number);
    }

    @Override
    public boolean isValid(String value) {
        return isNumeric(value);
    }

    @Override
    public String getmErrorMessage() {
        return mErrorMessage;
    }

    protected boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
