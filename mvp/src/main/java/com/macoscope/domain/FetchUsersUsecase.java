package com.macoscope.domain;

import com.macoscope.mvp.model.User;
import com.macoscope.network.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by Tomasz Kulikowski on 03.12.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class FetchUsersUsecase implements Usecase<List<User>> {
    private Repository repository;

    public FetchUsersUsecase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<User>> execute() {
        return repository.getUsers().map(new ResponseMappingFunc<List<User>>());
    }
}
