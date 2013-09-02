package com.sohu.pp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.greenrobot.event.EventBus;

public class PhotoTimeFragment extends Fragment {

	ProgressWheel progress;

	BroadcastReceiver slidingMenuClosedReceiver;
	BroadcastReceiver slidingMenuOpenedReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		slidingMenuClosedReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// progress.pasueSpinning();
				System.out.println("Closed LocalBroadcast");
			}
		};

		slidingMenuOpenedReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// progress.pasueSpinning();
				System.out.println("Opened LocalBroadcast");
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// inflater the layout
		View view = inflater.inflate(R.layout.photo_time_fragment, null);
		progress = (ProgressWheel) view
				.findViewById(R.id.photo_notice_progress);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		progress.spin();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
				slidingMenuClosedReceiver,
				new IntentFilter(AppEventAction.SLIDING_MENU_CLOSED_BROADCAST));

		LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
				slidingMenuOpenedReceiver,
				new IntentFilter(AppEventAction.SLIDING_MENU_OPENED_BROADCAST));

		EventBus.getDefault().register(this);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
				slidingMenuClosedReceiver);
		LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
				slidingMenuOpenedReceiver);

		EventBus.getDefault().unregister(this);
	}

	public void onEvent(SlidingMenuEvent event) {
//	public void onEventMainThread(SlidingMenuEvent event) {
//	public void onEventBackgroundThread(SlidingMenuEvent event) {
//	public void onEventAsync(SlidingMenuEvent event) {
		if (event.getEventName().equals("Closed")) {
			System.out.println("Closed EventBus");
		} else {
			System.out.println("Opened EventBus");
		}
	}

}
