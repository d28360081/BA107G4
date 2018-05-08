package android.com.product.model;

import java.util.List;

public class ProductService {
	private Product_interface dao;

	public ProductService() {
		dao=new ProductDAO();
	}
	
	public List<ProductVO> selectAllgogoshop(){
		return dao.selectAllgogoshop();
	}
	public List<ProductVO> selectAllgogoshopOrderByPrice(){
		return dao.selectAllgogoshopOrderByPrice();
	}
	public List<ProductVO> selectAllgogoshopOrderByPriceDESC(){
		return dao.selectAllgogoshopOrderByPriceDESC();
	}
	
	public ProductVO findByPrimaryKey(String it_id) {
		return dao.findByPrimaryKey(it_id);
	}

}
