package unii.counter.sevenwonders.view.adapter;

import java.util.List;

import unii.counter.sevenwonders.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;

public class PlayerListAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private List<String> mPlayerList;

	public PlayerListAdapter(Context context, List<String> playerName) {
		mContext = context;
		mPlayerList = playerName;
	}

	@Override
	public int getCount() {
		return mPlayerList.size();
	}

	@Override
	public String getItem(int position) {
		return mPlayerList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = layoutInflater
				.inflate(R.layout.adapter_player_name, null);

		TextView playerNameTextView = (TextView) convertView
				.findViewById(R.id.adapter_playerName_playerNameTextView);
		playerNameTextView.setText(mPlayerList.get(position));
		return convertView;
	}

}
