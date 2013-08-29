package com.sohu.pp;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LeftMenuFragment extends Fragment {

	public static final int PHOTO_INDEX = 0;
	public static final int NOTICE_INDEX = 1;
	public static final int SET_INDEX = 2;

	private LeftMenuLayout layout = null;

	private int currentIndex = PHOTO_INDEX;
	private SparseArray<Fragment> rightFragments = new SparseArray<Fragment>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.sliding_drawer_contents, null);

		layout = new LeftMenuLayout();
		layout.photo = (LinearLayout) view.findViewById(R.id.btn_drawer_photo);
		layout.notice = (LinearLayout) view
				.findViewById(R.id.btn_drawer_notice);
		layout.set = (LinearLayout) view.findViewById(R.id.btn_drawer_set);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		rightFragments.append(PHOTO_INDEX,
				((MainActivity) getActivity()).getPhotoFragment());
		rightFragments.append(NOTICE_INDEX,
				((MainActivity) getActivity()).getNoticeFragment());
		rightFragments.append(SET_INDEX,
				((MainActivity) getActivity()).getSetFragment());

		switchRightFragment(currentIndex);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		layout.photo.setOnClickListener(onClickListener);
		layout.notice.setOnClickListener(onClickListener);
		layout.set.setOnClickListener(onClickListener);
	}

	public void switchRightFragment(int position) {
		switch (position) {
		case PHOTO_INDEX:
			showPhotoFragment(true);
			break;
		case NOTICE_INDEX:
			showNoticeFragment(true);
			break;
		case SET_INDEX:
			showSetFragment(true);
			break;
		}

		drawButtonsBackground(position);
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_drawer_photo:
				showPhotoFragment(false);
				currentIndex = PHOTO_INDEX;
				break;
			case R.id.btn_drawer_notice:
				showNoticeFragment(false);
				currentIndex = NOTICE_INDEX;
				break;
			case R.id.btn_drawer_set:
				showSetFragment(false);
				currentIndex = SET_INDEX;
				break;
			}

			drawButtonsBackground(currentIndex);
		}
	};

	private boolean showPhotoFragment(boolean reset) {
		if (currentIndex == PHOTO_INDEX && !reset) {
			((MainActivity) getActivity()).getSlidingMenu().showContent();
			return true;
		}

		FragmentTransaction ft = getFragmentManager().beginTransaction();

		ft.hide(rightFragments.get(PHOTO_INDEX));
		ft.hide(rightFragments.get(NOTICE_INDEX));
		ft.hide(rightFragments.get(SET_INDEX));

		Fragment m = rightFragments.get(PHOTO_INDEX);

		((PhotoFragment) m).showMsg("Photo Fragment");

		ft.show(m);

		ft.commit();

		((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
				SlidingMenu.TOUCHMODE_FULLSCREEN);
		((MainActivity) getActivity()).getSlidingMenu().showContent();

		return false;
	}

	private boolean showNoticeFragment(boolean reset) {
		if (currentIndex == NOTICE_INDEX && !reset) {
			((MainActivity) getActivity()).getSlidingMenu().showContent();
			return true;
		}

		FragmentTransaction ft = getFragmentManager().beginTransaction();

		ft.hide(rightFragments.get(PHOTO_INDEX));
		ft.hide(rightFragments.get(NOTICE_INDEX));
		ft.hide(rightFragments.get(SET_INDEX));

		Fragment m = rightFragments.get(NOTICE_INDEX);

		((NoticeFragment) m).showMsg("Notice Fragment");

		ft.show(m);

		ft.commit();

		((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
				SlidingMenu.TOUCHMODE_FULLSCREEN);
		((MainActivity) getActivity()).getSlidingMenu().showContent();

		return false;
	}

	private boolean showSetFragment(boolean reset) {
		if (currentIndex == SET_INDEX && !reset) {
			((MainActivity) getActivity()).getSlidingMenu().showContent();
			return true;
		}

		FragmentTransaction ft = getFragmentManager().beginTransaction();

		ft.hide(rightFragments.get(PHOTO_INDEX));
		ft.hide(rightFragments.get(NOTICE_INDEX));
		ft.hide(rightFragments.get(SET_INDEX));

		Fragment m = rightFragments.get(SET_INDEX);

		((SetFragment) m).showMsg("Set Fragment");

		ft.show(m);

		ft.commit();

		((MainActivity) getActivity()).getSlidingMenu().setTouchModeAbove(
				SlidingMenu.TOUCHMODE_FULLSCREEN);
		((MainActivity) getActivity()).getSlidingMenu().showContent();

		return false;
	}

	private void drawButtonsBackground(int position) {
		layout.photo.setBackgroundResource(R.drawable.drawer_menu_btn);
		layout.notice.setBackgroundResource(R.drawable.drawer_menu_btn);
		layout.set.setBackgroundResource(R.drawable.drawer_menu_btn);

		switch (position) {
		case PHOTO_INDEX:
			layout.photo.setBackgroundResource(R.color.ics_blue_semi);
			break;
		case NOTICE_INDEX:
			layout.notice.setBackgroundResource(R.color.ics_blue_semi);
			break;
		case SET_INDEX:
			layout.set.setBackgroundResource(R.color.ics_blue_semi);
			break;
		}
	}

	private class LeftMenuLayout {
		LinearLayout photo;
		LinearLayout notice;
		LinearLayout set;
	}

}
