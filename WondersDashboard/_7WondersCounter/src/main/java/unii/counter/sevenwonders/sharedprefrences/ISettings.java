package unii.counter.sevenwonders.sharedprefrences;


public interface ISettings {

    public void setFirstRun(boolean isFirst);

    public boolean getFirstRun();

    public int getGameMode();

    public void setGameMode(int gameMode);
}
