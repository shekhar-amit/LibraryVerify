package com.pchmn.libraryverify;

import com.pchmn.libraryverify.slice.TwoFormAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

/**
 * Form validation via JSON InputValidators and Forms.
 */
public class TwoFormAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TwoFormAbilitySlice.class.getName());
    }
}
