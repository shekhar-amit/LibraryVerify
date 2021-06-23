package com.applib.libverify;

import ohos.aafwk.ability.AbilityPackage;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;

import java.lang.ref.WeakReference;

public class App extends AbilityPackage {

    private static Context mContext;
//    private static WeakReference<Context> mContext;

    @Override
    public void onInitialize() {
        System.out.println("AMIT : ONINITIALIZE APP 1");
        super.onInitialize();
        System.out.println("AMIT : ONINITIALIZE APP");
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
//        mContext = new WeakReference<Context>(this);
    }

    public static Context getmContext() {
//        return mContext.get();
        return mContext;
    }
}
