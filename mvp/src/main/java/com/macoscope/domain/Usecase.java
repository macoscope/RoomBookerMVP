package com.macoscope.domain;

import rx.Observable;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public interface Usecase<T> {
    Observable<T> execute();
}
