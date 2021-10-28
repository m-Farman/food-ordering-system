package com.farman.foodmanger.model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class OrderItem {

	private final String ID;
	private final String menuID;
	private final int qauntity;
	
	public OrderItem(String menuID, int qauntity) {
		super();
		this.ID = UUID.randomUUID().toString();
		this.menuID = menuID;
		this.qauntity = qauntity;
	}
	
	
}
