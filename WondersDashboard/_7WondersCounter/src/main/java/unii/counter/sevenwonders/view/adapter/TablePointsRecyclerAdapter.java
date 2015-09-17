package unii.counter.sevenwonders.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;

/**
 * Created by apachucy on 2015-09-17.
 */
public class TablePointsRecyclerAdapter extends RecyclerView.Adapter<TablePointsRecyclerAdapter.ViewHolder> {
    private Map<String, PlayerScoreSheet> mPlayerMap;
    private List<String> mPlayerNameList;

    public TablePointsRecyclerAdapter(Map<String, PlayerScoreSheet> playerMap, List<String> playerNameList) {
        mPlayerMap = playerMap;
        mPlayerNameList = playerNameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_table_points, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.playerNameTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getPlayerName());
        viewHolder.playerPointsTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getGamePoints()+"");
    }

    @Override
    public int getItemCount() {
        return mPlayerMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.table_points_playerNameTextView)
        TextView playerNameTextView;
        @Bind(R.id.table_points_playerPointsTextView)
        TextView playerPointsTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
