package com.commision_appeal.model;

import java.util.List;

public interface CommisionAppealDAO_interface {
	public void add(CommisionAppealVO ca);
	public void update(CommisionAppealVO ca);
	public void delete(String com_apl_id);
	CommisionAppealVO findByPK(String com_apl_id);
	List<CommisionAppealVO> getAll();
	List<CommisionAppealVO> getSelected(String apl_sts);
}
