package com.macoscope.domain;

import com.macoscope.mvp.model.Calendar;
import com.macoscope.network.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class FetchCalendarsUsecase implements Usecase<List<Calendar>> {

    private Repository repository;

    public FetchCalendarsUsecase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Calendar>> execute() {
        return repository.getCalendars().map(new ResponseMappingFunc<List<Calendar>>());
    }
}
