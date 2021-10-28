package com.farman.foodmanger.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farman.foodmanger.model.MenuItem;
import com.farman.foodmanger.model.OrderItem;
import com.farman.foodmanger.model.Restaurant;

@Service
public class LowerCostRestuarantSelection implements RestaurantSelectionStrategy {

	@Override
	public Optional<Restaurant> select(List<Restaurant> restaurants,List<OrderItem> orderItems) {
		//compute the cost and return min price restaurant
		
		Restaurant desiredRestaurant=null;
		double minCost = Double.MAX_VALUE;
		for (Restaurant r : restaurants) {
			double currentCost=0;
			Map<String, MenuItem> menuMapping = new HashMap<>(); //= r.getMenuItems().stream().
			for (OrderItem orderItem : orderItems) {
				String menuId = orderItem.getMenuID();
				currentCost += menuMapping.get(menuId).getAmount();
			}
			
			if (minCost > currentCost) {
				// checking capacity
				minCost = currentCost;
				desiredRestaurant = r;
			}
			
		}
		
		
		return Optional.ofNullable(desiredRestaurant);
	}

}
