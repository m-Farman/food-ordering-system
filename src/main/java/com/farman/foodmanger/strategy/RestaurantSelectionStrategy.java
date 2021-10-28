package com.farman.foodmanger.strategy;

import java.util.List;
import java.util.Optional;

import com.farman.foodmanger.model.OrderItem;
import com.farman.foodmanger.model.Restaurant;

public interface RestaurantSelectionStrategy {

	public Optional<Restaurant> select(List<Restaurant> restaurants,List<OrderItem> orderItemts);
}
