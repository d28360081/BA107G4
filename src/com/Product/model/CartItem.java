package com.Product.model;



import com.Product.model.ProductVO;


public class CartItem {

private ProductVO product;
private Integer it_num;
private Double it_prc;




public ProductVO getProduct() {
   return product;
}
public void setProduct(ProductVO product) {
   this.product = product;
}
public int getIt_num() {
   return it_num;
}

public void setIt_num(Integer it_num) {
   this.it_num = it_num;   
   this.it_prc = this.product.getIt_prc() * this.it_num;
}
public double getIt_prc() {
   return it_prc;
}
public void setIt_prc(Double it_prc) {
   this.it_prc = it_prc;
}

}
