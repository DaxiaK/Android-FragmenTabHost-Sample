package tw.android.sample.main;

import tw.android.sample.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/*
 * This base Fragment is special object for FragmentTabHost, it's helping to replace fragment and let it will not Overlap
 */
public class BaseContainerFragment extends Fragment
{
	public void replaceFragment(Fragment fragment, boolean addToBackStack)
	{
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		if (addToBackStack)
		{
			transaction.addToBackStack(null);
		}
		transaction.replace(R.id.container_framelayout, fragment);
		transaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}

	public boolean popFragment()
	{
		Log.e("test", "pop fragment: " + getChildFragmentManager().getBackStackEntryCount());
		boolean isPop = false;
		if (getChildFragmentManager().getBackStackEntryCount() > 0)
		{
			isPop = true;
			getChildFragmentManager().popBackStack();
		}
		return isPop;
	}
}
