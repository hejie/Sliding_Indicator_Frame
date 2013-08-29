package com.sohu.pp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

public class PhotoFragment extends Fragment {

	private static final String[] CONTENT = new String[] { "时间", "地点", "相册" };

	private ImageView toggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final Context contextThemeWrapper = new ContextThemeWrapper(
				getActivity(), R.style.Theme_PageIndicatorDefaults);
		// clone the inflater using the ContextThemeWrapper
		LayoutInflater localInflater = inflater
				.cloneInContext(contextThemeWrapper);

		// inflater the layout
		View view = localInflater.inflate(R.layout.photo_fragment, null);
		toggle = (ImageView) view.findViewById(R.id.btn_photo_toggle);
		toggle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				// TODO Auto-generated method stub
				((MainActivity) getActivity()).getSlidingMenu().toggle();
			}
		});

		FragmentPagerAdapter adapter = new GoogleMusicAdapter(getActivity()
				.getSupportFragmentManager());

		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) view
				.findViewById(R.id.indicator);
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int paramInt) {
				// TODO Auto-generated method stub
				SlidingMenu sm = ((MainActivity) getActivity())
						.getSlidingMenu();
				if (paramInt == 0) {
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else {
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}
			}

			@Override
			public void onPageScrolled(int paramInt1, float paramFloat,
					int paramInt2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int paramInt) {
				// TODO Auto-generated method stub

			}
		});
		indicator.setViewPager(pager);

		return view;
	}

	public void showMsg(String msg) {
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {

		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}

	}

}
