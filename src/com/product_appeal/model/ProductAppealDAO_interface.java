package com.product_appeal.model;

import java.util.List;

public interface ProductAppealDAO_interface {
	public void add(ProductAppealVO pa);
	public void update(ProductAppealVO pa);
	public void delete(String it_apl_id);
	ProductAppealVO findByPK(String it_apl_id);
	List<ProductAppealVO> getAll();
	List<ProductAppealVO> getSelected(String apl_sts);
}
