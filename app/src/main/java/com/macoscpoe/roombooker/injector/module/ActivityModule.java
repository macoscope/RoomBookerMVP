package com.macoscpoe.roombooker.injector.module;

import android.app.Activity;
import android.content.Context;

import com.macoscpoe.roombooker.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Context context() {
        return activity;
    }
}
