package com.macoscope.mvp.presenter;

import com.macoscope.domain.FetchCalendarsUsecase;
import com.macoscope.mvp.model.Calendar;
import com.macoscope.mvp.view.CalendarsView;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class CalendarsPresenter implements Presenter<CalendarsView> {

    private Subscription getCalendarsSubscription;
    private CalendarsView calendarsView;
    private FetchCalendarsUsecase fetchCalendarsUsecase;

    public CalendarsPresenter(FetchCalendarsUsecase fetchCalendarsUsecase) {
        this.fetchCalendarsUsecase = fetchCalendarsUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (getCalendarsSubscription != null && !getCalendarsSubscription.isUnsubscribed()) {
            getCalendarsSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(CalendarsView view) {
        this.calendarsView = view;
        getCalendars();
    }

    private void getCalendars() {
        calendarsView.showLoading();
        getCalendarsSubscription = fetchCalendarsUsecase.execute()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Calendar>>() {
                    @Override
                    public List<Calendar> call(Throwable throwable) {
                        throwable.printStackTrace();
                        calendarsView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Calendar>>() {
                    @Override
                    public void call(List<Calendar> calendars) {
                        if (calendars != null) {
                            calendarsView.showCalendars(calendars);
                        }
                    }
                });
    }
}
