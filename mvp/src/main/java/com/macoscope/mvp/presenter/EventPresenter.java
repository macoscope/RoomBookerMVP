package com.macoscope.mvp.presenter;

import com.macoscope.domain.FetchEventUsecase;
import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.view.EventView;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class EventPresenter implements Presenter<EventView> {

    private EventView eventView;
    private Subscription eventSubscribtion;
    private int calendarId;
    private int eventId;
    private FetchEventUsecase fetchEventUsecase;

    public EventPresenter(FetchEventUsecase fetchEventUsecase) {
        this.fetchEventUsecase = fetchEventUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (eventSubscribtion != null && !eventSubscribtion.isUnsubscribed()) {
            eventSubscribtion.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(EventView view) {
        this.eventView = view;
    }

    /**
     * Calls getEvent after setting calendarId and eventId params
     *
     * @param calendarId
     * @param eventId
     */
    public void setEventParams(int calendarId, int eventId) {
        this.calendarId = calendarId;
        this.eventId = eventId;
        getEvent();
    }

    private void getEvent() {
        fetchEventUsecase.setEventParams(calendarId, eventId);
        eventView.showLoading();
        eventSubscribtion = fetchEventUsecase.execute()
                .subscribeOn(Schedulers.io())

                .onErrorReturn(new Func1<Throwable, Event>() {
                    @Override
                    public Event call(Throwable throwable) {
                        eventView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        eventView.showEvent(event);
                    }
                });
    }
}
