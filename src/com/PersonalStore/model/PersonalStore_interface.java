package com.PersonalStore.model;

import java.util.List;
import java.util.Set;

import com.PersonalStore.model.PersonalStoreVO;
import com.Product.model.ProductVO;


public interface PersonalStore_interface {
	public void insert(PersonalStoreVO personalStoreVO);
	
    public void update(PersonalStoreVO personalStoreVO);
    public void delete(String st_id);
    public PersonalStoreVO findByPrimaryKey(String st_id);
    public List<PersonalStoreVO> getAll();
  
    public Set<ProductVO> getProductsBySt_id(String st_id);
}
