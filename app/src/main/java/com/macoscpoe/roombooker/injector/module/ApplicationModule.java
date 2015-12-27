package com.macoscpoe.roombooker.injector.module;

import android.app.Application;

import com.macoscpoe.roombooker.RoomBookerApplication;
import com.macoscpoe.roombooker.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides dependencies for application
 * Must be injected in main app component
 * If you want to use ApplicationContext please use Application object
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@Module
public class ApplicationModule {
    private final RoomBookerApplication application;

    public ApplicationModule(RoomBookerApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    public RoomBookerApplication provideMvpApplication() {
        return application;
    }

    @Provides
    @PerApplication
    public Application provideApplication() {return application; }

}
