package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.adapter.DividerItemDecorator;
import unii.counter.sevenwonders.view.adapter.TablePointsRecyclerAdapter;

/**
 * Created by Arkadiusz Pachucy on 2015-09-04.
 */
public class DashboardFragment extends Fragment {
    private Context mContext;
    private IPlayerScore mPlayerScore;

    @Bind(R.id.table_winner_playerNameTextView)
    TextView mWinnerNameTextView;
    @Bind(R.id.table_winner_playerPointsTextView)
    TextView mWinnerPointsTextView;

    @Bind(R.id.table_playerRecyclerView)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecorator(mContext, DividerItemDecorator.VERTICAL_LIST));
        mAdapter = new TablePointsRecyclerAdapter(mPlayerScore.getPlayersScoreSheet(), mPlayerScore.getPlayerNames());
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        calculatePlayerTotalPoints();
        List<PlayerScoreSheet> playerScoreSheetList = getBestPlayers();
        if (!playerScoreSheetList.isEmpty()) {
            mWinnerPointsTextView.setText(playerScoreSheetList.get(0).getGamePoints() + "");
            mWinnerNameTextView.setText(playerNameToString(playerScoreSheetList));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    private void calculatePlayerTotalPoints() {
        for(String playerName: mPlayerScore.getPlayerNames()){
            mPlayerScore.getPlayerScoreSheet(playerName).calculateTotalPoints();
        }
    }

    private List<PlayerScoreSheet> getBestPlayers() {
        List<PlayerScoreSheet> playerScoreSheetList = new ArrayList<>();

        Map playerData = mPlayerScore.getPlayersScoreSheet();
        int maxPoint = 0;
        for (String playerName : mPlayerScore.getPlayerNames()) {
            playerData.get(playerName);
            PlayerScoreSheet player = ((PlayerScoreSheet) playerData.get(playerName));
            if (maxPoint <= player.getGamePoints()) {
                if (maxPoint < player.getGamePoints()) {
                    playerScoreSheetList.clear();
                    maxPoint = player.getGamePoints();
                }
                playerScoreSheetList.add((PlayerScoreSheet) playerData.get(playerName));

            }
        }
        //if all data are not yet provided
        if (maxPoint == 0) {
            playerScoreSheetList.clear();
        }

        return playerScoreSheetList;
    }

    private String playerNameToString(List<PlayerScoreSheet> playerList) {
        String playerNames = getResources().getString(R.string.dashboard_player_most_points) + " ";
        for (PlayerScoreSheet player : playerList) {
            playerNames += player.getPlayerName() + " ";
        }
        return playerNames;
    }
}
