package com.macoscpoe.roombooker.injector.component;

import android.app.Application;

import com.macoscope.network.Repository;
import com.macoscpoe.roombooker.RoomBookerApplication;
import com.macoscpoe.roombooker.injector.module.ApplicationModule;
import com.macoscpoe.roombooker.injector.module.NetworkModule;
import com.macoscpoe.roombooker.injector.scope.PerApplication;

import dagger.Component;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@PerApplication
@Component(modules = {ApplicationModule.class,
        NetworkModule.class
}
)
public interface ApplicationComponent {

    Application application();
    RoomBookerApplication roomBookerApplication();
    Repository repository();
}
