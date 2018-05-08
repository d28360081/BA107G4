package com.bonusproductorder.model;

import java.util.HashMap;
import java.util.List;


import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusproduct.model.BonusProductVO;



public interface BonusProductOrder_interface {
	public void insert(BonusProductOrderVO bonusProductOrderVO);
    public void update(BonusProductOrderVO bonusProductOrderVO);
    public void updateSts(BonusProductOrderVO bonusProductOrderVO);
    public void delete(String bns_rec_id);
    public BonusProductOrderVO findByPrimaryKey(String bns_rec_id);
    public List<BonusProductOrderVO> findByMem_id(String mem_id);
    public List<BonusProductOrderVO> getAll();
    
    public void insert2(BonusProductOrderVO bonusProductOrderVO,List<BonusProductOrderdetailVO>list);
    public void insert5(BonusProductOrderVO bonusProductOrderVO,String BNS_IT_ID);
    public void insert3(BonusProductOrderVO bonusProductOrderVO,HashMap<String,BonusProductVO> BNSDetailMap);
}
