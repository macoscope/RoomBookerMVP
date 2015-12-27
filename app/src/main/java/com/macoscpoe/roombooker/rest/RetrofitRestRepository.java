package com.macoscpoe.roombooker.rest;

import com.macoscope.mvp.model.Calendar;
import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.model.ResponseWrapper;
import com.macoscope.mvp.model.User;
import com.macoscope.network.Repository;

import java.util.List;

import retrofit.Retrofit;
import rx.Observable;

/**
 * Class that implements interface repository. Should use rest methods provided by ApiService.class to implements Repository methods
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class RetrofitRestRepository implements Repository {

    private ApiService apiService;

    public RetrofitRestRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<ResponseWrapper<List<Calendar>>> getCalendars() {
        return apiService.getCalendars();
    }

    @Override
    public Observable<ResponseWrapper<List<Event>>> getEvents(int calendarId) {
        return apiService.getEvents(calendarId);
    }

    @Override
    public Observable<ResponseWrapper<Event>> postEvent(Event event) {
        return apiService.postEvent(event.calendarId, event);
    }

    @Override
    public Observable<Event> getEvent(int calendarId, int eventId) {
        return null;
    }

    @Override
    public Observable<ResponseWrapper<List<User>>> getUsers() {
        return apiService.getUsers();
    }
}
