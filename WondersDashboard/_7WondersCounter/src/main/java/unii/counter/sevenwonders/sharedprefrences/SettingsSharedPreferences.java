package unii.counter.sevenwonders.sharedprefrences;


import android.content.SharedPreferences;

public class SettingsSharedPreferences implements ISettings {
    private SharedPreferences mSharedPreferences;
    public static final String FLAG_EXTENSION = "FLAG_EXTENSION" + SettingsSharedPreferences.class.getName();
    public static final String FLAG_FIRST_RUN = "FLAG_FIRST_RUN" + SettingsSharedPreferences.class.getName();

    public SettingsSharedPreferences(SharedPreferences shPref) {
        mSharedPreferences = shPref;
    }


    @Override
    public boolean isExtensionSet() {
        return mSharedPreferences.getBoolean(FLAG_EXTENSION, false);
    }

    @Override
    public void setExtension(boolean setExtension) {
        mSharedPreferences.edit().putBoolean(FLAG_EXTENSION, setExtension).commit();
    }

    @Override
    public void setFirstRun(boolean isFirst) {
        mSharedPreferences.edit().putBoolean(FLAG_FIRST_RUN, isFirst).apply();
    }

    @Override
    public boolean getFirstRun() {
        return mSharedPreferences.getBoolean(FLAG_FIRST_RUN, true);
    }
}
