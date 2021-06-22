package com.applib.libraryverify.slice;

import com.applib.libraryverify.ResourceTable;
import com.applib.libverify.Form;
import com.applib.libverify.InputValidator;
import com.applib.libverify.validator.AbstractValidator;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.window.dialog.ToastDialog;

public class OneFormAbilitySlice extends AbilitySlice {

    Button mValidate;
    InputValidator mInputValidator;
    Form mForm;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_one_form);

        mValidate = (Button) findComponentById(ResourceTable.Id_validate_one_form);
        mInputValidator = (InputValidator) findComponentById(ResourceTable.Id_custom_validator);
        mInputValidator.setCustomValidator(new AbstractValidator() {
            @Override
            public boolean isValid(String value) {
                return value.equals("ok man");
            }

            @Override
            public String getmErrorMessage() {
                return "This field must be equals to 'ok man'";
            }
        });

        DirectionalLayout mViewForm = (DirectionalLayout) findComponentById(ResourceTable.Id_form);

        mForm = new Form.Builder(this, mViewForm)
                .showErrors(true)
                .build();

        mValidate.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                showToast(mForm.isValid() ? "This form is valid" : "This form is not valid");
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