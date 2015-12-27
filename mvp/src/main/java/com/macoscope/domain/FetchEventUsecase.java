package com.macoscope.domain;

import com.macoscope.mvp.model.Event;
import com.macoscope.network.Repository;

import rx.Observable;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class FetchEventUsecase implements Usecase<Event> {

    private Repository repository;
    private int calendarId;
    private int eventId;

    public FetchEventUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setEventParams(int calendarId, int eventId) {
        this.calendarId = calendarId;
        this.eventId = eventId;
    }

    @Override
    public Observable<Event> execute() {
        return repository.getEvent(calendarId, eventId);
    }

}
