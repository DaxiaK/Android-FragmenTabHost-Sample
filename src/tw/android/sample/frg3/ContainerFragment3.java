package tw.android.sample.frg3;

import tw.android.sample.R;
import tw.android.sample.main.BaseContainerFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContainerFragment3 extends BaseContainerFragment
{
	private boolean mIsViewInited;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.container_fragment, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (!mIsViewInited)
		{
			mIsViewInited = true;
			initView();
		}
	}

	private void initView()
	{
		replaceFragment(new frg3(), false);
	}
}
