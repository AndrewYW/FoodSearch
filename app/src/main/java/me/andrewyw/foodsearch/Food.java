package me.andrewyw.foodsearch;

public class Food {
    private String location_name;
    private String meal_name;
    private String name;
    private String genre_name;

    public Food(String name, String loc, String meal, String genre){
        this.name = name;
        this.location_name = loc;
        this.meal_name = meal;
        this.genre_name = genre;
    }

    public String getName(){
        return this.name;
    }

    public String getLocation(){
        return this.location_name;
    }

    public String getMeal(){
        return this.meal_name;
    }

    public String getGenre(){
        return this.genre_name;
    }

    public String toString(){
        return this.name;
    }


}
