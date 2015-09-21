package unii.counter.sevenwonders.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.helper.Category;

/**
 * Created by apachucy on 2015-09-18.
 */
public class GridCategoryRecyclerAdapter extends RecyclerView.Adapter<GridCategoryRecyclerAdapter.ViewHolder> {
    private ArrayList<Category> mCategoryList;
    private OnGridItemSelected mOnGridItemSelected;

    public GridCategoryRecyclerAdapter(ArrayList<Category> categoryList, OnGridItemSelected onGridItemSelected) {
        mCategoryList = categoryList;
        mOnGridItemSelected = onGridItemSelected;
    }

    @Override
    public GridCategoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category_grid, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridCategoryRecyclerAdapter.ViewHolder holder, int position) {
        holder.categoryTextView.setText(mCategoryList.get(position).getCategoryName());
        holder.categoryImageView.setImageResource(mCategoryList.get(position).getDrawableIcon());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.spinner_categoryImageView)
        ImageView categoryImageView;
        @Bind(R.id.spinner_categoryTextView)
        TextView categoryTextView;

        @OnClick({R.id.spinner_categoryImageView, R.id.spinner_categoryTextView})
        public void onItemClick() {
            mOnGridItemSelected.onCategorySelected(mCategoryList.get(getPosition()));
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
