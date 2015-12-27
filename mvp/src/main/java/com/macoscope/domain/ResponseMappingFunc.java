package com.macoscope.domain;

import com.macoscope.mvp.model.ResponseWrapper;

import rx.functions.Func1;

/**
 * Implementation of {@link Func1} with unwrapping operation on {@link ResponseWrapper<R>}
 * to {@code R} type object
 * <p/>
 * Created by Tomasz Kulikowski on 04.12.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class ResponseMappingFunc<R> implements Func1<ResponseWrapper<R>, R> {
    @Override
    public R call(ResponseWrapper<R> tResponseWrapper) {
        if (tResponseWrapper == null) {
            return null;
        }
        return tResponseWrapper.body;
    }
}
