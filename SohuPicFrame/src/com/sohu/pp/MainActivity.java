package com.sohu.pp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import de.greenrobot.event.EventBus;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		buildInterface();

		buildSlingMenu();
	}

	private void buildInterface() {
		// set the Content View
		setContentView(R.layout.frame_content);

		// set the Behind View
		setBehindContentView(R.layout.frame_menu);

		initRightFragments();

		initLeftFragments();
	}

	private void buildSlingMenu() {
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.drawer_menu_shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);

		sm.setOnClosedListener(new SlidingMenu.OnClosedListener() {
			@Override
			public void onClosed() {
				LocalBroadcastManager
						.getInstance(MainActivity.this)
						.sendBroadcast(
								new Intent(
										AppEventAction.SLIDING_MENU_CLOSED_BROADCAST));
				System.out.println("Closed LocalBroadcast");

				EventBus.getDefault().post(new SlidingMenuEvent("Closed"));
				System.out.println("Closed EventBus");
			}
		});

		sm.setOnOpenedListener(new OnOpenedListener() {

			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				LocalBroadcastManager
						.getInstance(MainActivity.this)
						.sendBroadcast(
								new Intent(
										AppEventAction.SLIDING_MENU_OPENED_BROADCAST));
				System.out.println("Opened LocalBroadcast");

				EventBus.getDefault().post(new SlidingMenuEvent("Opened"));
				System.out.println("Opened EventBus");
			}
		});
	}

	private void initLeftFragments() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();

		fragmentTransaction.replace(R.id.menu, getLeftMenuFragment(),
				LeftMenuFragment.class.getName());

		fragmentTransaction.commit();
	}

	private void initRightFragments() {
		PhotoFragment photo = getPhotoFragment();
		NoticeFragment notice = getNoticeFragment();
		SetFragment set = getSetFragment();

		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();

		if (!photo.isAdded()) {
			fragmentTransaction.add(R.id.content, photo,
					PhotoFragment.class.getName());
			fragmentTransaction.hide(photo);
		}

		if (!notice.isAdded()) {
			fragmentTransaction.add(R.id.content, notice,
					NoticeFragment.class.getName());
			fragmentTransaction.hide(notice);
		}

		if (!set.isAdded()) {
			fragmentTransaction.add(R.id.content, set,
					SetFragment.class.getName());
			fragmentTransaction.hide(set);
		}

		if (!fragmentTransaction.isEmpty()) {
			fragmentTransaction.commit();
			getSupportFragmentManager().executePendingTransactions();
		}
	}

	public LeftMenuFragment getLeftMenuFragment() {
		LeftMenuFragment fragment = ((LeftMenuFragment) getSupportFragmentManager()
				.findFragmentByTag(LeftMenuFragment.class.getName()));
		if (fragment == null) {
			fragment = new LeftMenuFragment();
		}
		return fragment;
	}

	public PhotoFragment getPhotoFragment() {
		PhotoFragment fragment = ((PhotoFragment) getSupportFragmentManager()
				.findFragmentByTag(PhotoFragment.class.getName()));
		if (fragment == null) {
			fragment = new PhotoFragment();
		}
		return fragment;
	}

	public NoticeFragment getNoticeFragment() {
		NoticeFragment fragment = ((NoticeFragment) getSupportFragmentManager()
				.findFragmentByTag(NoticeFragment.class.getName()));
		if (fragment == null) {
			fragment = new NoticeFragment();
		}
		return fragment;
	}

	public SetFragment getSetFragment() {
		SetFragment fragment = ((SetFragment) getSupportFragmentManager()
				.findFragmentByTag(SetFragment.class.getName()));
		if (fragment == null) {
			fragment = new SetFragment();
		}
		return fragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}