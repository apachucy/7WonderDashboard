package unii.counter.sevenwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.helper.Category;
import unii.counter.sevenwonders.helper.MenuHelper;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.sharedprefrences.SettingsPreferencesFactory;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.adapter.OnGridItemSelected;
import unii.counter.sevenwonders.view.fragment.GridCategoryFragment;
import unii.counter.sevenwonders.view.fragment.DashboardFragment;
import unii.counter.sevenwonders.view.fragment.InputPointsFragment;

public class DashboardActivity extends BaseActivity implements IPlayerScore, OnGridItemSelected {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private Map<String, PlayerScoreSheet> mPlayerScoreSheetMap;
    private ArrayList<String> mPlayerNameList;
    private TourGuide mTutorialHandler = null;


    private static final String TAG_FRAGMENT_SCORE_SHEET_TABLE = "DASHBOARD_LIST";
    private static final String TAG_FRAGMENT_CATEGORY_GRID = "ADD_POINT_VIEW";
    private static final String TAG_FRAGMENT_PLAYER_INPUT_POINTS = "TAG_FRAGMENT_PLAYER_INPUT_POINTS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        // if bundle has some elements
        // start with obtaining it
        Bundle b = getIntent().getExtras();

        if (b != null && b.containsKey(Config.BUNDLE_PLAYER_LIST)) {
            mPlayerNameList = b
                    .getStringArrayList(Config.BUNDLE_PLAYER_LIST);
            mPlayerScoreSheetMap = new HashMap<>();
            for (String name : mPlayerNameList) {
                mPlayerScoreSheetMap.put(name, new PlayerScoreSheet(name));
            }

        } else {
            // no players were passed
            // return to previous screen and finish this
            Intent intent = new Intent(DashboardActivity.this,
                    MenuActivity.class);
            startActivity(intent);
            DashboardActivity.this.finish();
        }
        setSupportActionBar(mToolBar);
        mToolBar.setLogo(R.drawable.ic_launcher);
        mToolBar.setLogoDescription(R.string.app_name);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(R.string.app_name);
        replaceFragments(new DashboardFragment(), TAG_FRAGMENT_SCORE_SHEET_TABLE, R.id.table_content_frame);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        setMenuActions((ImageView) menu.getItem(0).getActionView(), (ImageView) menu.getItem(1).getActionView());
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_dashboard) {
            replaceFragments(new DashboardFragment(), TAG_FRAGMENT_SCORE_SHEET_TABLE, R.id.table_content_frame);
        } else if (id == R.id.action_edit) {
            replaceFragments(new GridCategoryFragment(), TAG_FRAGMENT_CATEGORY_GRID, R.id.table_content_frame);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Map<String, PlayerScoreSheet> getPlayersScoreSheet() {
        return mPlayerScoreSheetMap;
    }

    @Override
    public List<String> getPlayerNames() {
        return mPlayerNameList;
    }

    @Override
    public PlayerScoreSheet getPlayerScoreSheet(String name) {
        return mPlayerScoreSheetMap.get(name);
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_PLAYER_INPUT_POINTS);
        if (fragment == null) {
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
        } else {
            //if current fragment is a fragment with inputting points - when pressing hardware back button let's go back to grid view
            replaceFragments(new GridCategoryFragment(), TAG_FRAGMENT_CATEGORY_GRID, R.id.table_content_frame);
        }


    }

    private void setMenuActions(ImageView dashboardButton, ImageView editButton) {
        // just adding some padding to look better
        int padding = MenuHelper.getHelperMenuPadding(getResources().getDisplayMetrics().density);
        dashboardButton.setPadding(padding, padding, padding, padding);
        editButton.setPadding(padding, padding, padding, padding);

        // set an image
        dashboardButton.setImageDrawable(this.getResources().getDrawable(R.mipmap.ic_dashboard));
        editButton.setImageDrawable(this.getResources().getDrawable(R.mipmap.ic_mode_edit));

        if (SettingsPreferencesFactory.getInstance().getFirstRun()) {
            Sequence sequence = new Sequence.SequenceBuilder().add(getDashboardTourGuide(dashboardButton), getEditTourGuide(editButton))
                    .setDefaultOverlay(new Overlay().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mTutorialHandler.next();
                        }
                    })).setContinueMethod(Sequence.ContinueMethod.OverlayListener).setDefaultPointer(new Pointer()).build();

            mTutorialHandler = TourGuide.init(this).playInSequence(sequence);
            SettingsPreferencesFactory.getInstance().setFirstRun(false);
        }
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragments(new DashboardFragment(), TAG_FRAGMENT_SCORE_SHEET_TABLE, R.id.table_content_frame);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragments(new GridCategoryFragment(), TAG_FRAGMENT_CATEGORY_GRID, R.id.table_content_frame);
            }
        });
    }

    private TourGuide getDashboardTourGuide(ImageView dashboardImageView) {
        ToolTip toolTipDashboard = new ToolTip()
                .setTitle(getString(R.string.tutorial_title))
                .setDescription(getString(R.string.tutorial_dashboard)).setBackgroundColor(this.getResources().getColor(R.color.accent))
                .setGravity(Gravity.LEFT | Gravity.BOTTOM);

        return TourGuide.init(this)
                .setToolTip(toolTipDashboard).playLater(dashboardImageView);
    }

    private TourGuide getEditTourGuide(ImageView editImageView) {

        ToolTip toolTipEdit = new ToolTip()
                .setTitle(getString(R.string.tutorial_title))
                .setDescription(getString(R.string.tutorial_edit)).setBackgroundColor(this.getResources().getColor(R.color.accent))
                .setGravity(Gravity.LEFT | Gravity.BOTTOM);

        return TourGuide.init(this)
                .setToolTip(toolTipEdit).playLater(editImageView);
    }

    @Override
    public void onCategorySelected(Category categorySelected) {
        Fragment fragment = new InputPointsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.BUNDLE_CATEGORY_SELECTED, categorySelected);
        fragment.setArguments(bundle);
        replaceFragments(fragment, TAG_FRAGMENT_PLAYER_INPUT_POINTS, R.id.table_content_frame);
    }
}
