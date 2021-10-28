package com.farman.foodmanger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farman.foodmanger.exception.OrderNotDeliverableExecption;
import com.farman.foodmanger.model.OrderItem;
import com.farman.foodmanger.model.Restaurant;
import com.farman.foodmanger.repository.RestaurantRepository;
import com.farman.foodmanger.strategy.RestaurantSelectionStrategy;

import lombok.NonNull;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	RestaurantRepository repository;
	
	@Autowired
	RestaurantSelectionStrategy restaurantSelectionStrategy;
	
	@Override
	public boolean order(@NonNull final String username,@NonNull final List<OrderItem> orderItems) throws OrderNotDeliverableExecption {
		
		List<Restaurant> restaurants = repository.searchRestaurant(orderItems);
		if (restaurants.isEmpty()) {
			throw new OrderNotDeliverableExecption();
		}
		
		Optional<Restaurant> restaurant = restaurantSelectionStrategy.select(restaurants, orderItems);
		if (!restaurant.isPresent()) {
			throw new OrderNotDeliverableExecption();
		}
		
		// process the order by restuarant
		
		
		return true;
	}

}
