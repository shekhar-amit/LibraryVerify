package com.applib.libraryverify;

import com.applib.libraryverify.slice.OneFormAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class OneFormAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(OneFormAbilitySlice.class.getName());
    }
}
