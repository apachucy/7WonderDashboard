package unii.counter.sevenwonders.sharedprefrences;

import android.content.Context;
import android.content.SharedPreferences;

public interface ISharedPrefererences {

	
public SharedPreferences getSharedPreferences(Context context, String name);
}
