package com.macoscope.mvp.view;

import com.macoscope.mvp.model.Calendar;

import java.util.List;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface CalendarsView extends View {

    void showCalendars(List<Calendar> calendars);
    void showLoading();
    void showError();

}
