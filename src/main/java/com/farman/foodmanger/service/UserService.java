package com.farman.foodmanger.service;

import java.util.List;

import com.farman.foodmanger.exception.OrderNotDeliverableExecption;
import com.farman.foodmanger.model.OrderItem;

public interface UserService {

	public boolean order(String username,List<OrderItem>  orderItems) throws OrderNotDeliverableExecption;
}
