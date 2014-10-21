package tw.android.sample.main;

import tw.android.sample.R;
import tw.android.sample.frg1.ContainerFragment1;
import tw.android.sample.frg2.ContainerFragment2;
import tw.android.sample.frg3.ContainerFragment3;
import tw.android.sample.frg4.ContainerFragment4;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
{
	/**
	 * UI
	 */
	// DRawe
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;
	// Tabhost
	private FragmentTabHost tabHost;
	// Viewpager
	private mselectAdapter adapter;
	private ViewPager viewpager;
	private String txt_Array[] = { "frg1", "frg2", "frg3", "frg4" };

	/**
	 * ==============
	 * Function
	 * ================
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * Draw
		 */
		// set UI
		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		)
		{
			public void onDrawerClosed(View view)
			{
				// getActionBar().setTitle("Close");
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView)
			{
				// getActionBar().setTitle("OPEN");
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		/**
		 * MyDefin UI
		 */
		LoadFragmentTabHost();
	}

	private void LoadFragmentTabHost()
	{
		tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realContainer);
		tabHost.addTab(tabHost.newTabSpec("frg1").setIndicator("frg1"), ContainerFragment1.class, null);
		tabHost.addTab(tabHost.newTabSpec("frg2").setIndicator("frg2"), ContainerFragment2.class, null);
		tabHost.addTab(tabHost.newTabSpec("frg3").setIndicator("frg3"), ContainerFragment3.class, null);
		tabHost.addTab(tabHost.newTabSpec("frg4").setIndicator("frg4"), ContainerFragment4.class, null);
		tabHost.setOnTabChangedListener(new OnTabChangeListener()
		{
			@Override
			public void onTabChanged(String arg0)
			{
				for (int i = 0; i < txt_Array.length; i++)
				{
					if (arg0.equals(txt_Array[i]))
					{
						viewpager.setCurrentItem(i);
					}
				}
			}
		});
		viewpager = (ViewPager) findViewById(R.id.realContainer);
		adapter = new mselectAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				tabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
			}
		});
	}

	/**
	 * ViewPager
	 */
	private class mselectAdapter extends FragmentPagerAdapter
	{
		public mselectAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			Fragment fragment = null;
			switch (position)
			{
				case 0:
					fragment = new ContainerFragment1();
					break;
				case 1:
					fragment = new ContainerFragment2();
					break;
				case 2:
					fragment = new ContainerFragment3();
					break;
				case 3:
					fragment = new ContainerFragment4();
					break;
			}
			return fragment;
		}

		@Override
		public int getCount()
		{
			return 4;
		}
	}

	/**
	 * Draw
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		// If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_map).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		// Handle action buttons
		switch (item.getItemId())
		{
			case R.id.action_map:
			{	// create intent to perform web search for this planet
				Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
				intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
				// catch event that there's no activity to handle intent
				if (intent.resolveActivity(getPackageManager()) != null)
				{
					startActivity(intent);
				}
				else
				{
					Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
				}
				return true;
			}
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			// selectItem(position);
		}
	}

	// }
	@Override
	public void setTitle(CharSequence title)
	{
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void onDestroy()
	{
		super.onDestroy();
	}
}
