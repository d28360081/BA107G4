package com.chtmsg.model;

public class ChtmsgService {
	
	private Cht_msgVO_interface dao;
	
	public ChtmsgService(){
		dao = new ChtmsgDAO();
	}
	public ChtmsgVO addchtmsg(String mem_id_send , String mem_id_get,String msg_cnt){
		ChtmsgVO chtmsgVO = new ChtmsgVO();
		chtmsgVO.setMem_id_send(mem_id_send);
		chtmsgVO.setMem_id_get(mem_id_get);
		chtmsgVO.setMsg_cnt(msg_cnt);
		dao.insert(chtmsgVO);
		return chtmsgVO;
	}
	public ChtmsgVO changsts(String msg_id , Integer msg_sts){
		
		ChtmsgVO chtmsgVO = new ChtmsgVO();
		
		chtmsgVO.setMsg_id(msg_id);
		chtmsgVO.setMsg_sts(msg_sts);
		dao.changests(chtmsgVO);
		return chtmsgVO;
	}
}
