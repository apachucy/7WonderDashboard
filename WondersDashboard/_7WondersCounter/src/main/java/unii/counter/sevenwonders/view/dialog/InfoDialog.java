package unii.counter.sevenwonders.view.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unii.counter.sevenwonders.R;

/**
 * Created by apachucy on 2015-09-17.
 */
public class InfoDialog extends DialogFragment {

    @Bind(R.id.dialog_titleTextView)
    TextView mTitleTextView;
    @Bind(R.id.dialog_messageTextView)
    TextView mMessageTextView;

    private static final String INFO_DIALOG_TITLE = "INFO_DIALOG_TITLE";
    private static final String INFO_DIALOG_MESSAGE = "INFO_DIALOG_MESSAGE";

    private String mDialogTitle;
    private String mDialogMessage;

    @OnClick(R.id.dialog_closeButton)
    void onCloseButtonClick(View v) {
        dismiss();
    }

    public static InfoDialog newInstance(String title, String message) {
        InfoDialog dialogFragment = new InfoDialog();
        // request a window without the title
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        Bundle args = new Bundle();
        args.putString(INFO_DIALOG_TITLE, title);
        args.putString(INFO_DIALOG_MESSAGE, message);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogTitle = getArguments().getString(INFO_DIALOG_TITLE);
        mDialogMessage = getArguments().getString(INFO_DIALOG_MESSAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_info, container,
                false);
        ButterKnife.bind(this, v);
        mTitleTextView.setText(mDialogTitle);
        mMessageTextView.setText(mDialogMessage);
        return v;
    }
}
