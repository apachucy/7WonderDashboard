package unii.counter.sevenwonders.view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.math.ScienceCalculation;
import unii.counter.sevenwonders.validation.ValidationHelper;

public class ScienceDialog extends DialogFragment {

    @Bind(R.id.dialog_platesEditText)
    EditText mPlateEditText;
    @Bind(R.id.dialog_circleEditText)
    EditText mCircleEditText;
    @Bind(R.id.dialog_caliperEditText)
    EditText mCaliperEditText;
    @Bind(R.id.dialog_allEditText)
    EditText mPossibleAllEditText;
    @Bind(R.id.dialog_calculateScienceTextView)
    TextView mSciencePoint;
    @Bind(R.id.dialog_calculateButton)
    Button mCalculateButton;
    @Bind(R.id.dialog_closeButton)
    Button mCloseButton;
    private int mSciencePoints;
    private static IScienceDialogCallback sScienceDialogCallback;

    public static ScienceDialog newInstance(
            IScienceDialogCallback scienceDialogCallback) {
        ScienceDialog dialogFragment = new ScienceDialog();

        // request a window without the title
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        sScienceDialogCallback = scienceDialogCallback;
        return dialogFragment;
    }

    private OnClickListener mOnButtonClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_calculateButton:
                    int plate = 0;
                    int circle = 0;
                    int caliper = 0;
                    int calculateAll = 0;
                    if (!ValidationHelper.isEditTextEmpty(mCaliperEditText, null)) {
                        caliper = Integer.parseInt(mCaliperEditText.getText()
                                .toString());
                    }
                    if (!ValidationHelper.isEditTextEmpty(mCircleEditText, null)) {
                        circle = Integer.parseInt(mCircleEditText.getText()
                                .toString());
                    }
                    if (!ValidationHelper.isEditTextEmpty(mPlateEditText, null)) {
                        plate = Integer.parseInt(mPlateEditText.getText()
                                .toString());
                    }
                    if (!ValidationHelper.isEditTextEmpty(mPossibleAllEditText,
                            null)) {
                        calculateAll = Integer.parseInt(mPossibleAllEditText
                                .getText().toString());
                    }
                    mSciencePoints = ScienceCalculation.calculateSciencePoints(
                            plate, circle, caliper, calculateAll);
                    mSciencePoint.setText(getActivity().getString(
                            R.string.science_calculation, mSciencePoints));
                    break;
                case R.id.dialog_closeButton:
                    sScienceDialogCallback.setSciencePoints(mSciencePoints);
                    dismiss();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_science, container,
                false);
        mSciencePoints = 0;
        ButterKnife.bind(this, v);
        mCalculateButton
                .setOnClickListener(mOnButtonClickListener);
        mCloseButton.setOnClickListener(mOnButtonClickListener);
        return v;
    }

}
