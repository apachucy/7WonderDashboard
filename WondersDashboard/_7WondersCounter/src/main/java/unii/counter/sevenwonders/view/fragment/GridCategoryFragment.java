package unii.counter.sevenwonders.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.helper.Category;
import unii.counter.sevenwonders.helper.CategoryHelper;
import unii.counter.sevenwonders.pojo.PlayerScoreSheet;
import unii.counter.sevenwonders.view.adapter.GridCategoryRecyclerAdapter;
import unii.counter.sevenwonders.view.adapter.OnGridItemSelected;

/**
 * Created by Arkadiusz Pachucy on 2015-09-03.
 */
public class GridCategoryFragment extends Fragment {


    private Context mContext;
    private OnGridItemSelected mOnGridItemSelectedListener;

    private RecyclerView.Adapter mCategoryRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Category> mCategoryList;
    @Bind(R.id.table_pointsRecyclerView)
    RecyclerView mRecyclerView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mOnGridItemSelectedListener = (OnGridItemSelected) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mCategoryList = CategoryHelper.getCategoryList(mContext);
        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mCategoryRecyclerAdapter = new GridCategoryRecyclerAdapter(mCategoryList, mOnGridItemSelected);
        mRecyclerView.setAdapter(mCategoryRecyclerAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private OnGridItemSelected mOnGridItemSelected = new OnGridItemSelected() {
        @Override
        public void onCategorySelected(Category categorySelected) {

            mOnGridItemSelectedListener.onCategorySelected(categorySelected);
        }
    };
}
