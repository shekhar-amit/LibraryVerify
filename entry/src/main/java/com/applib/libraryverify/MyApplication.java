package com.applib.libraryverify;

import ohos.aafwk.ability.AbilityPackage;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        System.out.println("AMIT : ONINITIALIZE SAMPLE APP");
        super.onInitialize();
    }
}
