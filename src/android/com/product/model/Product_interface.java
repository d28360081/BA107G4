package android.com.product.model;

import java.util.List;




public interface Product_interface {
	   public  List<ProductVO>  selectAllgogoshop();
	   public  List<ProductVO>  selectAllgogoshopOrderByPrice();
	   public  List<ProductVO>  selectAllgogoshopOrderByPriceDESC();
	   public ProductVO findByPrimaryKey(String it_id);  
	   

}
