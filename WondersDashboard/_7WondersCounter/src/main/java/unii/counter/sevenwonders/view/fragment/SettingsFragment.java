package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.sharedprefrences.GameModeConfig;
import unii.counter.sevenwonders.sharedprefrences.SettingsPreferencesFactory;

/**
 * Created by apachucy on 2015-09-22.
 */
public class SettingsFragment extends Fragment {
    private Context mContext;
    @Bind(R.id.settings_game_mode_normal)
    RadioButton mGameNoExtensionRadioButton;

    @Bind(R.id.settings_game_mode_leaders)
    RadioButton mGameLeadersExtensionRadioButton;

    @Bind(R.id.settings_game_mode_cities)
    RadioButton mGameCitiesExtensionRadioButton;

    @OnCheckedChanged(R.id.settings_game_mode_normal)
    public void onCheckGameModeOffRadioButton(boolean checked) {
        if (checked) {
            SettingsPreferencesFactory.getInstance().setGameMode(GameModeConfig.GAME_MODE_NO_EXTENSIONS);
        }
    }

    @OnCheckedChanged(R.id.settings_game_mode_leaders)
    public void onCheckGameModeLeaderRadioButton(boolean checked) {
        if (checked) {
            SettingsPreferencesFactory.getInstance().setGameMode(GameModeConfig.GAME_MODE_LEADERS);
        }
    }

    @OnCheckedChanged(R.id.settings_game_mode_cities)
    public void onCheckGameModeCitiesRadioButton(boolean checked) {
        if (checked) {
            SettingsPreferencesFactory.getInstance().setGameMode(GameModeConfig.GAME_MODE_CITIES);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        selectRadioButton(SettingsPreferencesFactory.getInstance().getGameMode());
        return view;
    }

    private void selectRadioButton(int selectedRadioButton) {
        switch (selectedRadioButton) {
            case GameModeConfig.GAME_MODE_NO_EXTENSIONS:
                mGameNoExtensionRadioButton.setChecked(true);
                break;
            case GameModeConfig.GAME_MODE_LEADERS:
                mGameLeadersExtensionRadioButton.setChecked(true);
                break;
            case GameModeConfig.GAME_MODE_CITIES:
                mGameCitiesExtensionRadioButton.setChecked(true);
                break;
            default:
                break;
        }
    }
}
