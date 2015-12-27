package com.macoscope.mvp.model;

/**
 * Generic class for obtaining responses with their bodies from {@link com.macoscope.network.Repository}.
 * Parsing typed element must be handled by custom {@link com.google.gson.JsonDeserializer<ResponseWrapper>}
 *
 * Created by Tomasz Kulikowski on 03.12.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class ResponseWrapper<T> {
    public T body;
}
