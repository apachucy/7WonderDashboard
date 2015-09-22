package unii.counter.sevenwonders.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;

/**
 * Created by apachucy on 2015-09-18.
 */
public class PlayerPointsRecyclerAdapter extends RecyclerView.Adapter<PlayerPointsRecyclerAdapter.ViewHolder> {
    private final static String LEADING_ZERO_PATTERN = "^0+(?!$)";
    private Map<String, PlayerScoreSheet> mPlayerMap;
    private List<String> mPlayerNameList;
    private PointsType mModeSelected;


    public PlayerPointsRecyclerAdapter(Map<String, PlayerScoreSheet> playerMap, List<String> playerNameList, PointsType modeSelected) {
        mPlayerMap = playerMap;
        mPlayerNameList = playerNameList;
        mModeSelected = modeSelected;
    }

    @Override
    public PlayerPointsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_player_points, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerPointsRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.playerNameTextView.setText(mPlayerMap.get(mPlayerNameList.get(viewHolder.getPosition())).getPlayerName());
        viewHolder.playerPointsEditText.setText(getPoints(i) + "");


    }

    @Override
    public int getItemCount() {
        return mPlayerMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.player_playerNameTextView)
        TextView playerNameTextView;
        @Bind(R.id.player_playerPointsEditText)
        EditText playerPointsEditText;

        @OnTextChanged(R.id.player_playerPointsEditText)
        void onTextChangedWarEditText(CharSequence s) {
            int value;


            if (s.length() > 0) {
                String input = s.toString();
                if (s.length() > 1) {
                    input = s.toString().replaceFirst(LEADING_ZERO_PATTERN, "");
                    if (!s.toString().equals(input)) {
                        playerPointsEditText.setText(input);
                        playerPointsEditText.setSelection(input.length());
                    }
                }
                value = Integer.parseInt(input);
            } else {
                value = 0;
            }
            setPoints(value, getPosition());
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    private void setPoints(int points, int playerPosition) {
        switch (mModeSelected) {
            case POINTS_WAR:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setWarPoints(points);
                break;
            case POINTS_GOLD:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setGoldPoints(points);
                break;
            case POINTS_WONDER:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setWonderPoints(points);
                break;
            case POINTS_CIVIC:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setCivilizationPoints(points);
                break;
            case POINTS_TRADE:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setTradePoints(points);
                break;
            case POINTS_GUILD:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setGuildsPoints(points);
                break;
            case POINTS_SCIENCE:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setSciencePoints(points);
                break;
            case POINTS_LEADERS:
                mPlayerMap.get(mPlayerNameList.get(playerPosition)).setLeadersPoints(points);

            default:
                break;
        }
    }

    private int getPoints(int playerPosition) {
        int points = 0;
        switch (mModeSelected) {
            case POINTS_WAR:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getWarPoints();
                break;
            case POINTS_GOLD:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getGoldPoints();
                break;
            case POINTS_WONDER:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getWonderPoints();
                break;
            case POINTS_CIVIC:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getCivilizationPoints();
                break;
            case POINTS_TRADE:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getTradePoints();
                break;
            case POINTS_GUILD:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getGuildsPoints();
                break;
            case POINTS_SCIENCE:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getSciencePoints();
                break;
            case POINTS_LEADERS:
                points = mPlayerMap.get(mPlayerNameList.get(playerPosition)).getLeadersPoints();
            default:
                break;
        }

        return points;
    }
}
