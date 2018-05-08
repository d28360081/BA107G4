package com.commision_appeal.model;

import java.util.List;

public interface CommisionCommentAppealDAO_interface {
	public void add(CommisionCommentAppealVO cca);
	public void update(CommisionCommentAppealVO cca);
	public void delete(String com_capl_id);
	CommisionCommentAppealVO findByPK(String com_capl_id);
	List<CommisionCommentAppealVO > getAll();
}
