package com.applib.libverify;

import com.applib.libverify.validator.*;
import ohos.agp.components.*;
import ohos.agp.text.Layout;
import ohos.app.Context;
import ohos.hiviewdfx.Debug;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.HashMap;
import java.util.Map;

public class InputValidator extends DependentLayout implements ComponentContainer.EstimateSizeListener,
        ComponentContainer.ArrangeListener {

    private int xx = 0;

    private int yy = 0;

    private int maxWidth = 0;

    private int maxHeight = 0;

    private int lastHeight = 0;

    // Layout data for the child component identified by the associated index
    private final Map<Integer, Layout> axis = new HashMap<>();

    private static class Layout {
        int positionX = 0;
        int positionY = 0;
        int width = 0;
        int height = 0;
    }

    private void invalidateValues() {
        xx = 0;
        yy = 0;
        maxWidth = 0;
        maxHeight = 0;
        axis.clear();
    }

    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");
    private static final String TAG = InputValidator.class.toString();
    // context
    private Context mContext;
    // const
    public static final int IS_EMAIL = 0, IS_PHONE_NUMBER = 1, IS_NUMERIC = 2, IS_URL = 3, IS_IP = 4;
    private static final int NONE = -1;
    // validator
    private AbstractValidator mValidator = new ValidateValidator();
    private RequiredValidator mRequiredValidator;
    // errors
    private boolean mShowError = true;
    // edit text
    private TextField mEditText;
    private TextField mOtherEditText;
    // attributes
    private boolean mRequired = false;
    private int mValidatorNumber = NONE;
    private int mMinLength = NONE, mMaxLength = NONE;
    private int mMinValue = NONE, mMaxValue = NONE;
    private String mRegex;
    private int mOtherEditTextId = NONE;
    private String mErrorMessage, mRequiredMessage;
    // build
    private boolean mBuilt = false;

    public InputValidator(Context context) {
        super(context);
        mContext = context;
        try {
            init(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HiLog.debug(LABEL,"REACHED HERE BASIC");
        setEstimateSizeListener(this);
        setArrangeListener(this);
    }

    public InputValidator(Context context, AttrSet attrs) {
        super(context, attrs);
        mContext = context;
        try {
            init(attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setEstimateSizeListener(this);
        setArrangeListener(this);
    }

    /**
     * Init the view with appropriate attributes
     *
     * @param attrs the attributes
     */
    private void init(AttrSet attrs) {
        // attributes
        if (attrs != null) {
            this.mRequired = attrs.getAttr("required").isPresent() ? attrs.getAttr(
                    "required").get().getBoolValue() : false;
            this.mValidatorNumber = attrs.getAttr("validator").isPresent() ? attrs.getAttr(
                    "validator").get().getIntegerValue() : NONE;
            this.mMinLength = attrs.getAttr("minLength").isPresent() ? attrs.getAttr(
                    "minLength").get().getIntegerValue() : NONE;
            this.mMaxLength = attrs.getAttr("maxLength").isPresent() ? attrs.getAttr(
                    "maxLength").get().getIntegerValue() : NONE;
            this.mMinValue = attrs.getAttr("minValue").isPresent() ? attrs.getAttr(
                    "minValue").get().getIntegerValue() : NONE;
            this.mMaxValue = attrs.getAttr("maxValue").isPresent() ? attrs.getAttr(
                    "maxValue").get().getIntegerValue() : NONE;
            this.mRegex = attrs.getAttr("regex").isPresent() ? attrs.getAttr(
                    "regex").get().getStringValue() : null;
            this.mOtherEditTextId = attrs.getAttr("identicalAs").isPresent() ? attrs.getAttr(
                    "identicalAs").get().getIntegerValue() : NONE;
            this.mRegex = attrs.getAttr("regex").isPresent() ? attrs.getAttr(
                    "regex").get().getStringValue() : null;
            this.mErrorMessage = attrs.getAttr("errorMessage").isPresent() ? attrs.getAttr(
                    "errorMessage").get().getStringValue() : null;
            this.mRequiredMessage = attrs.getAttr("requiredMessage").isPresent() ? attrs.getAttr(
                    "requiredMessage").get().getStringValue() : null;
        }
    }

    private void addChild(Component component, int id, int layoutWidth) {
        Layout layout = new Layout();
        layout.positionX = xx + component.getMarginLeft();
        layout.positionY = yy + component.getMarginTop();
        layout.width = component.getEstimatedWidth();
        layout.height = component.getEstimatedHeight();
        if ((xx + layout.width) > layoutWidth) {
            xx = 0;
            yy += lastHeight;
            lastHeight = 0;
            layout.positionX = xx + component.getMarginLeft();
            layout.positionY = yy + component.getMarginTop();
        }
        axis.put(id, layout);
        lastHeight = Math.max(lastHeight, layout.height + component.getMarginBottom());
        xx += layout.width + component.getMarginRight();
        maxWidth = Math.max(maxWidth, layout.positionX + layout.width);
        maxHeight = Math.max(maxHeight, layout.positionY + layout.height);
    }

    @Override
    public boolean onEstimateSize(int widthEstimatedConfig, int heightEstimatedConfig) {

        // Notify child components in the container component to perform measurement.
        measureChildren(widthEstimatedConfig, heightEstimatedConfig);
        int width = Component.EstimateSpec.getSize(widthEstimatedConfig);

        // Associate the index of the child component with its layout data.
        for (int idx = 0; idx < getChildCount(); idx++) {
            Component childView = getComponentAt(idx);
            addChild(childView, idx, width);
        }

        setEstimatedSize(
                Component.EstimateSpec.getChildSizeWithMode(maxWidth, widthEstimatedConfig, 0),
                Component.EstimateSpec.getChildSizeWithMode(maxHeight, heightEstimatedConfig, 0));
        return true;
    }

    private void measureChildren(int widthEstimatedConfig, int heightEstimatedConfig) {
        for (int idx = 0; idx < getChildCount(); idx++) {
            Component childView = getComponentAt(idx);
            if (childView != null) {
                measureChild(childView, widthEstimatedConfig, heightEstimatedConfig);
            }
        }
    }

    private void measureChild(Component child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        ComponentContainer.LayoutConfig lc = child.getLayoutConfig();
        int childWidthMeasureSpec = EstimateSpec.getChildSizeWithMode(
                lc.width, parentWidthMeasureSpec, EstimateSpec.UNCONSTRAINT);
        int childHeightMeasureSpec = EstimateSpec.getChildSizeWithMode(
                lc.height, parentHeightMeasureSpec, EstimateSpec.UNCONSTRAINT);
        child.estimateSize(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    //TODO: Add onLayout
    @Override
    public boolean onArrange(int left, int top, int width, int height) {

        // Arrange child components.
        for (int idx = 0; idx < getChildCount(); idx++) {
            Component childView = getComponentAt(idx);
            Layout layout = axis.get(idx);
            if (layout != null) {
                childView.arrange(layout.positionX, layout.positionY, layout.width, layout.height);
            }
        }
        HiLog.debug(LABEL,"REACHED HERE");
        mEditText = (TextField) getComponentAt(0);
        buildValidator();
        return true;
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.on(changed, l, t, r, b);
//        // get edit text
//        int childCount = getChildCount();
//        // only one edit text per input validator
//        if(childCount == 0 || childCount > 1)
//            try {
//                throw new Exception("InputValidator must contain only one EditText");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        mEditText = (EditText) getChildAt(0);
//
//        // build validator
//        buildValidator();
//    }

    /**
     * Build the validator according to the attributes
     */
    private void buildValidator() {

        // get views
        getOtherEditText();

        // required
        mRequiredValidator = new RequiredValidator(mRequired);

        // length
        if(mMaxLength != NONE && mMinLength != NONE) mValidator = new RangeLengthValidator(mMinLength, mMaxLength);
        else if(mMinLength != NONE) mValidator = new MinLengthValidator(mMinLength);
        else if(mMaxLength != NONE) mValidator = new MaxLengthValidator(mMaxLength);

        // value
        if(mMaxValue != NONE && mMinValue != NONE) mValidator = new RangeValueValidator(mMinValue, mMaxValue);
        else if(mMinValue != NONE) mValidator = new MinValueValidator(mMinValue);
        else if(mMaxValue != NONE) mValidator = new MaxValueValidator(mMaxValue);

        // validator
        setValidator(mValidatorNumber);

        // regex
        if(mRegex != null) mValidator = new RegexValidator(mRegex);

        // custom messages
        if(mErrorMessage != null) mValidator.setmErrorMessage(mErrorMessage);
        if(mRequiredMessage != null) mRequiredValidator.setmErrorMessage(mRequiredMessage);

        // mBuilt
        mBuilt = true;

//        setEstimateSizeListener(this);
//        setArrangeListener(this);
    }


    /**
     * Get the other EditText to compare with
     * Only if the attributes identicalAs is present
     */
    private void getOtherEditText() {
        // get other edit text
        if(mOtherEditText != null) {
            mValidator = new IdenticalValidator(mOtherEditText);
        }
        else if(mOtherEditTextId != NONE) {
            mOtherEditText = (TextField) findComponentById(mOtherEditTextId);
            mValidator = new IdenticalValidator(mOtherEditText);
        }
    }

    /**
     * Set the validator according to the validator type
     *
     * @param validator the validator type
     */
    private void setValidator(int validator) {
        switch (validator) {
            case IS_EMAIL:
                mValidator = new EmailValidator();
                break;
            case IS_PHONE_NUMBER:
                mValidator = new PhoneNumberValidator();
                break;
            case IS_IP:
                mValidator = new IPValidator();
                break;
            case IS_URL:
                mValidator = new UrlValidator();
                break;
            case IS_NUMERIC:
                if(mMinValue == NONE && mMaxValue == NONE)
                    mValidator = new NumericValidator();
                break;
        }
    }

    /**
     * Check if the input is valid
     *
     * @return true of it is valid, false either
     */
    public boolean isValid() {
        // build validator
        if(!mBuilt)
            buildValidator();

        String value = mEditText.getText();

        // reset error
//        mEditText.setError(null);

        // test requirement
        if(!mRequiredValidator.isValid(value)) {
//            if(mShowError) mEditText.setError(mRequiredValidator.getErrorMessage());
            return false;
        }

        // test validity
        if(value!=null && !value.isEmpty() && !mValidator.isValid(value)) {
//            if(mShowError) {
//                mEditText.setError(mValidator.getErrorMessage());
//                if(mValidator instanceof IdenticalValidator)
//                    mOtherEditText.setError(mValidator.getErrorMessage());
//            }
            return false;
        }

        return true;
    }

    public void setCustomValidator(AbstractValidator validator) {
        mValidator = validator;
    }

    public void setEditText(TextField editText) {
        mEditText = editText;
    }

    public void setShowError(boolean show) {
        mShowError = show;
    }

    public void setRequired(boolean mRequired) {
        this.mRequired = mRequired;
    }

    public void setValidatorType(int mValidatorNumber) {
        this.mValidatorNumber = mValidatorNumber;
    }

    public void setMinLength(int mMinLength) {
        this.mMinLength = mMinLength;
    }

    public void setMaxLength(int mMaxLength) {
        this.mMaxLength = mMaxLength;
    }

    public void setMinValue(int mMinValue) {
        this.mMinValue = mMinValue;
    }

    public void setMaxValue(int mMaxValue) {
        this.mMaxValue = mMaxValue;
    }

    public void setRegex(String mRegex) {
        this.mRegex = mRegex;
    }

    public void setIdenticalAs(int mOtherEditTextId) {
        this.mOtherEditTextId = mOtherEditTextId;
    }

    public void setOtherEditText(TextField mOtherEditText) {
        this.mOtherEditText = mOtherEditText;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public void setRequiredMessage(String mRequiredMessage) {
        this.mRequiredMessage = mRequiredMessage;
    }

    /**
     * Builder class for InputValidator
     */
    public static class Builder {
        private Context context;
        private boolean required = false;
        private AbstractValidator validator = new ValidateValidator();
        private int validatorType = NONE;
        private int minLength = NONE, maxLength = NONE;
        private int minValue = NONE, maxValue = NONE;
        private String regex;
        private int otherEditTextId = NONE;
        private TextField otherEditText;
        private String errorMessage, requiredMessage;
        private boolean showError = true;
        private TextField editText;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Whether the field is required or not
         *
         * @param required true if required, false either
         * @return the Builder
         */
        public Builder required(boolean required) {
            this.required = required;
            return this;
        }

        /**
         * Set a custom validator
         *
         * @param validator the validator
         * @return the Builder
         */
        public Builder customValidator(AbstractValidator validator) {
            this.validator = validator;
            return this;
        }

        /**
         * Set the validator type
         * IS_EMAIL, IS_PHONE_NUMBER, IS_NUMERIC, IS_URL or IS_IP
         *
         * @param validatorType the validator type
         * @return the Builder
         */
        public Builder validatorType(int validatorType) {
            this.validatorType = validatorType;
            return this;
        }

        /**
         * Set the min length of the field
         *
         * @param minLength the min length
         * @return the Builder
         */
        public Builder minLength(int minLength) {
            this.minLength = minLength;
            return this;
        }

        /**
         * Set the max length of the field
         *
         * @param maxLength the max length
         * @return the Builder
         */
        public Builder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        /**
         * Set the min value of the field
         * The field must be numeric
         *
         * @param minValue the min value
         * @return the Builder
         */
        public Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        /**
         * Set the max value of the field
         * The field must be numeric
         *
         * @param maxValue the max value
         * @return the Builder
         */
        public Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        /**
         * Set a regex to validate the field
         *
         * @param regex the regex
         * @return the Builder
         */
        public Builder regex(String regex) {
            this.regex = regex;
            return this;
        }

        /**
         * Compare the field to another EditText so they have to be equals
         *
         * @param otherEditTextId the id of the other EditText
         * @return the Builder
         */
        public Builder identicalAs(int otherEditTextId) {
            this.otherEditTextId = otherEditTextId;
            return this;
        }

        /**
         * Compare the field to another EditText so they have to be equals
         *
         * @param editText the other EditText
         * @return the Builder
         */
        public Builder identicalAs(TextField editText) {
            this.otherEditText = editText;
            return this;
        }

        /**
         * Set a custom error message
         *
         * @param errorMessage the error message
         * @return the Builder
         */
        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        /**
         * Set a custom required message
         *
         * @param requiredMessage the required message
         * @return the Builder
         */
        public Builder requiredMessage(String requiredMessage) {
            this.requiredMessage = requiredMessage;
            return this;
        }

        /**
         * Whether to show the error messages
         *
         * @param showError true to show, false either
         * @return the Builder
         */
        public Builder showError(boolean showError) {
            this.showError = showError;
            return this;
        }

        /**
         * Specify the EditText to validate
         *
         * @param editText the edit text
         * @return the Builder
         */
        public Builder on(TextField editText) {
            this.editText = editText;
            return this;
        }

        /**
         * Build a new InputValidator object
         *
         * @return a InputValidator object
         */
        public InputValidator build() {
            return newInstance(this);
        }
    }

    /**
     * Create a new instance of InputValidator according to a Builder
     *
     * @param builder the builder
     * @return an InputValidator object
     */
    public static InputValidator newInstance(Builder builder) {
        InputValidator inputValidator = new InputValidator(builder.context);
        inputValidator.setEditText(builder.editText);
        inputValidator.setRequired(builder.required);
        inputValidator.setCustomValidator(builder.validator);
        inputValidator.setValidatorType(builder.validatorType);
        inputValidator.setMinLength(builder.minLength);
        inputValidator.setMaxLength(builder.maxLength);
        inputValidator.setMaxValue(builder.maxValue);
        inputValidator.setMinValue(builder.minValue);
        inputValidator.setRegex(builder.regex);
        inputValidator.setOtherEditText(builder.otherEditText);
        inputValidator.setIdenticalAs(builder.otherEditTextId);
        inputValidator.setErrorMessage(builder.errorMessage);
        inputValidator.setRequiredMessage(builder.requiredMessage);
        inputValidator.setShowError(builder.showError);
        inputValidator.buildValidator();
        return inputValidator;
    }
}
