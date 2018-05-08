package com.FavoriteProduct.model;

import java.util.List;

import com.FavoriteProduct.model.FavoriteProductVO;


public interface FavoriteProductDAO_interface {
	public void insert(FavoriteProductVO favoriteProductVO);
    public void update(FavoriteProductVO favoriteProductVO);
    public void delete(String mem_id);   
    public void deleteBy_it_id(String it_id);
    public FavoriteProductVO findByPrimaryKey(String mem_id);
    public FavoriteProductVO findByPrimaryKey_by_it_id(String it_id);
    public List<FavoriteProductVO> getAll();
    
	
}
