package com.bonusproduct.model;

import java.util.List;

public interface BonusProduct_interface {
	public void insert(BonusProductVO bonusProductVO);
    public void update(BonusProductVO bonusProductVO);
    public void updateSts(BonusProductVO bonusProductVO);
    public void delete(String bns_it_id);
    public BonusProductVO findByPrimaryKey(String bns_it_id);
    public List<BonusProductVO> getAll();
}
