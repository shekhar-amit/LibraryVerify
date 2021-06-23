package com.applib.libraryverify.validator;

import com.applib.libverify.validator.EmailValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class EmailValidatorTest extends ValidatorTest{

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new EmailValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("test@gmail.com"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid("test@"));
        assertFalse(mValidator.isValid("@gmail.com"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("Enter a valid email");
        assertEquals(mValidator.getErrorMessage(), "Enter a valid email");
    }
}
