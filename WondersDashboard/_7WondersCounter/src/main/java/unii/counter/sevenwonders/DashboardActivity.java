package unii.counter.sevenwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.fragment.PlayerScoreSheetFragment;
import unii.counter.sevenwonders.view.fragment.ScoreSheetTableFragment;

public class DashboardActivity extends ActionBarActivity implements IPlayerScore {

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private Map<String, PlayerScoreSheet> mPlayerScoreSheetMap;
    private ArrayList<String> mPlayerNameList;


    private static final String TAG_FRAGMENT_SCORE_SHEET_TABLE = "DASHBOARD_LIST";
    private static final String TAG_FRAGMENT_PLAYER_SCORE_SHEET = "ADD_POINT_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);//activity_dashobard
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
        replaceFragments(new ScoreSheetTableFragment(), TAG_FRAGMENT_SCORE_SHEET_TABLE);
    }

    private void replaceFragments(Fragment fragment, String tag) {
        Fragment fragmentFound = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentFound != null) {
            getSupportFragmentManager().beginTransaction().show(fragmentFound);

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.table_content_frame, fragment, tag).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_dashboard) {
            replaceFragments(new ScoreSheetTableFragment(), TAG_FRAGMENT_SCORE_SHEET_TABLE);
        } else if (id == R.id.action_edit) {
            replaceFragments(new PlayerScoreSheetFragment(), TAG_FRAGMENT_PLAYER_SCORE_SHEET);
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
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
