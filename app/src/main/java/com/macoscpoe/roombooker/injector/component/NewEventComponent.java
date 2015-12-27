package com.macoscpoe.roombooker.injector.component;

import com.macoscpoe.roombooker.activity.NewEventActivity;
import com.macoscpoe.roombooker.injector.module.ActivityModule;
import com.macoscpoe.roombooker.injector.module.EventModule;
import com.macoscpoe.roombooker.injector.scope.PerActivity;

import dagger.Component;

/**
 * Created by Piotr Ziomacki on 02/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@PerActivity
@Component (dependencies = ApplicationComponent.class,
    modules = {ActivityModule.class,
            EventModule.class}
)
public interface NewEventComponent {

    void inject(NewEventActivity activity);

}
