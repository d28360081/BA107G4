package com.PersonalStore.model;

import java.util.List;
import java.util.Set;

import com.Product.model.ProductVO;

public class PersonalStoreService {
	PersonalStoreDAO personalStoreDAO=new PersonalStoreDAO();
	public void insert(PersonalStoreVO personalStoreVO) {
		personalStoreDAO.insert(personalStoreVO);
	}
	public void update(PersonalStoreVO personalStoreVO){
		personalStoreDAO.update(personalStoreVO);
	}
	 public void delete(String st_id){
		 personalStoreDAO.delete(st_id);		 
	 }
	 public PersonalStoreVO findByPrimaryKey(String st_id){
		 return personalStoreDAO.findByPrimaryKey(st_id);
	 }
	 public List<PersonalStoreVO> getAll(){
		 return (List<PersonalStoreVO>)personalStoreDAO.getAll();		 
	 }
	 public Set<ProductVO> getProductsBySt_id(String st_id){
		 return (Set<ProductVO>)personalStoreDAO.getProductsBySt_id(st_id);
	 }
	 public PersonalStoreVO findByMemId(String mem_id) {
		 return personalStoreDAO.findByMemId(mem_id);
	 } 
	 
}
