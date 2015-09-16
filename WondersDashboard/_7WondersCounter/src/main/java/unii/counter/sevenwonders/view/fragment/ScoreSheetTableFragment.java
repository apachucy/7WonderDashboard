package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.adapter.TablePointsAdapter;

/**
 * Created by Arkadiusz Pachucy on 2015-09-04.
 */
public class ScoreSheetTableFragment extends Fragment {
    //TODO:  pkt najlepszego zawodnika
    //TODO: wybor fragmentow
    private Context mContext;
    private IPlayerScore mPlayerScore;
    private TablePointsAdapter mTablePointsAdapter;

    @Bind(R.id.table_playerListView)
    ListView mPlayerListView;
    @Bind(R.id.table_winner_playerNameTextView)
    TextView mWinnerNameTextView;
    @Bind(R.id.table_winner_playerPointsTextView)
    TextView mWinnerPointsTextView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mPlayerScore = (IPlayerScore) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_sheet_table, container, false);
        ButterKnife.bind(this, view);

        mTablePointsAdapter = new TablePointsAdapter(mContext,mPlayerScore.getPlayersScoreSheet(),mPlayerScore.getPlayerNames());
        mPlayerListView.setAdapter(mTablePointsAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
