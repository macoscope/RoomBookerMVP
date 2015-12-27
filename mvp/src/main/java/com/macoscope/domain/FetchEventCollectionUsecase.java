package com.macoscope.domain;

import com.macoscope.mvp.model.Event;
import com.macoscope.network.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class FetchEventCollectionUsecase implements Usecase<List<Event>> {
    private Repository repository;
    private int calendarId;

    public FetchEventCollectionUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    @Override
    public Observable<List<Event>> execute() {
        return repository.getEvents(calendarId).map(new ResponseMappingFunc<List<Event>>());
    }
}
