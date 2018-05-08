package com.article.model;

import java.util.List;

import com.member.model.MemVO;

public class ArticleHibernateService {
	private ArticleVO_h_interface dao;
	public ArticleHibernateService(){
		dao = new ArticleHibernateDAO();
	}
	public ArticleVO_Hibernate addArt(String mem_id , String art_tlt , String art_cnt , byte[]art_pic1,byte[] art_pic2 , byte[] art_pic3 ) {
		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		artVO.setMemVO(memVO);
		artVO.setArt_tlt(art_tlt);
		artVO.setArt_cnt(art_cnt);
		artVO.setArt_pic1(art_pic1);
		artVO.setArt_pic2(art_pic2);
		artVO.setArt_pic3(art_pic3);
		
		dao.insert(artVO);
		
		return artVO;
	}
	
	public ArticleVO_Hibernate updateLike(String art_id,Integer like_num) {
		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
		
		artVO.setArt_id(art_id);
		artVO.setLike_num(like_num);
		
		dao.updateLike(artVO);
		
		return artVO;
	}
	
	public List<ArticleVO_Hibernate> getByMem_id(String mem_id){
		return dao.findBymem(mem_id);
	}
	
	public ArticleVO_Hibernate findByPromaryKey(String art_id){
		return dao.findByPrimaryKey(art_id);
	}
	
	public List<ArticleVO_Hibernate> getAll(){
		return dao.getAll();
	}
	public List<ArticleVO_Hibernate> searchArt(String value){
		return dao.searchArt(value);
	}
	public ArticleVO_Hibernate changeSts(String art_id,String art_sts){
		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
		artVO.setArt_id(art_id);
		artVO.setArt_sts(art_sts);
		
		dao.updatestatus(artVO);
		return artVO;
	}
}
