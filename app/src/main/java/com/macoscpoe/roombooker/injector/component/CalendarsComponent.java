package com.macoscpoe.roombooker.injector.component;

import android.content.Context;

import com.macoscpoe.roombooker.activity.RoomsActivity;
import com.macoscpoe.roombooker.fragment.RoomFragment;
import com.macoscpoe.roombooker.injector.module.ActivityModule;
import com.macoscpoe.roombooker.injector.module.CalendarsModule;
import com.macoscpoe.roombooker.injector.scope.PerActivity;

import dagger.Component;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@PerActivity
@Component (dependencies = ApplicationComponent.class,
    modules = {ActivityModule.class,
            CalendarsModule.class

    })
public interface CalendarsComponent  {

    void inject(RoomsActivity roomsActivity);
    void inject(RoomFragment roomFragment);
    Context context();
}
