package com.macoscpoe.roombooker.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macoscope.mvp.model.Event;
import com.macoscope.mvp.presenter.EventsCollectionPresenter;
import com.macoscope.mvp.view.EventsCollectionView;
import com.macoscpoe.roombooker.R;
import com.macoscpoe.roombooker.activity.RoomsActivity;
import com.macoscpoe.roombooker.adapter.RoomEventsAdapter;
import com.macoscpoe.roombooker.injector.component.CalendarsComponent;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tomasz Kulikowski on 30.11.2015.
 *  * source: http://developer.android.com/guide/topics/ui/controls/pickers.html

 */
public class RoomFragment extends Fragment implements EventsCollectionView {
    @Inject
    EventsCollectionPresenter eventsCollectionPresenter;

    @Bind(R.id.fc_list)
    RecyclerView eventsList;

    @Bind(R.id.fc_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Bind(R.id.fc_loading)
    View loadingView;

    @Bind(R.id.fc_empty_list)
    View emptyListView;

    private RoomEventsAdapter roomEventsAdapter;

    private static final String KEY_ROOM_ID = "key_room_id";
    private int roomId = -1;

    public static RoomFragment newInstance(int roomId) {
        RoomFragment roomFragment = new RoomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ROOM_ID, roomId);
        roomFragment.setArguments(bundle);
        return roomFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_ROOM_ID, roomId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        eventsCollectionPresenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, root);
        initAdapter();
        initList();
        if (savedInstanceState != null) {
            roomId = savedInstanceState.getInt(KEY_ROOM_ID);
        } else {
            roomId = getArguments().getInt(KEY_ROOM_ID);
        }
        initEvents();
        setupRefreshLayout();
        return root;
    }

    private void setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventsCollectionPresenter.onRefresh();
            }
        });
    }

    private  void injectDependencies() {
        if (getActivity() instanceof RoomsActivity) {
            RoomsActivity activity = (RoomsActivity) getActivity();
            CalendarsComponent registrationComponent = activity.getCalendarsComponent();
            registrationComponent.inject(this);
        }
    }

    private void initList() {
        eventsList.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsList.setAdapter(roomEventsAdapter);
    }


    private void initEvents() {
        eventsCollectionPresenter.attachView(this);
        eventsCollectionPresenter.setCalendarId(roomId);
    }

    private void initAdapter() {
        if (roomEventsAdapter == null) {
            roomEventsAdapter = new RoomEventsAdapter(getActivity());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        eventsCollectionPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventsCollectionPresenter.onStart();
    }

    public void addEvent(Event event) {
        roomEventsAdapter.addEvent(event);
    }

    public void addEvents(Collection<Event> eventsCollection) {
        roomEventsAdapter.replaceEvents(eventsCollection);
        eventsList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                emptyListView.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(true);
                eventsList.setVisibility(View.INVISIBLE);
            }
        };
        getActivity().runOnUiThread(myRunnable);
    }

    @Override
    public void showEvents(final List<Event> events) {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                loadingView.setVisibility(View.GONE);
                eventsList.setVisibility(View.VISIBLE);
                addEvents(events);
            }
        };
        getActivity().runOnUiThread(myRunnable);
    }

    @Override
    public void showError() {
       showEmpty();
    }

    @Override
    public void showEmpty() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                loadingView.setVisibility(View.GONE);
                eventsList.setVisibility(View.GONE);
                emptyListView.setVisibility(View.VISIBLE);
            }
        };
        getActivity().runOnUiThread(myRunnable);
    }

}
