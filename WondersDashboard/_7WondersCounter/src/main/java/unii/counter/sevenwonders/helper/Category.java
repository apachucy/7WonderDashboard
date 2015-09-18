package unii.counter.sevenwonders.helper;

import java.io.Serializable;

import unii.counter.sevenwonders.view.adapter.PointsType;

/**
 * Created by apachucy on 2015-09-18.
 */
public class Category  implements Serializable{
    private String mCategoryName;
    private PointsType mPointsType;
    private int mDrawableIcon;

    public Category(String categoryName, PointsType pointType, int resDrawableIcon) {
        mCategoryName = categoryName;
        mPointsType = pointType;
        mDrawableIcon = resDrawableIcon;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public PointsType getPointsType() {
        return mPointsType;
    }

    public void setPointsType(PointsType mPointsType) {
        this.mPointsType = mPointsType;
    }

    public int getDrawableIcon() {
        return mDrawableIcon;
    }

    public void setDrawableIcon(int mDrawableIcon) {
        this.mDrawableIcon = mDrawableIcon;
    }
}