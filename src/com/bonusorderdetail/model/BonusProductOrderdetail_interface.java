package com.bonusorderdetail.model;

import java.util.List;




public interface BonusProductOrderdetail_interface {
	public void insert(BonusProductOrderdetailVO bonusProductOrderdetailVO);
    public void delete(String bns_rec_id , String bns_it_id);
    public BonusProductOrderdetailVO findByPrimaryKey(String bns_rec_id);
    public List<BonusProductOrderdetailVO> getAll();
    public void insert2(BonusProductOrderdetailVO bonusProductOrderdetailVO , java.sql.Connection con);
}
