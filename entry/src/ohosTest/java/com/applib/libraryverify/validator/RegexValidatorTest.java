package com.applib.libraryverify.validator;

import com.applib.libverify.validator.RegexValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class RegexValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new RegexValidator("^[0-9]{4}$");
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("1"));
        assertTrue(mValidator.isValid("-1"));
        assertTrue(mValidator.isValid("123"));
        assertTrue(mValidator.isValid("1.2"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("abc"));
        assertFalse(mValidator.isValid("12a"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("numeric");
        assertEquals(mValidator.getErrorMessage(), "numeric");
    }
}