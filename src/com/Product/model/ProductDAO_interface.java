package com.Product.model;

import java.util.List;
import java.util.Map;

import com.Product.model.ProductVO;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
    public void update(ProductVO productVO);  
    public void updateNum(ProductVO productVO);  
    public ProductVO findByPrimaryKey(String it_id);  
    
    
    public Map<String, ProductVO>getAllProductMap();
    public Map<String, ProductVO>getAllProductMapBy_it_id(String it_id);
    public Map<String, ProductVO>getAllProductMapBy_it_name_Serach(String it_name);
    public Map<String, ProductVO> getAllProductMapBy_Sts_On_AndStore(String st_id);
    public Map<String, ProductVO> getAllProductMapBy_Sts_Off_AndStore(String st_id);
    public Map<String, ProductVO>getAllProductMapBy_Sts_On();	
    public Map<String, ProductVO>getAllProductMapBy_Sts_Off();
    public Map<String, ProductVO> findBy_Cate_Sts_OnProduct(String it_cate);
    public void update_STS(ProductVO productVO);
   
	
	}
	

