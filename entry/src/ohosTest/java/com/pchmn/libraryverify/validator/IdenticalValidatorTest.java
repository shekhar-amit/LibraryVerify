package com.pchmn.libraryverify.validator;

import com.pchmn.libverify.validator.IdenticalValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.TextField;

import static org.junit.Assert.*;

public class IdenticalValidatorTest extends ValidatorTest{

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        TextField editText = new TextField(mContext);
        editText.setText("test identical");
        mValidator = new IdenticalValidator(editText);
    }

    @Override
    public void validate() {
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid(""));
        assertTrue(mValidator.isValid("test identical"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("Fields mismatch");
        assertEquals("Fields mismatch", mValidator.getErrorMessage());
    }
}
