package com.macoscpoe.roombooker.rest;

import com.macoscope.mvp.model.Calendar;
import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.model.ResponseWrapper;
import com.macoscope.mvp.model.User;

import java.util.List;

import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Retrofit service interface with retrofit annoted method like @GET, @POST
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface ApiService {


    @GET("calendars")
    Observable<ResponseWrapper<List<Calendar>>> getCalendars();

    @GET("calendars/{calendar_id}/events")
    Observable<ResponseWrapper<List<Event>>> getEvents(@Path("calendar_id") int calendarId);

    @DELETE("calendars/{calendar_id}/events/{event_id}")
    Observable<Response> deleteEvent(@Path("calendar_id") int calendarId, @Path("event_id") int eventId);

    @GET("calendars/{calendar_id}/events/{event_id}")
    Observable<Event> getEvent(@Path("calendar_id") int calendarId, @Path("event_id") int eventId);

    @GET("users")
    Observable<ResponseWrapper<List<User>>> getUsers();

    @POST("calendars/{calendar_id}/events")
    Observable<ResponseWrapper<Event>> postEvent(@Path("calendar_id") int calendarId, @Body Event event);
}
