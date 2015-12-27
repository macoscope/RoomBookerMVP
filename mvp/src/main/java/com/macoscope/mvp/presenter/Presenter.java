package com.macoscope.mvp.presenter;

import com.macoscope.mvp.view.View;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface Presenter<T extends View> {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(T view);

}
