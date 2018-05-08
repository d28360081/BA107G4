package com.Product.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Cart {

	private Map<String, CartItem> map = Collections.synchronizedMap(new LinkedHashMap());
	// private Map<String, CartItem> map = new TreeMap<>();
	private Double it_prc;

	public void addCart(ProductVO product) {

		CartItem item = map.get(product.getIt_id());
		if (item == null) {
			item = new CartItem();
			item.setProduct(product);
			item.setIt_num(1);
			map.put(product.getIt_id(), item);
		} else {
			if (item.getIt_num() >= item.getProduct().getIt_num()) {
				item.setIt_num(item.getProduct().getIt_num());
			} else {
				item.setIt_num(item.getIt_num() + 1);
			}
		}

	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public Double getPrice() {
		double totalprice = 0;
		for (Map.Entry<String, CartItem> entry : map.entrySet()) {
			CartItem item = entry.getValue();
			totalprice += item.getIt_prc();
		}
		this.it_prc = totalprice;
		return it_prc;
	}

	public void setPrice(double it_prc) {
		this.it_prc = it_prc;
	}

}