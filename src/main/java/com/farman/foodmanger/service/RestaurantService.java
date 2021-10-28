package com.farman.foodmanger.service;

import java.util.List;

import com.farman.foodmanger.exception.EmptyMenuException;
import com.farman.foodmanger.exception.IllegalMenuItemException;
import com.farman.foodmanger.exception.RestaurantAlreadyExistsException;
import com.farman.foodmanger.exception.RestaurantNotFoundException;
import com.farman.foodmanger.model.MenuItem;

public interface RestaurantService {

	public void register(String name, Integer maxCap)throws RestaurantAlreadyExistsException;
	public void freeCapacity(String name);
	public void addMenuItem(String restaurantName, List<MenuItem> menuItems)throws RestaurantNotFoundException, IllegalMenuItemException;
	public void deleteMenuItem(String restaurantName, List<MenuItem> menuItems)throws RestaurantNotFoundException, IllegalMenuItemException;
	public void updateMenuItem(String restaurantName, List<MenuItem> menuItems)throws RestaurantNotFoundException, IllegalMenuItemException;
	public List<MenuItem> getMenu()throws EmptyMenuException; 

}
