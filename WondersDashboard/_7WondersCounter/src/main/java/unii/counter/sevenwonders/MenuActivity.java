package unii.counter.sevenwonders;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.validation.ValidationHelper;
import unii.counter.sevenwonders.view.adapter.PlayerListAdapter;
import unii.counter.sevenwonders.view.dialog.InfoDialog;

public class MenuActivity extends ActionBarActivity {

    private static final String TAG_DIALOG_INFO = "INFO_DIALOG_TAG";
    private PlayerListAdapter mPlayerListAdapter;
    private List<String> mPlayerList;

    @Bind(R.id.settings_playerListView)
    DynamicListView mPlayerDynamicListView;
    @Bind(R.id.settings_startGameButton)
    Button mStartGameButton;
    @Bind(R.id.settings_addPlayerButton)
    Button mAddPlayerButton;
    @Bind(R.id.settings_addPlayerEditText)
    EditText mPlayerNameEditText;
    @Bind(R.id.settings_warningTextView)
    TextView mWarningTextView;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        // creating new objects
        mPlayerList = new ArrayList<String>();

        mPlayerListAdapter = new PlayerListAdapter(this, mPlayerList);

        mPlayerDynamicListView.setAdapter(mPlayerListAdapter);
        mPlayerDynamicListView.enableSwipeToDismiss(mOnDismissCallback);
        mAddPlayerButton.setOnClickListener(mOnButtonClick);
        mStartGameButton.setOnClickListener(mOnButtonClick);

        hideWarningText();


        setSupportActionBar(mToolBar);
        mToolBar.setLogo(R.drawable.ic_launcher);
        mToolBar.setLogoDescription(R.string.app_name);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(R.string.app_name);


    }

    /**
     * Remove element from a list when swipe had place
     */
    private OnDismissCallback mOnDismissCallback = new OnDismissCallback() {

        @Override
        public void onDismiss(ViewGroup listView,
                              int[] reverseSortedPositions) {
            for (int position : reverseSortedPositions) {
                mPlayerList.remove(position);
            }
            mPlayerListAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private OnClickListener mOnButtonClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            String playerName;
            switch (v.getId()) {

                /**
                 * Start new activity
                 */
                case R.id.settings_startGameButton:
                    if (mPlayerList.size() < Config.MIN_PLAYERS) {
                        setWarningText(getString(R.string.warning_add_players));
                    } else {
                        hideWarningText();
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
                    break;

                /**
                 * Add a new player
                 */
                case R.id.settings_addPlayerButton:
                    // empty text view
                    if (ValidationHelper.isEditTextEmpty(mPlayerNameEditText, null)) {
                        setWarningText(getString(R.string.warning_player_name_not_empty));
                    }// name was previously added
                    else if (isNameOnList(playerName = mPlayerNameEditText
                            .getText().toString())) {
                        setWarningText(getString(R.string.warning_name_added));

                    } else {
                        hideWarningText();
                        mPlayerList.add(playerName);
                        mPlayerNameEditText.setText("");
                        mPlayerListAdapter.notifyDataSetChanged();

                    }
                    break;

                default:
                    Toast.makeText(MenuActivity.this,
                            getString(R.string.warning_not_yet_implemented),
                            Toast.LENGTH_LONG).show();
            }
        }
    };

    /*  private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(CompoundButton buttonView,
                                       boolean isChecked) {

              isExtensionGameOn = isChecked;
          }
      };
  */
    private void setWarningText(String warningText) {

        mWarningTextView.setVisibility(View.VISIBLE);
        mWarningTextView.setText(warningText);
    }

    private void hideWarningText() {
        mWarningTextView.setVisibility(View.INVISIBLE);
    }

    /**
     * Check if name was already added on a list
     *
     * @param name
     * @return if name was added previously return true in other cases return
     * false
     */
    private boolean isNameOnList(String name) {

        for (String nameAdded : mPlayerList) {
            if (name.equals(nameAdded)) {
                return true;
            }
        }

        return false;
    }

    private void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(TAG_DIALOG_INFO);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);


        DialogFragment newFragment = InfoDialog.newInstance(getString(R.string.info_dialog_title), getString(R.string.info_dialog_message));
        newFragment.show(ft, TAG_DIALOG_INFO);
    }
}
