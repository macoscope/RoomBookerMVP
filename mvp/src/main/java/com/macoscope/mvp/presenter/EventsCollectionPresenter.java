package com.macoscope.mvp.presenter;

import com.macoscope.domain.FetchEventCollectionUsecase;
import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.view.EventsCollectionView;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class EventsCollectionPresenter implements Presenter<EventsCollectionView> {

    private EventsCollectionView eventsCollectionView;
    private Subscription getEventsSubscritpion;
    private FetchEventCollectionUsecase fetchEventCollectionUsecase;
    private int calendarId;
    private List<Event> eventsCollection;

    public EventsCollectionPresenter(FetchEventCollectionUsecase fetchEventCollectionUsecase) {
        this.fetchEventCollectionUsecase = fetchEventCollectionUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        getEvents();
    }

    @Override
    public void onStop() {
        if (getEventsSubscritpion != null && !getEventsSubscritpion.isUnsubscribed()) {
            getEventsSubscritpion.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    @Override
    public void attachView(EventsCollectionView view) {
        this.eventsCollectionView = view;
    }

    private void getEvents() {
        if (eventsCollection != null && eventsCollection != null) {
            eventsCollectionView.showEvents(eventsCollection);
        } else {
            eventsCollectionView.showLoading();
        }
        fetchEventCollectionUsecase.setCalendarId(calendarId);
        getEventsSubscritpion = fetchEventCollectionUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Event>>() {
                    @Override
                    public List<Event> call(Throwable throwable) {
                        throwable.printStackTrace();
                        eventsCollectionView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Event>>() {
                    @Override
                    public void call(List<Event> events) {
                        if (events != null) {
                            if (events != null && events.size() > 0) {
                                eventsCollection = events;
                                eventsCollectionView.showEvents(events);
                            } else {
                                eventsCollectionView.showEmpty();
                            }
                        }
                    }
                });
    }

    public void onRefresh() {
        eventsCollection = null;
        getEvents();
    }
}
