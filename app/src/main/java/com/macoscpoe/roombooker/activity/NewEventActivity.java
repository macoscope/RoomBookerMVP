package com.macoscpoe.roombooker.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.presenter.NewEventPresenter;
import com.macoscope.mvp.view.NewEventView;
import com.macoscpoe.roombooker.R;
import com.macoscpoe.roombooker.RoomBookerApplication;
import com.macoscpoe.roombooker.fragment.DatePickerFragment;
import com.macoscpoe.roombooker.fragment.TimePickerFragment;
import com.macoscpoe.roombooker.injector.component.ApplicationComponent;
import com.macoscpoe.roombooker.injector.component.DaggerNewEventComponent;
import com.macoscpoe.roombooker.injector.module.ActivityModule;
import com.macoscpoe.roombooker.injector.module.EventModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

/**
 * Created by Tomasz Kulikowski on 01.12.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class NewEventActivity extends AppCompatActivity implements NewEventView {

    @Inject
    public NewEventPresenter newEventPresenter;

    public static final String KEY_CALENDAR_ID = "calendarId";
    public static final String KEY_CALENDAR_NAME = "calendarName";

    @Bind(R.id.ane_summary)
    EditText summary;

    @Bind(R.id.ane_description)
    EditText description;

    @Bind(R.id.ane_end_date)
    EditText endDate;

    @Bind(R.id.ane_end_time)
    EditText endTime;

    @Bind(R.id.ane_start_date)
    EditText startDate;

    @Bind(R.id.ane_start_time)
    EditText startTime;

    @Bind(R.id.ane_toolbar)
    Toolbar toolbar;

    @Bind(R.id.ane_progressbar)
    ProgressBar progressBar;

    private int calendarId;
    private String calendarName;
    private DateFormat simpleTimeFormat;
    private DateFormat simpleDateFormat;
    private Toast toast;

    @Override
    protected void onStop() {
        super.onStop();
        newEventPresenter.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDateTimeFormat();
        setContentView(R.layout.activity_new_event);
        ButterKnife.bind(this);
        init(savedInstanceState);
        initToolbar();
        ApplicationComponent appComponent = ((RoomBookerApplication) getApplication()).getApplicationComponent();

        DaggerNewEventComponent.builder()
                .applicationComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .eventModule(new EventModule())
                .build()
                .inject(this);
        initializePresenter();
    }

    private void initializePresenter() {
        newEventPresenter.attachView(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CALENDAR_ID, calendarId);
        outState.putString(KEY_CALENDAR_NAME, calendarName);
    }

    private void initDateTimeFormat() {
        simpleTimeFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT);
        simpleDateFormat = SimpleDateFormat.getDateInstance();
    }

    private void init(Bundle savedInstanceState) {
        Bundle extras = savedInstanceState != null ? savedInstanceState : getIntent().getExtras();
        calendarId = extras.getInt(KEY_CALENDAR_ID, -1);
        calendarName = extras.getString(KEY_CALENDAR_NAME, "");
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.new_event_title);
        toolbar.setSubtitle(calendarName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_event, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ne_save_action) {
            saveEditTextsValues();
            newEventPresenter.postNewEvent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnFocusChange(R.id.ane_start_date)
    public void openStartDatePicker(boolean hasFocus) {
        if (hasFocus) {
            displayDatePicker(startDate);
        }
    }

    @OnFocusChange(R.id.ane_end_date)
    public void openEndDatePicker(boolean hasFocus) {
        if (hasFocus) {
            displayDatePicker(endDate);
        }
    }

    @OnFocusChange(R.id.ane_start_time)
    public void openStartTimePicker(boolean hasFocus) {
        if (hasFocus) {
            displayTimePicker(startTime);
        }
    }

    @OnFocusChange(R.id.ane_end_time)
    public void openEndTimePicker(boolean hasFocus) {
        if (hasFocus) {
            displayTimePicker(endTime);
        }
    }

    private void displayTimePicker(final EditText destination) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String displayTime = simpleTimeFormat.format(calendar.getTime());
                destination.setText(displayTime);
                switch (destination.getId()) {
                    case R.id.ane_start_time:
                        newEventPresenter.setStartTime(hourOfDay, minute);
                        break;
                    case R.id.ane_end_time:
                        newEventPresenter.setEndTime(hourOfDay, minute);
                        break;
                }
            }
        });
        timePickerFragment.show(getSupportFragmentManager(), TimePickerFragment.TAG);
    }

    private void displayDatePicker(final EditText destination) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                String displayDate = simpleDateFormat.format(calendar.getTime());
                destination.setText(displayDate);
                switch (destination.getId()) {
                    case R.id.ane_start_date:
                        newEventPresenter.setStartDate(year, monthOfYear, dayOfMonth);
                        break;
                    case R.id.ane_end_date:
                        newEventPresenter.setEndDate(year, monthOfYear, dayOfMonth);
                        break;
                }
            }
        });
        datePickerFragment.show(getSupportFragmentManager(), DatePickerFragment.TAG);
    }

    @Override
    public void showLoading() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
        runOnUiThread(runnable);
    }

    @Override
    public void showError() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showToast(getString(R.string.new_event_error));
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
        runOnUiThread(runnable);

    }

    @Override
    public void showEventCreated(final Event event) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showToast(getString(R.string.new_event_added, event.getSummary(), calendarName));
                finish();
            }
        };
        runOnUiThread(runnable);
    }

    private void saveEditTextsValues() {
        newEventPresenter.setCalendarId(calendarId);
        newEventPresenter.setDescription(description.getText().toString());
        newEventPresenter.setSummary(summary.getText().toString());
    }

    private void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}