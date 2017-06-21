package com.example.lorena.challengifier.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.MainMenuFragment;
import com.example.lorena.challengifier.fragments.s.challenge.AddChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.challenge.EditChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ViewChallengeFragment;
import com.example.lorena.challengifier.fragments.s.milestone.AddMilestoneFragment;
import com.example.lorena.challengifier.fragments.s.objective.AddObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.objective.EditObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.milestone.MilestoneListFragment;
import com.example.lorena.challengifier.fragments.s.objective.ObjectiveListFragment;
import com.example.lorena.challengifier.fragments.s.user.FrontScreenFragment;
import com.example.lorena.challengifier.fragments.s.user.LoginFragment;
import com.example.lorena.challengifier.utils.tools.DrawerListAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private List<String> mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        RxBus.get().register(this);
        mPlanetTitles = new ArrayList();
        mPlanetTitles.add("A");
        mPlanetTitles.add("B");
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        View v = inflator.inflate(R.layout.custom_actionbar_image, null);

        actionBar.setCustomView(v);*/

        this.getSupportActionBar().hide();
        // Set the adapter for the list view
        mDrawerList.setAdapter(new DrawerListAdapter(this, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_open) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle("drawer");
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FrontScreenFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Subscribe(tags = @Tag(MainMenuFragment.SHOW_SCREEN))
    public void showMainMenuFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainMenuFragment())
                .commit();
    }

    @Subscribe(tags = @Tag(ObjectiveListFragment.SHOW_SCREEN))
    public void showObjectiveListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ObjectiveListFragment())
                .addToBackStack(null)
                .commit();
    }


    @Subscribe(tags = @Tag(AddMilestoneFragment.SHOW_SCREEN))
    public void showAddMilestoneFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AddMilestoneFragment())
                .addToBackStack(null)
                .commit();
    }
    @Subscribe(tags = @Tag(AddObjectiveFragment.SHOW_SCREEN))
    public void showAddObjectiveFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AddObjectiveFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ViewChallengeFragment.SHOW_SCREEN))
    public void showViewChallengeFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ViewChallengeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(EditChallengeFragment.SHOW_SCREEN))
    public void showEditChallengeFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new EditChallengeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(AddChallengeFragment.SHOW_SCREEN))
    public void showAddChallengeFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AddChallengeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ChallengeListFragment.SHOW_SCREEN))
    public void showChallengeListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ChallengeListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(EditObjectiveFragment.SHOW_SCREEN))
    public void editObjectiveFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new EditObjectiveFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(MilestoneListFragment.SHOW_SCREEN))
    public void milestoneListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MilestoneListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(LoginFragment.SHOW_SCREEN))
    public void loginFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
    /* Called whenever we call invalidateOptionsMenu() */
   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles.get(position));
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
