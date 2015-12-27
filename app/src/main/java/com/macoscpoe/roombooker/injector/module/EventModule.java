package com.macoscpoe.roombooker.injector.module;

import com.macoscope.domain.NewEventUsecase;
import com.macoscope.mvp.presenter.NewEventPresenter;
import com.macoscope.network.Repository;
import com.macoscpoe.roombooker.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Piotr Ziomacki on 02/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@PerActivity
@Module
public class EventModule {

    @PerActivity
    @Provides
    public NewEventUsecase provideNewEventUsecase(Repository repository) {
        return new NewEventUsecase(repository);
    }

    @PerActivity
    @Provides
    public NewEventPresenter provideNewEventPresenter(NewEventUsecase usecase) {
        return new NewEventPresenter(usecase);
    }


}
