# FoodSearch
Simple food information list pulled from Rutgers Dining Services. Able to search by food name.

## What it does
FoodSearch pulls data from Rutgers dining hall data from https://rumobile.rutgers.edu/1/rutgers-dining.txt. The Rutgers 
mobile app loads food data into lists separated by dining hall, meal, and genre, which is a good way for people to find
the foods that each location offers at a given time. FoodSearch lets users filter searches by food name rather than other parameters.
I can be a rather picky eater at times, and am willing to go to a different campus to eat something that I want, which is 
partially the motivation behind the app.

## Design
From observing the official Rutgers App, I assume that the listview used to display the food data uses a basic `ArrayAdapter` that
takes strings. While simple and easily replicated, it doesn't quite display all the data that I'd like to see immediately;
namely, the food name, location where it's served, and which meal time it's available during.

To accomplish this, I implemented a `Food` class, as well as a custom `FoodItemAdapter` which extends the `ArrayAdapter` class.
Each Food item is added to the list using the viewholder design pattern to avoid slowing down scrolling by repeated `findViewByID()`
method calls. Of particular note is the `toString()` override in the Food class, which returns the food name, in order to
use a Textwatcher listener on the Edittext searchbar.

The UI of the app itself is quite simple: one TableRow to hold the EditText, one to hold the Listview. 

## Future considerations
If you look at the Food class, you'll notice that I also added the genre_name string field. I felt it was relatively important
enough information to keep, but at the same time I didn't add it into the view items to display. I figured that if you already
know what you're looking for to eat, you don't really care about (or, more likely, already know) what food genre it's in.

I considered further list filtering options, such as by location, meal time, etc., but I simply didn't have the time to work
this enough (most of the project was done in the car). If I were to add that functionality, though, I'd probably use CheckBoxes and 
implement the `Filterable` interface, and override the `performFiltering()` method. It'd be relatively easy to do,
just by using some `setOnCheckedChangeListener`'s on each Checkbox.
