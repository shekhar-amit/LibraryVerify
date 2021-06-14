package com.applib.libverify;

import ohos.aafwk.ability.Ability;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class Form extends DirectionalLayout {

    private static final String TAG = Form.class.toString();
    // activity
    private Ability mActivity;
    private ComponentContainer mViewGroupRoot;
    // show errors
    private boolean mShowErrors = true;
    // validity
    private boolean mIsValid;
    // input validators
    private List<InputValidator> mInputValidatorList = new ArrayList<>();
    // inflated
    private boolean mInflated = false;

    public Form(Context context) {
        super(context);
    }

    public Form(Context context, AttrSet attrs) {
        super(context, attrs);
        mShowErrors = attrs.getAttr("showErrors").isPresent() ? attrs.getAttr(
                "showErrors").get().getBoolValue() : true;
    }

    /**
     * Check if the form is valid
     *
     * @return true if it is valid, false either
     */
    public boolean isValid() {
        validate();
        return mIsValid;
    }

    /**
     * Validate the form by getting and checking all the fields of the form
     */
    private void validate() {
        // get the fields
        if(!mInflated) {
            int childCount = getChildCount();

            // TODO: Add the activity root view validation
            if(childCount == 0 && mViewGroupRoot != null)
                getChildViews(mViewGroupRoot);
            else
                getChildViews(this);

            mInflated = true;
        }

        // check the fields
        validateList();
    }

    /**
     * Get all fields
     *
     * @param viewGroup the root view
     */
    private void getChildViews(ComponentContainer viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Component v = viewGroup.getComponentAt(i);

            // only if input validator
            if(v instanceof InputValidator)
                mInputValidatorList.add((InputValidator) v);
                // iterate threw view group children
            else if(v instanceof ComponentContainer)
                getChildViews((ComponentContainer) v);
        }
    }

    /**
     * Validate all the fields of the form
     */
    private void validateList() {
        mIsValid = true;
        for(InputValidator inputValidator: mInputValidatorList) {
            // show error
            inputValidator.setShowError(mShowErrors);
            // if validator not valid, the form is not valid either
            if(!inputValidator.isValid())
                mIsValid = false;
        }
    }

    public void setShowErrors(boolean mShowErrors) {
        this.mShowErrors = mShowErrors;
    }

    public void addInputValidator(InputValidator inputValidator) {
        mInputValidatorList.add(inputValidator);
    }

    public void setInputValidatorList(List<InputValidator> mInputValidatorList) {
        this.mInputValidatorList = mInputValidatorList;
    }

    public void setActivity(Ability mActivity) {
        this.mActivity = mActivity;
    }

    public void setViewGroupRoot(ComponentContainer mViewGroupRoot) {
        this.mViewGroupRoot = mViewGroupRoot;
    }

    /**
     * Builder class for Form
     */
    public static class Builder {
        private Ability activity;
        private Context context;
        private ComponentContainer viewGroup;
        private boolean showErrors = true;
        private List<InputValidator> inputValidatorList = new ArrayList<>();

        //TODO: Add activity builder
//        public Builder(Ability activity) {
//            this.activity = activity;
//        }

        public Builder(Context context, Component rootView) {
            this.context = context;
            this.viewGroup = (ComponentContainer) rootView;
        }

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Whether to show error messages
         *
         * @param showErrors true to show, false either
         * @return the Builder
         */
        public Builder showErrors(boolean showErrors) {
            this.showErrors = showErrors;
            return this;
        }

        /**
         * Add an InputValidator to the form
         *
         * @param inputValidator the input validator
         * @return the Builder
         */
        public Builder addInputValidator(InputValidator inputValidator) {
            this.inputValidatorList.add(inputValidator);
            return this;
        }

        /**
         * Build a new Form object
         *
         * @return a Form object
         */
        public Form build() {
            return Form.newInstance(this);
        }
    }

    /**
     * Create a new instance of Form according to a Builder
     *
     * @param builder the builder
     * @return a Form object
     */
    public static Form newInstance(Builder builder) {
        Form form;
        if(builder.activity != null) {
            form = new Form(builder.activity);
            form.setActivity(builder.activity);
        }
        else if(builder.viewGroup != null) {
            form = new Form(builder.context);
            form.setViewGroupRoot(builder.viewGroup);
        }
        else {
            form = new Form(builder.context);
        }
        form.setShowErrors(builder.showErrors);
        form.setInputValidatorList(builder.inputValidatorList);
        return form;
    }
}
