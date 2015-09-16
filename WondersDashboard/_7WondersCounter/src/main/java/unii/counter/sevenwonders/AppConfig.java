package unii.counter.sevenwonders;

import android.app.Application;

import unii.counter.sevenwonders.sharedprefrences.SettingsPreferencesFactory;
import unii.counter.sevenwonders.sharedprefrences.SharedPreferencesManager;
import unii.counter.sevenwonders.sharedprefrences.SettingsSharedPreferences;
/**
 * Created by Arkadiusz Pachucy on 2015-05-04.
 */
public class AppConfig extends Application {
    public final static String APP_SHARED_PREFERENCES = "APP_SHARED_PREFERENCES"+ AppConfig.class.getName();
    @Override
    public void onCreate() {
        super.onCreate();
        SettingsPreferencesFactory.configure(new SettingsSharedPreferences(
                new SharedPreferencesManager().getSharedPreferences(this,
                        APP_SHARED_PREFERENCES)));
    }
}
