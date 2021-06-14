package com.applib.libverify;

import ohos.aafwk.ability.AbilityPackage;
import ohos.app.Context;

import java.lang.ref.WeakReference;

public class App extends AbilityPackage {

    private static WeakReference<Context> mContext;

    @Override
    public void onInitialize() {
        super.onInitialize();
        mContext = new WeakReference<Context>(this);
    }

    public static Context getmContext() {
        return mContext.get();
    }
}
