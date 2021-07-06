package com.pchmn.libraryverify.validator;

import com.pchmn.libverify.validator.RequiredValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class RequiredValidatorTest extends ValidatorTest {

    private RequiredValidator mRequiredValidator;
    private RequiredValidator mNotRequiredValidator;

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mRequiredValidator = new RequiredValidator(true);
        mNotRequiredValidator = new RequiredValidator(false);
    }

    @Override
    public void validate() {
        // required
        assertTrue(mRequiredValidator.isValid("abcd"));
        assertTrue(mRequiredValidator.isValid("123"));
        assertFalse(mRequiredValidator.isValid(""));
        // not required
        assertTrue(mNotRequiredValidator.isValid(""));
        assertTrue(mNotRequiredValidator.isValid("abcd"));
        assertTrue(mNotRequiredValidator.isValid("123"));
    }

    @Override
    public void message() {
        mRequiredValidator.setErrorMessage("required");
        assertEquals(mRequiredValidator.getErrorMessage(), "required");
    }
}