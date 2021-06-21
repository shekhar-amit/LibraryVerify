package com.applib.libraryverify;

import com.applib.libraryverify.slice.TwoFormAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TwoFormAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TwoFormAbilitySlice.class.getName());
    }
}
