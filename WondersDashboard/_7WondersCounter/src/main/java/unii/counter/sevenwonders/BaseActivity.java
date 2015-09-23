package unii.counter.sevenwonders;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by apachucy on 2015-09-23.
 */
public class BaseActivity extends ActionBarActivity {


    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    protected void replaceFragments(Fragment fragment, String tag, int container) {
        Fragment fragmentFound = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentFound != null) {
            getSupportFragmentManager().beginTransaction().show(fragmentFound).commit();
        } else {
            hideKeyboard();
            getSupportFragmentManager().beginTransaction().replace(container, fragment, tag).commit();
        }
    }
}
