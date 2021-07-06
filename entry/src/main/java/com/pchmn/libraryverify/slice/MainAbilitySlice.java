package com.pchmn.libraryverify.slice;

import com.pchmn.libraryverify.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;

/**
 * Entry screen with buttons to various forms.
 */
public class MainAbilitySlice extends AbilitySlice {

    Button btn_java_form;
    Button btn_two_form;
    Button btn_one_form;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

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