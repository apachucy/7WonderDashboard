package unii.counter.sevenwonders.pojo;

import unii.counter.sevenwonders.sharedprefrences.GameModeConfig;

public class PlayerScoreSheet {

    private String mPlayerName;
    private int mWarPoints;
    private int mGoldPoints;
    //private int mNegativeGoldPoints;
    private int mWonderPoints;
    private int mCivilizationPoints;
    private int mTradePoints;
    private int mSciencePoints;
    private int mGuildsPoints;
    private int mLeadersPoints;
    //black card/leaders card
    private int mGamePoints;

    public PlayerScoreSheet(String playerName) {
        mPlayerName = playerName;
        mWarPoints = 0;
        mGoldPoints = 0;
        mWonderPoints = 0;
        mCivilizationPoints = 0;
        mTradePoints = 0;
        mSciencePoints = 0;
        mGuildsPoints = 0;
        mLeadersPoints = 0;
        mGamePoints = 0;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String mPlayerName) {
        this.mPlayerName = mPlayerName;
    }

    public int getWarPoints() {
        return mWarPoints;
    }

    public void setWarPoints(int mWarPoints) {
        this.mWarPoints = mWarPoints;
    }

    public int getGoldPoints() {
        return mGoldPoints;
    }

    public void setGoldPoints(int mGoldPoints) {
        this.mGoldPoints = mGoldPoints;
    }


    public int getWonderPoints() {
        return mWonderPoints;
    }

    public void setWonderPoints(int mWonderPoints) {
        this.mWonderPoints = mWonderPoints;
    }

    public int getCivilizationPoints() {
        return mCivilizationPoints;
    }

    public void setCivilizationPoints(int mCivilizationPoints) {
        this.mCivilizationPoints = mCivilizationPoints;
    }

    public int getTradePoints() {
        return mTradePoints;
    }

    public void setTradePoints(int mTradePoints) {
        this.mTradePoints = mTradePoints;
    }

    public int getSciencePoints() {
        return mSciencePoints;
    }

    public void setSciencePoints(int mSciencePoints) {
        this.mSciencePoints = mSciencePoints;
    }

    public int getGuildsPoints() {
        return mGuildsPoints;
    }

    public void setGuildsPoints(int mGuildsPoints) {
        this.mGuildsPoints = mGuildsPoints;
    }

    public int getGamePoints() {
        return mGamePoints;
    }

    public void setGamePoints(int mGamePoints) {
        this.mGamePoints = mGamePoints;
    }

    public void calculateTotalPoints(int extension) {
        mGamePoints = mWarPoints + mGoldPoints + mWonderPoints + mCivilizationPoints + mTradePoints + mSciencePoints;
        switch (extension) {
            case GameModeConfig.GAME_MODE_LEADERS:
                mGamePoints += mLeadersPoints;
                break;
            default:
                break;
        }
    }

    public int getLeadersPoints() {
        return mLeadersPoints;
    }

    public void setLeadersPoints(int mLeadersPoints) {
        this.mLeadersPoints = mLeadersPoints;
    }
}
