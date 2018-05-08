package com.FavoriteProduct.model;

import java.util.List;

public class FavoriteProductService {
	FavoriteProductDAO dao=new FavoriteProductDAO();
	
	public void insert(FavoriteProductVO favoriteProductVO){
		dao.insert(favoriteProductVO);
	}

	public void update(FavoriteProductVO favoriteProductVO){
		dao.update(favoriteProductVO);
	}

	public void delete(String mem_id){
		dao.delete(mem_id);
	}
	public void deleteBy_it_id(String it_id){
		dao.deleteBy_it_id(it_id);
	}

	public FavoriteProductVO findByPrimaryKey(String mem_id){
		return dao.findByPrimaryKey(mem_id);
	}
	 public FavoriteProductVO findByPrimaryKey_by_it_id(String it_id){
		 return dao.findByPrimaryKey_by_it_id(it_id);
	 }

	public List<FavoriteProductVO> getAll(){
		return(List<FavoriteProductVO>) dao.getAll();		
	}
	public List<FavoriteProductVO> getAll_by_it_id(String it_id){
		return(List<FavoriteProductVO>) dao.getAll_by_it_id(it_id)	;	
	}
	public List<FavoriteProductVO> getAll_by_mem_id(String mem_id){
		return(List<FavoriteProductVO>) dao.getAll_by_mem_id(mem_id);
	}
	
	
}
