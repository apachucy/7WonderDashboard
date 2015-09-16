package unii.counter.sevenwonders.sharedprefrences;


import android.content.SharedPreferences;

public class SettingsSharedPreferences implements ISettings {
	private SharedPreferences mSharedPreferences;
    public static String FLAG_EXTENSION = "FLAG_EXTENSION" + SettingsSharedPreferences.class.getName();
	public SettingsSharedPreferences(SharedPreferences shPref) {
		mSharedPreferences = shPref;
	}


    @Override
    public boolean isExtensionSet() {
        return mSharedPreferences.getBoolean(FLAG_EXTENSION,false);
    }

    @Override
    public void setExtension(boolean setExtension) {
        mSharedPreferences.edit().putBoolean(FLAG_EXTENSION,setExtension).apply();
    }
}
