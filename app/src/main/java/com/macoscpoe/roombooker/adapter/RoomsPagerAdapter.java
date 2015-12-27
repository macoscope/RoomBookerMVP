package com.macoscpoe.roombooker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.macoscope.mvp.model.Calendar;
import com.macoscpoe.roombooker.fragment.RoomFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tomasz Kulikowski on 30.11.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class RoomsPagerAdapter extends FragmentStatePagerAdapter{


    private List<Calendar> calendars = new ArrayList<>();

    public RoomsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addAll(Collection<Calendar> calendars) {
        this.calendars.addAll(calendars);
        notifyDataSetChanged();
    }

    public void add(Calendar calendar) {
        this.calendars.add(calendar);
        notifyDataSetChanged();
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars.clear();
        this.calendars = calendars;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Calendar calendar = calendars.get(position);
        return RoomFragment.newInstance(calendar.getId());
    }

    @Override
    public int getCount() {
        return calendars.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return calendars.get(position).getSummary();
    }

    public Calendar getPageCalendar(int position){
        return calendars.get(position);
    }

}
