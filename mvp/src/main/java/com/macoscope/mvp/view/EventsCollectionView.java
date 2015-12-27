package com.macoscope.mvp.view;

import com.macoscope.mvp.model.Event;

import java.util.List;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface EventsCollectionView extends View{

    void showLoading();
    void showEvents(List<Event> events);
    void showError();
    void showEmpty();

}
