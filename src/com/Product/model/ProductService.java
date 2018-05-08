package com.Product.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.Product.model.ProductVO;

import com.Product.model.Cart;
import com.Product.model.CartItem;

public class ProductService {	
	//private Map<String, ProductVO> Products = Collections.synchronizedMap(new HashMap<String, ProductVO>());
	private ProductDAO dao = new ProductDAO();

	public Map<String, ProductVO> getAllProductMap() {
		return (Map<String, ProductVO>) dao.getAllProductMap();
	}
	public Map<String, ProductVO> getAllProductMap_ST001() {
		return (Map<String, ProductVO>) dao.getAllProductMap_ST001();
	}
	
	
	public Map<String, ProductVO> getAllProductMapBy_Sts_On() {
		return (Map<String, ProductVO>) dao.getAllProductMapBy_Sts_On();
	}
	public Map<String, ProductVO> getAllProductMapBy_Sts_On_ST001() {
		return (Map<String, ProductVO>) dao.getAllProductMapBy_Sts_On_ST001();
	}
	
	public Map<String, ProductVO> getAllProductMapBy_Sts_On_AndStore(String st_id) {
		return (Map<String, ProductVO>) dao.getAllProductMapBy_Sts_On_AndStore(st_id);
	}
	public Map<String, ProductVO> getAllProductMapBy_Sts_Off_AndStore(String st_id) {
		return (Map<String, ProductVO>) dao.getAllProductMapBy_Sts_Off_AndStore(st_id);
	}
	
	public Map<String, ProductVO> getAllProductMapBy_it_name_Serach(String it_name) {
		return (Map<String, ProductVO>)dao.getAllProductMapBy_it_name_Serach(it_name);
	}
	
	public Map<String, ProductVO> getAllProductMapBy_Sts_Off() {
		return (Map<String, ProductVO>) dao.getAllProductMapBy_Sts_Off();
	}
	
	public void insertProduct(ProductVO product){
		dao.insert(product);
	}
	
	public  Map<String, ProductVO> findBy_Cate_Sts_OnProduct(String it_cate){
	
		return dao.findBy_Cate_Sts_OnProduct(it_cate);
	}
	public  Map<String, ProductVO> getAllProductMapBy_Sts_Off_ST001(){
		
		return dao.getAllProductMapBy_Sts_Off_ST001();
	}
	
	
	public void updateProduct(ProductVO product){
		dao.update(product);
	}
	public void updateProduct_num(ProductVO product){
		dao.updateNum(product);
	}
	
	public void update_STS(ProductVO product){
		dao.update_STS(product);
	}	
	

	public ProductVO findProduct(String it_id) {
		return dao.findByPrimaryKey(it_id);
	}
	public  Map<String, ProductVO>getAllProductMapBy_it_id(String it_id) { 
		return (Map<String, ProductVO>)dao.getAllProductMapBy_it_id(it_id);
		
	}
	public Map<String, ProductVO> findBy_Cate_Sts_OnProduct_ST001(String it_cate) {
		return (Map<String, ProductVO>)dao.findBy_Cate_Sts_OnProduct_ST001(it_cate);
	}
	public Map<String, ProductVO> getAllProductMapBy_it_name_Serach_ST001(String it_name) {
		return  (Map<String, ProductVO>)dao.getAllProductMapBy_it_name_Serach_ST001(it_name);
	}
	


	public void deleteCartItem(String it_id, Cart cart) {
		cart.getMap().remove(it_id);
	}

	public void clearCart(Cart cart) {
		cart.getMap().clear();
	}

	public void changeItemQuantity(String it_id, String it_num, Cart cart) {
		CartItem item = cart.getMap().get(it_id);
		item.setIt_num(Integer.parseInt(it_num));
	}
}