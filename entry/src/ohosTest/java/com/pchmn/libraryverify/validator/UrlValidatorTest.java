package com.pchmn.libraryverify.validator;

import com.pchmn.libverify.validator.UrlValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;

import static org.junit.Assert.*;

public class UrlValidatorTest extends ValidatorTest {

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new UrlValidator();
    }

    @Override
    public void validate() {
        assertTrue(mValidator.isValid("www.google.com"));
        assertTrue(mValidator.isValid("google.com"));
        assertTrue(mValidator.isValid("http://www.wp.pl/"));
        assertTrue(mValidator.isValid("http://www.google.com/page/1"));
        assertTrue(mValidator.isValid("https://google.com"));
        assertFalse(mValidator.isValid("htp://google.com"));
        assertFalse(mValidator.isValid("http://google"));
    }

    @Override
    public void message() {
        mValidator.setErrorMessage("url");
        assertEquals(mValidator.getErrorMessage(), "url");
    }
}