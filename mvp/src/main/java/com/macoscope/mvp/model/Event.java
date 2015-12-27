package com.macoscope.mvp.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class Event {

    public static final String JSON_OBJECT_NAME = "event";
    public static final String JSON_ARRAY_NAME = "events";

    public String id;
    @Expose(serialize = false)
    public int calendarId;
    public String organizer;
    public String summary;
    public String description;
    @Expose(serialize = false)
    public String status;
    public Date startDate;
    public Date endDate;
    public List<String> attendeesEmails;

    public String getId() {
        return id;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<String> getAttendeesEmails() {
        return attendeesEmails;
    }


}
