package com.pchmn.libraryverify.validator;

import com.pchmn.libverify.validator.PhoneNumberValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class PhoneNumberValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new PhoneNumberValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("0299123456"));
        assertTrue(mValidator.isValid("+33123456789"));
        assertTrue(mValidator.isValid("600456789"));
        assertTrue(mValidator.isValid("600456"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("123"));
        assertFalse(mValidator.isValid("ab12"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("phone number");
        assertEquals(mValidator.getErrorMessage(), "phone number");
    }
}