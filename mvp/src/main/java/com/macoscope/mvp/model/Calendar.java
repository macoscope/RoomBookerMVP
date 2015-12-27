package com.macoscope.mvp.model;

/**
 * Created by Piotr Ziomacki on 01/12/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class Calendar {

    public static final String JSON_ARRAY_NAME = "calendars";

    public int id;
    public String description;
    public String summary;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

}