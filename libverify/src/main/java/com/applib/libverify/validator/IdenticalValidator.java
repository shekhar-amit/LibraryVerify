package com.applib.libverify.validator;

import com.applib.libverify.App;
import com.applib.libverify.ResourceTable;
import ohos.agp.components.TextField;


public class IdenticalValidator extends AbstractValidator {

    private TextField mOtherTextField;

    public IdenticalValidator(TextField textField) {
        mOtherTextField = textField;
        // TODO: Fix string
        mErrorMessage = "The two fields mismatch";
//        mErrorMessage = App.getmContext().getString(ResourceTable.String_error_fields_mismatch);
    }

    @Override
    public boolean isValid(String value){
        if(mOtherTextField.getText()==null)
            if(value==null) return true;
            else return false;
        if(mOtherTextField.getText().length()==0)
            if(value.length()==0) return true;
            else return false;
        return value.equals(mOtherTextField.getText());
    }

    @Override
    public String getmErrorMessage() { return  mErrorMessage; }
}
