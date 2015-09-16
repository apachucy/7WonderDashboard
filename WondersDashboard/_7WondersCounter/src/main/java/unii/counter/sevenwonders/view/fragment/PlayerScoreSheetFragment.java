package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.adapter.PlayerListAdapter;
import unii.counter.sevenwonders.view.dialog.IScienceDialogCallback;
import unii.counter.sevenwonders.view.dialog.ScienceDialog;

/**
 * Created by Arkadiusz Pachucy on 2015-09-03.
 */
public class PlayerScoreSheetFragment extends Fragment {
    private static final String SCIENCE_DIALOG_TAG = PlayerScoreSheet.class
            .getName() + "SCIENCE_DIALOG_TAG";

    private Context mContext;
    private IPlayerScore mPlayerScore;
    private String mPlayerName;
    private PlayerListAdapter mPlayerListAdapter;

    @Bind(R.id.calculate_playerNameSpinner)
    Spinner mPlayerNameSpinner;

    @Bind(R.id.calculate_player_points_warEditText)
    EditText mWarEditText;
    @Bind(R.id.calculate_player_points_goldEditText)
    EditText mGoldEditText;
    @Bind(R.id.calculate_player_points_wonderEditText)
    EditText mWonderEditText;
    @Bind(R.id.calculate_player_points_civicEditText)
    EditText mCivicEditText;
    @Bind(R.id.calculate_player_points_tradeEditText)
    EditText mTradeEditText;
    @Bind(R.id.calculate_player_points_guildEditText)
    EditText mGuildEditText;
    @Bind(R.id.calculate_player_points_scienceTextView)
    TextView mScienceTextView;
    @Bind(R.id.calculate_player_points_totalTextView)
    TextView mTotalTextView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mPlayerScore = (IPlayerScore) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate_player_points, container, false);
        ButterKnife.bind(this, view);

        mPlayerListAdapter = new PlayerListAdapter(mContext, mPlayerScore.getPlayerNames());
        mPlayerNameSpinner.setAdapter(mPlayerListAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void calculatePoints(TextView totalTextView) {
        totalTextView.setText(calculateScorePoints(mPlayerScore.getPlayerScoreSheet(mPlayerName)) + "");
    }

    private int calculateScorePoints(PlayerScoreSheet playerScoreSheet) {
        playerScoreSheet.setGamePoints(playerScoreSheet.getCivilizationPoints()
                + playerScoreSheet.getGoldPoints()
                + playerScoreSheet.getGuildsPoints()
                + playerScoreSheet.getSciencePoints()
                + playerScoreSheet.getTradePoints()
                + playerScoreSheet.getWarPoints()
                + playerScoreSheet.getWonderPoints());
        return playerScoreSheet.getGamePoints();
    }


    @OnTextChanged(R.id.calculate_player_points_warEditText)
    void onTextChangedWarEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setWarPoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnTextChanged(R.id.calculate_player_points_wonderEditText)
    void onTextChangedWonderEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setWonderPoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnTextChanged(R.id.calculate_player_points_civicEditText)
    void onTextChangedCivicEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setCivilizationPoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnTextChanged(R.id.calculate_player_points_goldEditText)
    void onTextChangedGoldEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setGoldPoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnTextChanged(R.id.calculate_player_points_guildEditText)
    void onTextChangedGuildEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setGuildsPoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnTextChanged(R.id.calculate_player_points_tradeEditText)
    void onTextChangedTradeEditText(CharSequence s) {
        int value;

        if (s.length() > 0) {
            value = Integer.parseInt(s.toString());
        } else {
            value = 0;
        }

        mPlayerScore.getPlayerScoreSheet(mPlayerName).setTradePoints(value);
        calculatePoints(mTotalTextView);
    }

    @OnClick(R.id.calculate_player_points_scienceTextView)
    void onScienceClick(View view) {
        DialogFragment dialogFragment = ScienceDialog
                .newInstance(new IScienceDialogCallback() {
                    @Override
                    public void setSciencePoints(
                            int sciencePoints) {
                        mPlayerScore.getPlayerScoreSheet(mPlayerName)
                                .setSciencePoints(sciencePoints);
                        mScienceTextView.setText(sciencePoints + "");
                        calculatePoints(mTotalTextView);
                    }
                });

        dialogFragment.show(
                ((Activity) mContext).getFragmentManager(),
                SCIENCE_DIALOG_TAG);
    }

    @OnItemSelected(R.id.calculate_playerNameSpinner)
    void onItemSelected(int position) {
        mPlayerName = mPlayerListAdapter.getItem(position);
        updateFields(mPlayerName);
    }

    private void updateFields(String playerName) {
        mWarEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getWarPoints() + "");
        mGoldEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getGoldPoints() + "");
        mCivicEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getCivilizationPoints() + "");
        mGuildEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getGuildsPoints() + "");
        mWonderEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getWonderPoints() + "");
        mTradeEditText.setText(mPlayerScore.getPlayerScoreSheet(playerName).getTradePoints() + "");
        mScienceTextView.setText(mPlayerScore.getPlayerScoreSheet(playerName).getSciencePoints() + "");
        mTotalTextView.setText(mPlayerScore.getPlayerScoreSheet(playerName).getGamePoints() + "");
    }
}
