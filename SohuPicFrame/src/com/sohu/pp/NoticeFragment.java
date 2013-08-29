package com.sohu.pp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoticeFragment extends Fragment {

	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// inflater the layout
		View view = inflater.inflate(R.layout.notice_fragment, null);
		tv = (TextView) view.findViewById(R.id.text);
		return view;
	}

	public void showMsg(String msg) {
		tv.setText(msg);
	}

}
