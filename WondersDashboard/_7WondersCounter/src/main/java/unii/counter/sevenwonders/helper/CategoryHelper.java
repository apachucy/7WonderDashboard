package unii.counter.sevenwonders.helper;

import android.content.Context;

import java.util.ArrayList;

import unii.counter.sevenwonders.R;
import unii.counter.sevenwonders.view.adapter.PointsType;

/**
 * Created by apachucy on 2015-09-18.
 */
public class CategoryHelper {

    public static ArrayList<Category> getCategoryList(Context context) {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(context.getString(R.string.list_header_war_points), PointsType.POINTS_WAR, R.drawable.ic_war_));
        categoryList.add(new Category(context.getString(R.string.list_header_gold_points), PointsType.POINTS_GOLD, R.drawable.ic_gold_));
        categoryList.add(new Category(context.getString(R.string.list_header_wonder_points), PointsType.POINTS_WONDER, R.drawable.ic_wonder_));
        categoryList.add(new Category(context.getString(R.string.list_header_civil_points), PointsType.POINTS_CIVIC, R.drawable.ic_civic_));
        categoryList.add(new Category(context.getString(R.string.list_header_trade_points), PointsType.POINTS_TRADE, R.drawable.ic_trade_));
        categoryList.add(new Category(context.getString(R.string.list_header_guild_points), PointsType.POINTS_GUILD, R.drawable.ic_guild_));
        categoryList.add(new Category(context.getString(R.string.list_header_science_points), PointsType.POINTS_SCIENCE, R.drawable.ic_science_));
        return categoryList;
    }


}
