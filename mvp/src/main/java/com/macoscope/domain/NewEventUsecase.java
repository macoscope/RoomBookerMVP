package com.macoscope.domain;

import com.macoscope.mvp.model.Event;
import com.macoscope.network.Repository;

import rx.Observable;

/**
 * Created by Piotr Ziomacki on 02/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class NewEventUsecase implements Usecase<Event> {

    private Repository repository;
    private Event event;

    public NewEventUsecase(Repository repository) {
        this.repository = repository;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public Observable<Event> execute() {
        return repository.postEvent(event).map(new ResponseMappingFunc<Event>());
    }
}
