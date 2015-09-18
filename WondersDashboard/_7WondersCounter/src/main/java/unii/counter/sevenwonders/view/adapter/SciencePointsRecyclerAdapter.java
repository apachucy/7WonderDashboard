package unii.counter.sevenwonders.view.adapter;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.view.dialog.IScienceDialogCallback;
import unii.counter.sevenwonders.view.dialog.ScienceDialog;

/**
 * Created by apachucy on 2015-09-18.
 */
public class SciencePointsRecyclerAdapter extends RecyclerView.Adapter<SciencePointsRecyclerAdapter.ViewHolder> {
    private static final String SCIENCE_DIALOG_TAG = SciencePointsRecyclerAdapter.class
            .getName() + "SCIENCE_DIALOG_TAG";
    private Map<String, PlayerScoreSheet> mPlayerMap;
    private List<String> mPlayerNameList;
    private Context mContext;

    public SciencePointsRecyclerAdapter(Context context, Map<String, PlayerScoreSheet> playerMap, List<String> playerNameList) {
        mPlayerMap = playerMap;
        mPlayerNameList = playerNameList;
        mContext = context;
    }

    @Override
    public SciencePointsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_table_points, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SciencePointsRecyclerAdapter.ViewHolder viewHolder, int position) {
        viewHolder.playerNameTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getPlayerName());
        viewHolder.playerSciencePointsTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getSciencePoints() + "");
    }

    @Override
    public int getItemCount() {
        return mPlayerMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.table_points_playerNameTextView)
        TextView playerNameTextView;
        @Bind(R.id.table_points_playerPointsTextView)
        TextView playerSciencePointsTextView;

        @OnClick(R.id.table_points_playerPointsTextView)
        public void onScienceCalculationClick() {
            DialogFragment dialogFragment = ScienceDialog
                    .newInstance(new IScienceDialogCallback() {
                        @Override
                        public void setSciencePoints(
                                int sciencePoints) {
                            mPlayerMap.get(mPlayerNameList.get(getPosition())).setSciencePoints(sciencePoints);

                            playerSciencePointsTextView.setText(sciencePoints + "");

                        }
                    });

            dialogFragment.show(
                    ((Activity) mContext).getFragmentManager(),
                    SCIENCE_DIALOG_TAG);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
