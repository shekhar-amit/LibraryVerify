package com.applib.libraryverify.slice;

import com.applib.libverify.Form;
import com.applib.libverify.InputValidator;
import com.applib.libraryverify.ResourceTable;
import com.applib.libverify.Form;
import com.applib.libverify.InputValidator;
import com.applib.libverify.validator.AbstractValidator;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {

    Button btn_java_form;
    Button btn_two_form;
    Button btn_one_form;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        System.out.println("AMIT : APPLICATION START");

        btn_java_form = (Button) findComponentById(ResourceTable.Id_btn_java_form);
        btn_two_form = (Button) findComponentById(ResourceTable.Id_btn_two_form);
        btn_one_form = (Button) findComponentById(ResourceTable.Id_btn_one_form);

        btn_java_form.setClickedListener(listener -> present(new JavaFormAbilitySlice(), new Intent()));
        btn_two_form.setClickedListener(listener -> present(new TwoFormAbilitySlice(), new Intent()));
        btn_one_form.setClickedListener(listener -> present(new OneFormAbilitySlice(), new Intent()));

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