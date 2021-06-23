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
     * Check if the Form is valid
     *
     * @return true if it is valid, false either
     */
    public boolean isValid() {
        validate();
        return mIsValid;
    }

    /**
     * Validate the Form by getting and checking all the fields of the Form
     */
    private void validate() {
        // get the fields
        if(!mInflated) {
            int childCount = getChildCount();

            if(childCount == 0 && mViewGroupRoot != null)
                getChildViews(mViewGroupRoot);
            else {
                getChildViews(this);
            }
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
            if(v instanceof InputValidator) {
                mInputValidatorList.add((InputValidator) v);
            }
            // iterate threw view group children
            else if(v instanceof ComponentContainer)
                getChildViews((ComponentContainer) v);
        }
    }

    /**
     * Validate all the fields of the Form
     */
    private void validateList() {
        mIsValid = true;
        for(InputValidator InputValidator: mInputValidatorList) {
            // show error
            InputValidator.setShowError(mShowErrors);
            // if validator not valid, the Form is not valid either
            if(!InputValidator.isValid())
                mIsValid = false;
        }
    }

    public void setShowErrors(boolean mShowErrors) {
        this.mShowErrors = mShowErrors;
    }

    public void addInputValidator(InputValidator InputValidator) {
        mInputValidatorList.add(InputValidator);
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
        private List<InputValidator> InputValidatorList = new ArrayList<>();

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
         * Add an InputValidator to the Form
         *
         * @param InputValidator the input validator
         * @return the Builder
         */
        public Builder addInputValidator(InputValidator InputValidator) {
            this.InputValidatorList.add(InputValidator);
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
        Form Form;
        if(builder.activity != null) {
            Form = new Form(builder.activity);
            Form.setActivity(builder.activity);
        }
        else if(builder.viewGroup != null) {
            Form = new Form(builder.context);
            Form.setViewGroupRoot(builder.viewGroup);
        }
        else {
            Form = new Form(builder.context);
        }
        Form.setShowErrors(builder.showErrors);
        Form.setInputValidatorList(builder.InputValidatorList);
        return Form;
    }

}
