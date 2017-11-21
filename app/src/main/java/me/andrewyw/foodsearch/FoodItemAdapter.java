package me.andrewyw.foodsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.ArrayList;



public class FoodItemAdapter extends ArrayAdapter<Food> {

    private static class ViewHolder {
        public TextView foodName;
        public TextView foodCampus;
        public TextView foodMeal;
    }

    public FoodItemAdapter(Context context, ArrayList<Food> foods) {

        super(context, 0, foods);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        final Food food = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.food_item, parent, false);
            viewHolder.foodName = convertView.findViewById(R.id.foodTitle);
            viewHolder.foodCampus = convertView.findViewById(R.id.foodCampus);
            viewHolder.foodMeal = convertView.findViewById(R.id.mealName);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.foodName.setText(food.getName());
        viewHolder.foodCampus.setText(food.getLocation());
        viewHolder.foodMeal.setText(food.getMeal());

        return convertView;

    }

}
