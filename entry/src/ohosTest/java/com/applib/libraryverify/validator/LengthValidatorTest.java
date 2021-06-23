package com.applib.libraryverify.validator;

import com.applib.libverify.validator.IPValidator;
import com.applib.libverify.validator.MaxLengthValidator;
import com.applib.libverify.validator.MinLengthValidator;
import com.applib.libverify.validator.RangeLengthValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Test;

import static org.junit.Assert.*;

public class LengthValidatorTest extends ValidatorTest {

    private MinLengthValidator mMinLengthValidator;
    private MaxLengthValidator mMaxLengthValidator;
    private RangeLengthValidator mRangeLengthValidator;

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mMinLengthValidator = new MinLengthValidator(2);
        mMaxLengthValidator = new MaxLengthValidator(5);
        mRangeLengthValidator = new RangeLengthValidator(2, 5);
        mValidator = new IPValidator();
    }

    @Override
    public void validate() {
        // min length
        assertTrue(mMinLengthValidator.isValid("abc"));
        assertTrue(mMinLengthValidator.isValid("ab"));
        assertFalse(mMinLengthValidator.isValid(""));
        assertFalse(mMinLengthValidator.isValid("a"));
        // max length
        assertTrue(mMaxLengthValidator.isValid("abcde"));
        assertTrue(mMaxLengthValidator.isValid("abc"));
        assertTrue(mMaxLengthValidator.isValid(""));
        assertFalse(mMaxLengthValidator.isValid("abcdef"));
        // range length
        assertTrue(mRangeLengthValidator.isValid("ab"));
        assertTrue(mRangeLengthValidator.isValid("abcde"));
        assertTrue(mRangeLengthValidator.isValid("abc"));
        assertFalse(mRangeLengthValidator.isValid(""));
        assertFalse(mRangeLengthValidator.isValid("a"));
        assertFalse(mRangeLengthValidator.isValid("abcdef"));
    }

    @Test
    public void illegalArgumentMinLength() {
        // negative min length
        try {
            MinLengthValidator minLengthValidator = new MinLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentMaxLength() {
        // negative max length
        try {
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // max length = 0
        try {
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator(0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentRangeLength() {
        // negative min length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(-1, 0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative min length and max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(-1, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(0, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // min length > max length
        try {
            RangeLengthValidator rangeLengthValidator = new RangeLengthValidator(3, 1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Override
    public void message() {
        // min length
        mMinLengthValidator.setErrorMessage("Min length 2");
        assertEquals(mMinLengthValidator.getErrorMessage(), "Min length 2");
        // max length
        mMaxLengthValidator.setErrorMessage("Max length 5");
        assertEquals(mMaxLengthValidator.getErrorMessage(), "Max length 5");
        // range length
        mRangeLengthValidator.setErrorMessage("Between 2  and 5 characters");
        assertEquals(mRangeLengthValidator.getErrorMessage(), "Between 2  and 5 characters");
    }
}
