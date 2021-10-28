package com.farman.foodmanger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class MenuItem {
	
	private final String ID;
	private final String name;
	private final String description;
	private final double amount;
}
