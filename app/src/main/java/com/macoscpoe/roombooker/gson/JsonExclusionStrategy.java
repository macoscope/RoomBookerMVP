package com.macoscpoe.roombooker.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

/**
 * Implementation of {@link ExclusionStrategy} which allows determining if serializable class field
 * should be serialized or deserialized using {@link Expose} annotation.
 * Created by Tomasz Kulikowski on 03.12.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class JsonExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
        return expose != null && !expose.serialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
