package com.applib.libraryverify;

import com.applib.libraryverify.slice.JavaFormAbilitySlice;
import com.applib.libverify.Form;
import com.applib.libverify. InputValidator;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

public class JavaFormAbility extends Ability {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(JavaFormAbilitySlice.class.getName());
    }
}
