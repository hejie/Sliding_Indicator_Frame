package com.sohu.pp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

public class PhotoFragment extends Fragment {

	private String[] photoTabs = null;
	private SparseArray<Fragment> photoTabFragments = new SparseArray<Fragment>();

	private ImageView toggle = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		initResource();
	}

	private void initResource() {
		Resources res = getResources();
		photoTabs = res.getStringArray(R.array.photo_tab_array);

		photoTabFragments.append(0, new PhotoTimeFragment());
		photoTabFragments.append(1, TestFragment.newInstance(photoTabs[1]));
		photoTabFragments.append(2, TestFragment.newInstance(photoTabs[2]));
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

		FragmentPagerAdapter adapter = new PhotoFragmentPagerAdapter(
				getActivity().getSupportFragmentManager());

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

	private class PhotoFragmentPagerAdapter extends FragmentPagerAdapter {

		public PhotoFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return photoTabFragments.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return photoTabs[position % photoTabs.length];
		}

		@Override
		public int getCount() {
			return photoTabs.length;
		}

	}

}
