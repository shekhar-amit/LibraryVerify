package com.applib.libverify;

import ohos.aafwk.ability.Ability;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Form extends DirectionalLayout {

    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");
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
        System.out.println("AMIT : Form Validate Entry");
        // get the fields
        if(!mInflated) {
            int childCount = getChildCount();

            // TODO: Add the activity root view validation
//            if(childCount == 0 && mActivity != null)
//                getChildViews(((ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content)));

            if(childCount == 0 && mViewGroupRoot != null)
                getChildViews(mViewGroupRoot);
            else {
                System.out.println("AMIT : Form getChildViews called through 'this'");
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
        System.out.println("AMIT : Form getChildViews Entry");
        System.out.println("AMIT : Form CRASH BEFORE?");
        int childCount = viewGroup.getChildCount();
        System.out.println("AMIT : Form CRASH AFTER? childCount: "+childCount);
        for (int i = 0; i < childCount; i++) {
            System.out.println("AMIT : Form getChildViews loop "+i);
            Component v = viewGroup.getComponentAt(i);

            // only if input validator
            if(v instanceof InputValidator) {
                System.out.println("AMIT : Form getChildViews loop call tiv");
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
        System.out.println("AMIT : Form validatelist ENTRY list size: "+mInputValidatorList.size());
        mIsValid = true;
        for(InputValidator InputValidator: mInputValidatorList) {
            System.out.println("AMIT : Form INSIDE LOOP");
            // show error
            InputValidator.setShowError(mShowErrors);
            // if validator not valid, the Form is not valid either
            if(!InputValidator.isValid())
                mIsValid = false;
        }
        System.out.println("AMIT : Form validatelist EXIT");
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

        //TODO: Add activity builder
//        public Builder(Ability activity) {
//            this.activity = activity;
//        }

        public Builder(Context context, Component rootView) {
            this.context = context;
            this.viewGroup = (ComponentContainer) rootView;
        }

        public Builder(Context context) {
            System.out.println("AMIT : Form Builder Constructer by Context");
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
            System.out.println("AMIT : Added a InputValidator");
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
        System.out.println("AMIT : Form Builder NewInstance");
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
            System.out.println("AMIT : NewInstance set context");
            Form = new Form(builder.context);
        }
        Form.setShowErrors(builder.showErrors);
        Form.setInputValidatorList(builder.InputValidatorList);
        System.out.println("AMIT : Form Builder NewInstance LEAVE");
        return Form;
    }

}
