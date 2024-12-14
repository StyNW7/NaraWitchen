package utility;

import java.util.ArrayList;

import foods.Food;

public class Sorting {
	
	// Sort the menu using bubble sort algorithm
	
	public void sortAscendingly(ArrayList<Food> foodList) {

        for (int i = 0; i < foodList.size() - 1; i++) {
            for (int j = 0; j < foodList.size() - 1 - i; j++) {
                if (foodList.get(j).getPrice() > foodList.get(j + 1).getPrice()) {
                    // Swap
                    Food temp = foodList.get(j);
                    foodList.set(j, foodList.get(j + 1));
                    foodList.set(j + 1, temp);
                }
            }
        }
        
    }

    public void sortDescendingly(ArrayList<Food> foodList) {

        for (int i = 0; i < foodList.size() - 1; i++) {
            for (int j = 0; j < foodList.size() - 1 - i; j++) {
                if (foodList.get(j).getPrice() < foodList.get(j + 1).getPrice()) {
                    // Swap
                    Food temp = foodList.get(j);
                    foodList.set(j, foodList.get(j + 1));
                    foodList.set(j + 1, temp);
                }
            }
        }
        
    }

}
