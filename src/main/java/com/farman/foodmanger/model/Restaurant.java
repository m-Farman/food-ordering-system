package com.farman.foodmanger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Restaurant {

	private final String ID;
	private final String name;
	private final Integer capacity;
	private final AtomicInteger currentCapacity;

	private List<MenuItem> menuItems; // need to return deep copy in getter so that it not updated

	public Restaurant(String ID, String name,Integer capacity) {
		this.ID = ID;
		this.name = name;
		this.capacity = capacity;
		this.currentCapacity = new AtomicInteger(0);
		menuItems = new ArrayList<>();
	}
	
	public void addMenuItems(List<MenuItem> items) {
		menuItems.addAll(items);
	}

}
