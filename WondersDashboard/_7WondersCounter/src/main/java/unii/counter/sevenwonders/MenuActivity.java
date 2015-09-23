package unii.counter.sevenwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.TourGuide;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.helper.MenuHelper;
import unii.counter.sevenwonders.sharedprefrences.SettingsPreferencesFactory;
import unii.counter.sevenwonders.view.dialog.InfoDialog;
import unii.counter.sevenwonders.view.fragment.IMenuActivityBinder;
import unii.counter.sevenwonders.view.fragment.IMenuFragmentBinder;
import unii.counter.sevenwonders.view.fragment.MenuFragment;
import unii.counter.sevenwonders.view.fragment.SettingsFragment;

public class MenuActivity extends BaseActivity implements IMenuFragmentBinder {

    private static final String TAG_DIALOG_INFO = "INFO_DIALOG_TAG";
    private List<String> mPlayerList;
    private TourGuide mTutorialHandler = null;

    private static final String TAG_FRAGMENT_MENU = "TAG_FRAGMENT_MENU";
    private static final String TAG_FRAGMENT_SETTINGS = "TAG_FRAGMENT_SETTINGS";

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        // creating new objects
        mPlayerList = new ArrayList<String>();


        setSupportActionBar(mToolBar);
        mToolBar.setLogo(R.drawable.ic_launcher);
        mToolBar.setLogoDescription(R.string.app_name);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(R.string.app_name);
        replaceFragments(new MenuFragment(), TAG_FRAGMENT_MENU, R.id.fragment_content_frame);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_content_frame);
        if (currentFragment instanceof IMenuActivityBinder) {
            setMenuActions((ImageView) menu.getItem(0).getActionView(), (ImageView) menu.getItem(1).getActionView(), ((IMenuActivityBinder) currentFragment).getListTitle());
        } else {
            setMenuActions((ImageView) menu.getItem(0).getActionView(), (ImageView) menu.getItem(1).getActionView(), null);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_info) {
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public List<String> getPlayerNameList() {
        return mPlayerList;
    }

    @Override
    public void openDashboard() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Config.BUNDLE_PLAYER_LIST,
                (ArrayList<String>) mPlayerList);
        Intent intent = new Intent(MenuActivity.this,
                DashboardActivity.class);
        intent.putExtras(bundle);
        //Transition to new activity
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(MenuActivity.this).toBundle());
        MenuActivity.this.finish();

    }


    private void showDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(TAG_DIALOG_INFO);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);


        DialogFragment newFragment = InfoDialog.newInstance(getString(R.string.info_dialog_title), getString(R.string.info_dialog_message));
        newFragment.show(ft, TAG_DIALOG_INFO);
    }

    private void setMenuActions(ImageView aboutButton, ImageView settingsButton, TextView titleList) {
        // just adding some padding to look better
        int padding = MenuHelper.getHelperMenuPadding(getResources().getDisplayMetrics().density);

        aboutButton.setPadding(padding, padding, padding, padding);
        settingsButton.setPadding(padding, padding, padding, padding);

        // set an image
        aboutButton.setImageDrawable(this.getResources().getDrawable(R.mipmap.ic_info));
        settingsButton.setImageDrawable(this.getResources().getDrawable(R.mipmap.ic_settings_applications));
        //1rst run
        if (SettingsPreferencesFactory.getInstance().getFirstRun()) {
            Sequence sequence = null;
            if (titleList != null) {
                sequence = new Sequence.SequenceBuilder().add(bindTourGuideButton(getString(R.string.tutorial_title), aboutButton), bindTourGuideButton(getString(R.string.tutorial_settings), settingsButton), bindTourGuideButton(getString(R.string.tutorial_remove), titleList))
                        .setDefaultOverlay(new Overlay().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mTutorialHandler.next();
                            }
                        })).setContinueMethod(Sequence.ContinueMethod.OverlayListener).setDefaultPointer(new Pointer()).build();

            } else {
                sequence = new Sequence.SequenceBuilder().add(bindTourGuideButton(getString(R.string.tutorial_title), aboutButton), bindTourGuideButton(getString(R.string.tutorial_settings), settingsButton))
                        .setDefaultOverlay(new Overlay().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mTutorialHandler.next();
                            }
                        })).setContinueMethod(Sequence.ContinueMethod.OverlayListener).setDefaultPointer(new Pointer()).build();
            }

            mTutorialHandler = TourGuide.init(this).playInSequence(sequence);
        }
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        settingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentFound = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_MENU);
                if (fragmentFound != null) {
                    replaceFragments(new SettingsFragment(), TAG_FRAGMENT_SETTINGS, R.id.fragment_content_frame);
                } else {
                    replaceFragments(new MenuFragment(), TAG_FRAGMENT_MENU, R.id.fragment_content_frame);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_SETTINGS);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            //if current fragment is a fragment with inputting points - when pressing hardware back button let's go back to grid view
            replaceFragments(new MenuFragment(), TAG_FRAGMENT_MENU, R.id.fragment_content_frame);
        }

    }
}
