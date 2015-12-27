package com.macoscope.network;

import com.macoscope.mvp.model.Calendar;
import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.model.ResponseWrapper;
import com.macoscope.mvp.model.User;

import java.util.List;

import rx.Observable;

/**
 * Interface for API repository.
 * Usecases should use this interface for fetching and uploading data to repository.
 *
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface Repository {
    Observable<ResponseWrapper<List<Calendar>>> getCalendars();
    Observable<ResponseWrapper<List<Event>>> getEvents(int calendarId);
    Observable<ResponseWrapper<Event>> postEvent(Event event);
    Observable<Event> getEvent(int calendarId, int eventId);
    Observable<ResponseWrapper<List<User>>> getUsers();
}
