package com.macoscope.mvp.view;

import com.macoscope.mvp.model.Event;

/**
 * Created by Piotr Ziomacki on 02/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface NewEventView  extends View{
    void showLoading();
    void showError();
    void showEventCreated(Event event);
}
