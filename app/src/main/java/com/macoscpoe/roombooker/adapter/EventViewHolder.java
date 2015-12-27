package com.macoscpoe.roombooker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.macoscpoe.roombooker.R;

/**
 * Created by Tomasz Kulikowski on 30.11.2015.
 * Copyright © 2015 Macoscope sp. z o.o. All rights reserved.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView summary;
    public TextView organizer;
    public TextView startDate;
    public TextView endDate;
    public TextView status;

    public EventViewHolder(View itemView) {
        super(itemView);
        summary = (TextView) itemView.findViewById(R.id.ve_summary);
        organizer = (TextView) itemView.findViewById(R.id.ve_organizer);
        startDate = (TextView) itemView.findViewById(R.id.ve_start_date);
        endDate = (TextView) itemView.findViewById(R.id.ve_end_date);
        status = (TextView) itemView.findViewById(R.id.ve_status);
    }
}
