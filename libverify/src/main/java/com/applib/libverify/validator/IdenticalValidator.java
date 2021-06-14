package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;
import ohos.agp.components.TextField;


public class IdenticalValidator extends AbstractValidator {

    private TextField mOtherTextField;

    public IdenticalValidator(TextField textField) {
        mOtherTextField = textField;
        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_fields_mismatch);
    }

    @Override
    public boolean isValid(String value){
        return value.equals(mOtherTextField.getText().toString());
    }

    @Override
    public String getmErrorMessage() { return  mErrorMessage; }
}
