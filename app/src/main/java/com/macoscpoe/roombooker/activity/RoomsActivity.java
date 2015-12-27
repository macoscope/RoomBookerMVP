package com.macoscpoe.roombooker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.macoscope.mvp.model.Calendar;
import com.macoscope.mvp.presenter.CalendarsPresenter;
import com.macoscope.mvp.view.CalendarsView;
import com.macoscpoe.roombooker.R;
import com.macoscpoe.roombooker.RoomBookerApplication;
import com.macoscpoe.roombooker.adapter.RoomsPagerAdapter;
import com.macoscpoe.roombooker.injector.component.ApplicationComponent;
import com.macoscpoe.roombooker.injector.component.CalendarsComponent;
import com.macoscpoe.roombooker.injector.component.DaggerCalendarsComponent;
import com.macoscpoe.roombooker.injector.module.ActivityModule;
import com.macoscpoe.roombooker.injector.module.CalendarsModule;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class RoomsActivity extends AppCompatActivity implements CalendarsView {

    @Inject
    CalendarsPresenter calendarsPresenter;

    @Bind(R.id.ar_toolbar)
    Toolbar toolbar;

    @Bind(R.id.ar_tabs)
    TabLayout tabLayout;

    @Bind(R.id.ar_pager)
    ViewPager viewPager;

    @Bind(R.id.ar_loading)
    View loadingView;

    @Bind(R.id.ar_fab)
    FloatingActionButton fab;

    private RoomsPagerAdapter calendarPagerAdapter;
    private CalendarsComponent calendarsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        ButterKnife.bind(this);
        ApplicationComponent appComponent = ((RoomBookerApplication) getApplication()).getApplicationComponent();

        calendarsComponent = DaggerCalendarsComponent.builder()
                .calendarsModule(new CalendarsModule())
                .activityModule(new ActivityModule(this))
                .applicationComponent(appComponent)
                .build();
        calendarsComponent.inject(this);
        initializePresenter();
        setSupportActionBar(toolbar);
        calendarPagerAdapter = new RoomsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(calendarPagerAdapter);
    }


    public CalendarsComponent getCalendarsComponent() {
        return calendarsComponent;
    }

    @Override
    protected void onStop() {
        super.onStop();
        calendarsPresenter.onStop();
    }

    private void initializePresenter() {
        calendarsPresenter.attachView(this);
    }

    @OnClick(R.id.ar_fab)
    public void onFabClick() {
        int position = viewPager.getCurrentItem();
        Calendar pageCalendar = calendarPagerAdapter.getPageCalendar(position);
        Intent intent = new Intent(this, NewEventActivity.class);
        intent.putExtra(NewEventActivity.KEY_CALENDAR_ID, pageCalendar.getId());
        intent.putExtra(NewEventActivity.KEY_CALENDAR_NAME, pageCalendar.getDescription());
        startActivity(intent);
    }

    public void addRooms(Collection<Calendar> rooms) {
        calendarPagerAdapter.addAll(rooms);
        hideLoading();
        tabLayout.setupWithViewPager(viewPager);
    }

    public void showLoading() {
        fab.setVisibility(View.GONE);
        viewPager.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        fab.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCalendars(final List<Calendar> calendars) {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                addRooms(calendars);
            }
        };
        runOnUiThread(myRunnable);
    }

    @Override
    public void showError() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RoomsActivity.this, R.string.main_error_loading, Toast.LENGTH_LONG).show();
            }
        };
        runOnUiThread(myRunnable);
    }
}
