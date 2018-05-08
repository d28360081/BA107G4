package com.chtmsg.model;

import java.util.List;

public interface Cht_msgVO_interface {
	public void insert(ChtmsgVO cht_msgVO);
	public void update(ChtmsgVO cht_msgVO);
	public void delete(String msg_id);
	public ChtmsgVO findByPrimaryKey(String msg_id);
	public List<ChtmsgVO> getAll();
	public void changests (ChtmsgVO cht_msgVO);
}
