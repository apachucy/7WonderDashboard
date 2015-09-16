package unii.counter.sevenwonders.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;

/**
 * Created by Arkadiusz Pachucy on 2015-09-04.
 */
public class TablePointsAdapter extends BaseAdapter {
    private Context mContext;
    private Map<String, PlayerScoreSheet> mPlayerMap;
    private List<String> mPlayerNameList;

    public TablePointsAdapter(Context context, Map<String, PlayerScoreSheet> playerMap, List<String> playerNameList) {
        mContext = context;
        mPlayerMap = playerMap;
        mPlayerNameList = playerNameList;
    }

    @Override
    public int getCount() {
        return mPlayerMap.size();
    }

    @Override
    public Object getItem(int position) {
       /* int i = 0;
        String mapKey = null;
        for (String key : mPlayerMap.keySet()) {
            if (i == position) {
                mapKey = key;
                break;
            }
            i++;
        }*/
        return mPlayerMap.get(mPlayerNameList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(
                    R.layout.adapter_table_points, null);

            viewHolder = new ViewHolder(convertView);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.playerNameTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getPlayerName());
        viewHolder.playerPointsTextView.setText(mPlayerMap.get(mPlayerNameList.get(position)).getGamePoints()+"");
        return convertView;
    }

    static class ViewHolder {
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Bind(R.id.table_points_playerNameTextView)
        TextView playerNameTextView;
        @Bind(R.id.table_points_playerPointsTextView)
        TextView playerPointsTextView;
    }
}
