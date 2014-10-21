package tw.android.sample.main;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter
{
	private final ArrayList<TabsInfo> lstTabsInfo;
	private final Context mContext;

	public static class TabsInfo
	{
		private final Class<?> clss_;
		private final Bundle args_;

		public TabsInfo(Class<?> clss, Bundle args)
		{
			this.clss_ = clss;
			this.args_ = args;
		}
	}

	public FragmentViewPagerAdapter(FragmentActivity activity, ArrayList<TabsInfo> lstTabsInfo)
	{
		super(activity.getSupportFragmentManager());
		mContext = activity;
		this.lstTabsInfo = lstTabsInfo;
	}

	@Override
	public Fragment getItem(int position)
	{
		// TODO Auto-generated method stub
		TabsInfo info = lstTabsInfo.get(position);
		return Fragment.instantiate(mContext, info.clss_.getName(), info.args_);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return lstTabsInfo.size();
	}
}
