package com.pchmn.libverify.validator;

import java.util.regex.Pattern;

/**
 * Basic unit that validates phone numbers.
 */
public class PhoneNumberValidator extends AbstractValidator {

    private static final Pattern PHONE
            = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?" + "(\\([0-9]+\\)[\\- \\.]*)?" + "([0-9][0-9\\- \\.]+[0-9])");

    public PhoneNumberValidator() {
        mErrorMessage = "This phone number is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return PHONE.matcher(value).matches();
    }


    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
