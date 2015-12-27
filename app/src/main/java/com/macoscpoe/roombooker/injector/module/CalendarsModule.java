package com.macoscpoe.roombooker.injector.module;

import com.macoscope.domain.FetchCalendarsUsecase;
import com.macoscope.domain.FetchEventCollectionUsecase;
import com.macoscope.mvp.presenter.CalendarsPresenter;
import com.macoscope.mvp.presenter.EventsCollectionPresenter;
import com.macoscope.network.Repository;
import com.macoscpoe.roombooker.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
@Module
public class CalendarsModule {
    @PerActivity
    @Provides
    public FetchCalendarsUsecase provideGetCalendarsUsecase(Repository repository) {
        return new FetchCalendarsUsecase(repository);
    }

    @PerActivity
    @Provides
    public CalendarsPresenter provideCalendarPresenter(FetchCalendarsUsecase usecase) {
        return new CalendarsPresenter(usecase);
    }

    @Provides
    public FetchEventCollectionUsecase provideGetEventCollectionUsecase(Repository repository) {
        return new FetchEventCollectionUsecase(repository);
    }

    @Provides
    public EventsCollectionPresenter provideEventsCollectionPresenter(FetchEventCollectionUsecase usecase) {
        return new EventsCollectionPresenter(usecase);
    }
}
