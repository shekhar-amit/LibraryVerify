package com.applib.libraryverify.slice;

import com.applib.libverify.Form;
import com.applib.libverify.InputValidator;
import com.applib.libraryverify.ResourceTable;
import com.applib.libverify.validator.AbstractValidator;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {

    Button mValidate;
    InputValidator mInputValidator;
    private Form mForm;
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        mValidate = (Button) findComponentById(ResourceTable.Id_validate);
        mInputValidator = (InputValidator) findComponentById(ResourceTable.Id_required_validator);
//        mInputValidator.setRequiredMessage("Please enter a value");
        mForm = new Form.Builder(this).showErrors(true).build();
//        mInputValidator.setCustomValidator(new AbstractValidator() {
//            @Override
//            public boolean isValid(String value) {
//                return value.equals("ok man");
//            }
//
//            @Override
//            public String getmErrorMessage() {
//                return "This field must be equals to 'ok man'";
//            }
//        });
        mValidate.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if(mForm.isValid()){
                    showToast("This form is valid");
                }
                else{
                    showToast("This form is invalid");
                }
                HiLog.debug(LABEL,"REACHED HERE SUPER BASIC");
            }
        });

    }

    private void showToast(String msg){
        new ToastDialog(this).setDuration(1000).setText(msg).show();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}