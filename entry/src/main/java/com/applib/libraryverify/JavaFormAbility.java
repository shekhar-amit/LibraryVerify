package com.applib.libraryverify;

import com.applib.libraryverify.slice.JavaFormAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class JavaFormAbility extends Ability {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(JavaFormAbilitySlice.class.getName());
    }
}
