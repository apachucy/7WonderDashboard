package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.config.Config;
import unii.counter.sevenwonders.helper.Category;
import unii.counter.sevenwonders.view.IPlayerScore;
import unii.counter.sevenwonders.view.adapter.DividerItemDecorator;
import unii.counter.sevenwonders.view.adapter.PlayerPointsRecyclerAdapter;
import unii.counter.sevenwonders.view.adapter.PointsType;
import unii.counter.sevenwonders.view.adapter.SciencePointsRecyclerAdapter;

/**
 * Created by apachucy on 2015-09-18.
 */
public class InputPointsFragment extends Fragment {
    private Context mContext;
    private IPlayerScore mPlayerScore;
    private Category mSelectedCategory;
    private RecyclerView.Adapter mPointsRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.input_pointsRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mPlayerScore = (IPlayerScore) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mSelectedCategory = (Category) bundle.getSerializable(Config.BUNDLE_CATEGORY_SELECTED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_points, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecorator(mContext, DividerItemDecorator.VERTICAL_LIST));
        if (mSelectedCategory.getPointsType() != PointsType.POINTS_SCIENCE) {
            mPointsRecyclerAdapter = new PlayerPointsRecyclerAdapter(mPlayerScore.getPlayersScoreSheet(), mPlayerScore.getPlayerNames(), mSelectedCategory.getPointsType());
        } else {
            mPointsRecyclerAdapter = new SciencePointsRecyclerAdapter(mContext, mPlayerScore.getPlayersScoreSheet(), mPlayerScore.getPlayerNames());
        }
        mRecyclerView.setAdapter(mPointsRecyclerAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
