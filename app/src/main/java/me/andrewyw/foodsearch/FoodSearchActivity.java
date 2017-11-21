package me.andrewyw.foodsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class FoodSearchActivity extends AppCompatActivity {

    private ListView foodItemList;
    private FoodItemAdapter adapter;
    private ArrayList<Food> foodArrayList = null;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);

        foodItemList = findViewById(R.id.foodItemList);


        String url = "https://rumobile.rutgers.edu/1/rutgers-dining.txt";
        jsonAsync task = new jsonAsync();
        JSONArray jsonArray = null;
        try {
            System.out.println("Trying to set JSONArray");
            String webText = task.execute(url).get();
            jsonArray = new JSONArray(webText);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(jsonArray != null){
            System.out.println("Array not null");
            foodArrayList = fromJson(jsonArray);

        }
        else{
            System.out.println("JSONArray not loaded.");
        }

        adapter = new FoodItemAdapter(this, foodArrayList);
        foodItemList.setAdapter(adapter);

        searchText = findViewById(R.id.searchText);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("text changed");
                try {
                    adapter.getFilter().filter(s);
                }catch (Exception e){
                    System.out.println("no");
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public static ArrayList<Food> fromJson(JSONArray jsonArray){
        ArrayList<Food> foodList = new ArrayList<Food>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                String location_name = obj.getString("location_name");

                JSONArray meals = (JSONArray) obj.get("meals");

                for(int j = 0; j < meals.length(); j++){
                    JSONObject meal = null;
                    try{
                        meal = meals.getJSONObject(j);
                        String meal_name = meal.getString("meal_name");
                        if( meal.getBoolean("meal_avail")){
                            JSONArray genres = (JSONArray) meal.get("genres");

                            for(int k = 0; k < genres.length(); k++){

                                JSONObject genre = genres.getJSONObject(k);
                                String genre_name = genre.getString("genre_name");
                                JSONArray items = genre.getJSONArray("items");

                                for(int z = 0; z < items.length(); z++){
                                    Food food = new Food(items.getString(z), location_name, meal_name, genre_name);
                                    if(food != null) {
                                        foodList.add(food);
                                    }
                                }
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return foodList;
    }

}
