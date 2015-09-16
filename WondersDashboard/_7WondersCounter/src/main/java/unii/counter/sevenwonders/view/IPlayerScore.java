package unii.counter.sevenwonders.view;

import java.util.List;
import java.util.Map;

import unii.counter.sevenwonders.pojo.PlayerScoreSheet;

/**
 * Created by Arkadiusz Pachucy on 2015-09-03.
 */
public interface IPlayerScore {
    public Map<String,PlayerScoreSheet> getPlayersScoreSheet();
    public List<String> getPlayerNames();
    public PlayerScoreSheet getPlayerScoreSheet(String name);
}
