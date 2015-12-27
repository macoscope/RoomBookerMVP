package com.macoscpoe.roombooker;

import android.app.Application;

import com.macoscpoe.roombooker.injector.component.ApplicationComponent;
import com.macoscpoe.roombooker.injector.component.DaggerApplicationComponent;
import com.macoscpoe.roombooker.injector.module.ApplicationModule;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class RoomBookerApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupInjector();
    }

    private void setupInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
