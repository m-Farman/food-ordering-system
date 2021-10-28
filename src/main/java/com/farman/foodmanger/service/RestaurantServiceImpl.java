package com.farman.foodmanger.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farman.foodmanger.exception.EmptyMenuException;
import com.farman.foodmanger.exception.IllegalMenuItemException;
import com.farman.foodmanger.exception.RestaurantAlreadyExistsException;
import com.farman.foodmanger.exception.RestaurantNotFoundException;
import com.farman.foodmanger.model.MenuItem;
import com.farman.foodmanger.model.Restaurant;
import com.farman.foodmanger.repository.RestaurantRepository;

import lombok.NonNull;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public void register(@NonNull final String name, @NonNull final Integer maxCap)
			throws RestaurantAlreadyExistsException {
		if (restaurantRepository.containsRestaurant(name)) {
			throw new RestaurantAlreadyExistsException();
		}
		Restaurant restaurant = new Restaurant(UUID.randomUUID().toString(), name, maxCap);
		restaurantRepository.addRestautant(restaurant);
	}

	@Override
	public void freeCapacity(@NonNull final String restaurantName) {
		if (!restaurantRepository.containsRestaurant(restaurantName)) {
			throw new RestaurantNotFoundException();
		}
		restaurantRepository.updateCapacity(restaurantName);
	}

	@Override
	public List<MenuItem> getMenu() throws EmptyMenuException {
		return restaurantRepository.getAllMenuItem();
	}

	@Override
	public void addMenuItem(@NonNull final String restaurantName, @NonNull final List<MenuItem> menuItems)
			throws RestaurantNotFoundException, IllegalMenuItemException {
		if (!restaurantRepository.containsRestaurant(restaurantName)) {
			throw new RestaurantNotFoundException();
		}
		if (menuItems.stream().anyMatch(m -> m.getID() == null || m.getID().equals(""))) {
			throw new IllegalMenuItemException();
		}
		restaurantRepository.addMenuItem(restaurantName, menuItems);

	}

	@Override
	public void deleteMenuItem(@NonNull final String restaurantName, @NonNull final List<MenuItem> menuItems)
			throws RestaurantNotFoundException, IllegalMenuItemException {
		if (!restaurantRepository.containsRestaurant(restaurantName)) {
			throw new RestaurantNotFoundException();
		}
		if (menuItems.stream().anyMatch(m -> m.getID() == null || m.getID().equals(""))) {
			throw new IllegalMenuItemException();
		}
		restaurantRepository.deleteMenuItem(restaurantName, menuItems);

	}

	@Override
	public void updateMenuItem(@NonNull final String restaurantName, @NonNull final List<MenuItem> menuItems)
			throws RestaurantNotFoundException, IllegalMenuItemException {
		if (!restaurantRepository.containsRestaurant(restaurantName)) {
			throw new RestaurantNotFoundException();
		}
		if (menuItems.stream().anyMatch(m -> m.getID() == null || m.getID().equals(""))) {
			throw new IllegalMenuItemException();
		}
		restaurantRepository.updateMenuItem(restaurantName, menuItems);

	}

}
