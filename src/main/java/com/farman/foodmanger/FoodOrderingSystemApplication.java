package com.farman.foodmanger;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.farman.foodmanger.model.MenuItem;
import com.farman.foodmanger.service.RestaurantService;

@SpringBootApplication
public class FoodOrderingSystemApplication implements CommandLineRunner {

	@Autowired
	RestaurantService restaurantService;

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		restaurantService.register("Restaurant 1", 5);
		restaurantService.addMenuItem("Restaurant 1",
				Arrays.asList(new MenuItem("1", "pizza", "pizza desc",1), new MenuItem("2", "dosa", "dosa desc",2),
						new MenuItem("3", "juice", "juice desc",2), new MenuItem("4", "idli", "idli desc",2),
						new MenuItem("5", "burger", "burger desc",2)));
		
		List<MenuItem> allMenu = restaurantService.getMenu();
		System.out.println("Initial Menu \n" +allMenu);
		
		restaurantService.deleteMenuItem("Restaurant 1", Arrays.asList(new MenuItem("1", "pizza", "pizza desc",2)));
		System.out.println("after deleting pizza Menu \n" +restaurantService.getMenu());
		
		restaurantService.updateMenuItem("Restaurant 1", Arrays.asList(new MenuItem("2", "dosa", "dosa desc changed",2)));
		System.out.println("after udpating dosa Menu \n" +restaurantService.getMenu());
		
	}

}
