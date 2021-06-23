package com.applib.libverify.validator;

import ohos.agp.components.TextField;


public class IdenticalValidator extends AbstractValidator {

    private TextField mOtherTextField;

    public IdenticalValidator(TextField textField) {
        mOtherTextField = textField;
        mErrorMessage = "The two fields mismatch";
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
    public String getErrorMessage() { return  mErrorMessage; }
}
