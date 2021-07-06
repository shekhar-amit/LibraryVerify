package com.pchmn.libraryverify.validator;

import com.pchmn.libverify.validator.AbstractValidator;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

public abstract class ValidatorTest {

    protected Context mContext;
    protected AbstractValidator mValidator;

    @Before
    public abstract void setUp();

    @Test
    public abstract void validate();

    @Test
    public abstract void message();

}
