package com.farman.foodmanger.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.farman.foodmanger.model.MenuItem;
import com.farman.foodmanger.model.OrderItem;
import com.farman.foodmanger.model.Restaurant;

@Component
public class RestaurantRepository {

	private final Map<String, Restaurant> restaurantsDB;

	public RestaurantRepository() {
		this.restaurantsDB = new ConcurrentHashMap<>();
	}

	public synchronized boolean containsRestaurant(String name) {
		return restaurantsDB.containsKey(name);
	}

	public synchronized boolean updateCapacity(String restaurantName) {
		final Restaurant restaurant = restaurantsDB.get(restaurantName);
		final int currCap = restaurant.getCurrentCapacity().get();
		if (currCap == 0) {
			return false;
		}
		return restaurant.getCurrentCapacity().compareAndSet(currCap, currCap - 1);
	}

	public List<MenuItem> getAllMenuItem() {
		return restaurantsDB.values().stream().flatMap(r -> r.getMenuItems().stream()).collect(Collectors.toList());
	}

	public synchronized void addRestautant(Restaurant restaurant) {
		restaurantsDB.put(restaurant.getName(), restaurant);
	}

	public synchronized void addMenuItem(String restaurantName, List<MenuItem> menuItems) {
		final Restaurant restaurant = restaurantsDB.get(restaurantName);
		List<MenuItem> menu = restaurant.getMenuItems();
		menu.addAll(menuItems);
	}

	public synchronized List<MenuItem> deleteMenuItem(String restaurantName, List<MenuItem> menuItems) {
		final Restaurant restaurant = restaurantsDB.get(restaurantName);
		Set<String> iDSet = menuItems.stream().map(r -> r.getID()).collect(Collectors.toSet());
		List<MenuItem> menu = restaurant.getMenuItems().stream().filter(m -> !iDSet.contains(m.getID()))
				.collect(Collectors.toList());
		restaurant.setMenuItems(menu);
		return menu;
	}

	public synchronized List<MenuItem> updateMenuItem(String restaurantName, List<MenuItem> menuItems) {
		final Restaurant restaurant = restaurantsDB.get(restaurantName);
		Set<String> iDSet = menuItems.stream().map(r -> r.getID()).collect(Collectors.toSet());
		List<MenuItem> menu = restaurant.getMenuItems().stream().filter(m -> !iDSet.contains(m.getID()))
				.collect(Collectors.toList());
		menu.addAll(menuItems);
		restaurant.setMenuItems(menu);
		return menu;
	}
	
	public List<Restaurant> searchRestaurant(List<OrderItem> items) {
		
		List<Restaurant> output=new ArrayList<Restaurant>();
		 for (Restaurant rest : restaurantsDB.values()) {
			Set<String> menuIDs = rest.getMenuItems().stream().map(m-> m.getID()).collect(Collectors.toSet());
			boolean containsAll = true;
			for (OrderItem order : items) {
				if (!menuIDs.contains(order.getMenuID())) {
					containsAll = false;
				}
			}
			if (containsAll) {
				output.add(rest);
			}
		}
		
		return output;
	}
}
