package unii.counter.sevenwonders.sharedprefrences;


import android.content.SharedPreferences;

public class SettingsSharedPreferences implements ISettings {
    private SharedPreferences mSharedPreferences;
    private static final String FLAG_EXTENSION = "FLAG_EXTENSION" + SettingsSharedPreferences.class.getName();
    private static final String FLAG_FIRST_RUN = "FLAG_FIRST_RUN" + SettingsSharedPreferences.class.getName();

    public SettingsSharedPreferences(SharedPreferences shPref) {
        mSharedPreferences = shPref;
    }


    @Override
    public void setFirstRun(boolean isFirst) {
        mSharedPreferences.edit().putBoolean(FLAG_FIRST_RUN, isFirst).commit();
    }

    @Override
    public boolean getFirstRun() {
        return mSharedPreferences.getBoolean(FLAG_FIRST_RUN, true);
    }

    @Override
    public int getGameMode() {
        return mSharedPreferences.getInt(FLAG_EXTENSION, 0);
    }

    @Override
    public void setGameMode(int gameMode) {
        mSharedPreferences.edit().putInt(FLAG_EXTENSION, gameMode).commit();
    }
}
