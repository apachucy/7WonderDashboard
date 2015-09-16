package unii.counter.sevenwonders.view.adapter;

import android.widget.TextView;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;

public interface IPlayerScoreSheetCallBack {

	public void calculatePoints(PlayerScoreSheet player, TextView displayValue);
}
