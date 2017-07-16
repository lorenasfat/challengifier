package com.example.lorena.challengifier.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.MainMenuFragment;
import com.example.lorena.challengifier.fragments.s.challenge.AddChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ArchivedChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.challenge.EditChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.MyChallengesListFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ViewChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ViewMyChallengeFragment;
import com.example.lorena.challengifier.fragments.s.milestone.AddMilestoneFragment;
import com.example.lorena.challengifier.fragments.s.milestone.MilestoneListFragment;
import com.example.lorena.challengifier.fragments.s.objective.AddObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.objective.EditObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.objective.ObjectiveHistoryListFragment;
import com.example.lorena.challengifier.fragments.s.objective.ObjectiveListFragment;
import com.example.lorena.challengifier.fragments.s.objective.ObjectivesForReviewListFragment;
import com.example.lorena.challengifier.fragments.s.objective.ReviewObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.objective.ViewObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.planning.step.AddPlanningStepFragment;
import com.example.lorena.challengifier.fragments.s.planning.step.PlanningStepListFragment;
import com.example.lorena.challengifier.fragments.s.user.FrontScreenFragment;
import com.example.lorena.challengifier.fragments.s.user.LeaderboardFragment;
import com.example.lorena.challengifier.fragments.s.user.LoginFragment;
import com.example.lorena.challengifier.models.User;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.LoginService;
import com.example.lorena.challengifier.services.external.services.services.ApiLoginService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenActivity extends AppCompatActivity {
    Activity activity;

    public static DrawerLayout mDrawerLayout;
    public static ActionBarDrawerToggle mDrawerToggle;

    private Button buttonChallenges;
    private Button buttonObjectives;
    private Button buttonStats;
    private static TextView username;
    private static TextView points;

    public static void updateDrawerContent() {
        String userName = SessionUser.loggedInUser.getUsername();
        String formatted = userName.substring(0, userName.indexOf("@"));
        username.setText(formatted);
        points.setText(String.valueOf(SessionUser.loggedInUser.getPoints()) + " points");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        RxBus.get().register(this);
//SessionUser.clearSession(this);
        activity = this;
        setUpLauncher();
    }

    private void setUpLauncher() {
        if (isConnected(getApplicationContext())) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.ic_prize);
            getSupportActionBar().setDisplayUseLogoEnabled(true);

            buttonChallenges = (Button) findViewById(R.id.buttonMyChallenges);
            buttonObjectives = (Button) findViewById(R.id.buttonObjectives);
            buttonObjectives.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            });

            buttonStats = (Button) findViewById(R.id.buttonStats);
            username = (TextView) findViewById(R.id.username);
            points = (TextView) findViewById(R.id.points);

            TextView logOut = (TextView)findViewById(R.id.textViewLogOut);
            logOut.setClickable(true);
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Are you sure you want to log out?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FlowAids.ClearCache();
                                    SessionUser.clearSession(activity);
                                    RxBus.get().post(FrontScreenFragment.SHOW_SCREEN, true);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });

            ImageView homeScreen = (ImageView) findViewById(R.id.homeScreen);
            ImageView icon = (ImageView) findViewById(R.id.imageUserIcon);
            icon.setClickable(true);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginService service = ApiLoginService.getService();
                    Call<User> call = service.getInfo(SessionUser.loggedInUser.getUsername());

                    try {
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                SessionUser.loggedInUser = (User)response.body();
                                SessionUser.saveSession(activity,SessionUser.authToken, SessionUser.loggedInUser);
                                updateDrawerContent();
                                Toast.makeText(activity.getApplicationContext(), "Account refreshed", Toast.LENGTH_LONG).show();
                                // The network call was a success and we got a response
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(activity.getApplicationContext(), "Oops! :(", Toast.LENGTH_LONG).show();
                                // the network call was a failure
                                // TODO: handle error
                                t.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            if (SessionUser.isSessionStarted(MainScreenActivity.this)) {
            }

            homeScreen.setClickable(true);
            homeScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(MainMenuFragment.SHOW_SCREEN, true);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);

                }
            });

            buttonStats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(LeaderboardFragment.SHOW_SCREEN, true);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            });

            buttonChallenges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(MyChallengesListFragment.SHOW_SCREEN, true);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            });

            this.getSupportActionBar().hide();

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                    R.string.drawer_open, R.string.drawer_open) {
                @Override
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getSupportActionBar().setTitle(FlowAids.BackUpTitle);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle("Challenger account");
                }
            };

            mDrawerLayout.closeDrawer(Gravity.LEFT);

            // Set the drawer toggle as the DrawerListener
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();

            if (!SessionUser.isSessionStarted(MainScreenActivity.this)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new FrontScreenFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new MainMenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            boolean isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

            if (isWifi || isMobile) {
                return true;
            } else {
                showDialog();
                return false;
            }
        } else {
            showDialog();
            return false;
        }
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Turn mobile data on or come back later")
                .setCancelable(false)
                .setPositiveButton("Turn mobile data on", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Subscribe(tags = @Tag(ViewObjectiveFragment.SHOW_SCREEN))
    public void showViewObjectiveFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ViewObjectiveFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(FrontScreenFragment.SHOW_SCREEN))
    public void showFrontScreenFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FrontScreenFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(LeaderboardFragment.SHOW_SCREEN))
    public void showLeaderboardFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LeaderboardFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ArchivedChallengeListFragment.SHOW_SCREEN))
    public void showArchivedChallengeListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ArchivedChallengeListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ObjectiveHistoryListFragment.SHOW_SCREEN))
    public void showObjectiveHistoryListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ObjectiveHistoryListFragment())
                .addToBackStack(null)
                .commit();
    }
    @Subscribe(tags = @Tag(MainMenuFragment.SHOW_SCREEN))
    public void showMainMenuFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainMenuFragment())
                .commit();
    }

    @Subscribe(tags = @Tag(ViewMyChallengeFragment.SHOW_SCREEN))
    public void showViewMyChallengeFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ViewMyChallengeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ObjectiveListFragment.SHOW_SCREEN))
    public void showObjectiveListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ObjectiveListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ObjectivesForReviewListFragment.SHOW_SCREEN))
    public void showObjectiveForReviewListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ObjectivesForReviewListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(ReviewObjectiveFragment.SHOW_SCREEN))
    public void showReviewObjectiveFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ReviewObjectiveFragment())
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

    @Subscribe(tags = @Tag(MyChallengesListFragment.SHOW_SCREEN))
    public void showMyChallengesListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MyChallengesListFragment())
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

    @Subscribe(tags = @Tag(PlanningStepListFragment.SHOW_SCREEN))
    public void showPlanningStepListFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PlanningStepListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Subscribe(tags = @Tag(AddPlanningStepFragment.SHOW_SCREEN))
    public void showAddPlanningStepFragment(Boolean loginSuccess) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AddPlanningStepFragment())
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
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpLauncher();
    }

}
