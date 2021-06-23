package com.applib.libraryverify.validator;

import com.applib.libverify.validator.IPValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class IPValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new IPValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("91.198.174.225"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("91.198.174"));
        assertFalse(mValidator.isValid(".198.174.225"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("IP not valid");
        assertEquals(mValidator.getErrorMessage(), "IP not valid");
    }
}
