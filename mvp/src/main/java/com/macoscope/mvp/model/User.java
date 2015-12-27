package com.macoscope.mvp.model;

import java.util.List;

/**
 * Created by Piotr Ziomacki on 30/11/15.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class User {

    public static final String JSON_ARRAY_NAME = "users";

    public String name;
    public List<String> emails;


    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }
}
