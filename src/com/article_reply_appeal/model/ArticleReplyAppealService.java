package com.article_reply_appeal.model;

import java.util.List;

public class ArticleReplyAppealService {

	private ArticleReplyAppealDAO_interface dao;
	
	public ArticleReplyAppealService(){
		dao = new ArticleReplyAppealDAO();
	}
	
	public ArticleReplyAppealVO addArp(String re_id, String mem_id, String art_re_apl_cnt )
	{
		ArticleReplyAppealVO araVO = new ArticleReplyAppealVO();
		araVO.setRe_id(re_id);
		araVO.setMem_id(mem_id);
		araVO.setArt_re_apl_cnt(art_re_apl_cnt);
		dao.add(araVO);
		return araVO;
	}
	public ArticleReplyAppealVO findByPk(String art_capl_id){
		
		ArticleReplyAppealVO araVO = new ArticleReplyAppealVO();
		araVO.setArt_capl_id(art_capl_id);
		araVO = dao.findByPK(art_capl_id);
		return araVO;
	}
	public ArticleReplyAppealVO update(String emp_no, String art_re_apl_sts, String art_re_rapl, String art_capl_id){
		ArticleReplyAppealVO araVO = new ArticleReplyAppealVO();
		araVO.setEmp_no(emp_no);
		araVO.setArt_re_apl_sts(art_re_apl_sts);
		araVO.setArt_re_rapl(art_re_rapl);
		araVO.setArt_capl_id(art_capl_id);
		dao.update(araVO);
		return araVO;
	}
	public List<ArticleReplyAppealVO>getAll(){
		return dao.getAll();
	}
	public List<ArticleReplyAppealVO>getSelected(String art_re_apl_sts){
		return dao.getSelected(art_re_apl_sts);
	}
	
	
}
