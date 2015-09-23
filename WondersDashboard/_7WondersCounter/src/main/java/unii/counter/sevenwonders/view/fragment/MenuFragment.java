package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.validation.ValidationHelper;
import unii.counter.sevenwonders.view.adapter.PlayerListAdapter;

/**
 * Created by apachucy on 2015-09-22.
 */
public class MenuFragment extends Fragment implements IMenuActivityBinder {

    private Context mContext;
    private IMenuFragmentBinder mMenuFragment;
    private PlayerListAdapter mPlayerListAdapter;

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

    @Bind(R.id.settings_playerListTextView)
    TextView mTitlePlayerList;

    @OnClick(R.id.settings_addPlayerButton)
    void onAddPlayerClick(View v) {
        String playerName;
        // empty text view
        if (ValidationHelper.isEditTextEmpty(mPlayerNameEditText, null)) {
            setWarningText(getString(R.string.warning_player_name_not_empty));
        }// name was previously added
        else if (isNameOnList(playerName = mPlayerNameEditText
                .getText().toString())) {
            setWarningText(getString(R.string.warning_name_added));

        } else {
            hideWarningText();
            mMenuFragment.getPlayerNameList().add(playerName);
            mPlayerNameEditText.setText("");
            mPlayerListAdapter.notifyDataSetChanged();

        }
    }

    @OnClick(R.id.settings_startGameButton)
    void onGameStartClick(View v) {
        if (mMenuFragment.getPlayerNameList().size() < Config.MIN_PLAYERS) {
            setWarningText(getString(R.string.warning_add_players));
        } else {
            hideWarningText();
            mMenuFragment.openDashboard();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mMenuFragment = (IMenuFragmentBinder) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        mPlayerListAdapter = new PlayerListAdapter(mContext, mMenuFragment.getPlayerNameList());

        mPlayerDynamicListView.setAdapter(mPlayerListAdapter);
        mPlayerDynamicListView.enableSwipeToDismiss(mOnDismissCallback);

        hideWarningText();

        return view;
    }

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

        for (String nameAdded : mMenuFragment.getPlayerNameList()) {
            if (name.equals(nameAdded)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Remove element from a list when swipe had place
     */
    private OnDismissCallback mOnDismissCallback = new OnDismissCallback() {

        @Override
        public void onDismiss(ViewGroup listView,
                              int[] reverseSortedPositions) {
            for (int position : reverseSortedPositions) {
                mMenuFragment.getPlayerNameList().remove(position);
            }
            mPlayerListAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public TextView getListTitle() {
        return mTitlePlayerList;
    }
}
