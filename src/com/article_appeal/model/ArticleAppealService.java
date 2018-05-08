package com.article_appeal.model;

import java.util.List;

public class ArticleAppealService {

	private ArticleAppealDAO_interface dao;
	
	public ArticleAppealService(){
		dao = new ArticleAppealDAO();
	}
	
	public ArticleAppealVO addAap(String art_id, String mem_id,
			String art_apl_cnt)
	{
		ArticleAppealVO aaVO = new ArticleAppealVO();
		
		aaVO.setArt_id(art_id);
		aaVO.setMem_id(mem_id);
		aaVO.setArt_apl_cnt(art_apl_cnt);
		dao.add(aaVO);
		
		
		return aaVO;
	}
	
	public ArticleAppealVO update(String emp_no, String art_apl_sts, String art_rapl,String art_apl_id){
		ArticleAppealVO aaVO = new ArticleAppealVO();
		aaVO.setEmp_no(emp_no);
		aaVO.setArt_apl_sts(art_apl_sts);
		aaVO.setArt_rapl(art_rapl);
		aaVO.setArt_apl_id(art_apl_id);
		
		dao.update(aaVO);
		return aaVO;
	}
	public void delete(String art_apl_id){
		dao.delete(art_apl_id);
	}
	public ArticleAppealVO findByPK(String art_apl_id){
		return dao.findByPK(art_apl_id);
	}
	public List<ArticleAppealVO>getAll(){
		return dao.getAll();
	}
	public List<ArticleAppealVO>getSelected(String art_apl_sts){
		return dao.getSelected(art_apl_sts);
	}
	
}
