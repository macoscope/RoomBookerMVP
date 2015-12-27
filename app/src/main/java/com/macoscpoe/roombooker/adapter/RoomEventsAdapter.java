package com.macoscpoe.roombooker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macoscope.mvp.model.Event;
import com.macoscpoe.roombooker.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tomasz Kulikowski on 30.11.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class RoomEventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> eventsList = new ArrayList<>();
    private Context context;

    public RoomEventsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.view_event, parent, false);

        EventViewHolder eventViewHolder = new EventViewHolder(itemView);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = eventsList.get(position);
        holder.summary.setText(getString(R.string.summary, event.getSummary()));
        holder.organizer.setText(getString(R.string.organizer, event.getOrganizer()));
        if (event.getStartDate() != null) {
            holder.startDate.setText(getString(R.string.start_date, event.getStartDate().toString()));
        }
        if (event.getEndDate() != null) {
            holder.endDate.setText(getString(R.string.end_date, event.getEndDate().toString()));
        }
        holder.status.setText(getString(R.string.status, event.getStatus()));
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    private String getString(int id, String... values) {
        return context.getString(id, values);
    }

    public void addEvent(Event event) {
        eventsList.add(event);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addEvents(Collection<Event> events) {
        eventsList.addAll(events);
        notifyDataSetChanged();
    }

    public void replaceEvents(Collection<Event> events) {
        eventsList.clear();
        eventsList.addAll(events);
        notifyDataSetChanged();
    }

}
