package com.article.model;

import java.util.List;

import com.articlecollection.model.ArtcolDAO;
import com.articlecollection.model.ArtcolVO;
import com.articlecollection.model.ArtcolVO_interface;
import com.articlecommand.model.ArtcomDAO;
import com.articlecommand.model.ArtcomVO;
import com.articlecommand.model.ArtcomVO_interface;
import com.articlereply.model.ArtreDAO;
import com.articlereply.model.ArtreVO;
import com.articlereply.model.ArtreVO_interface;

public class ArticleService {
	private ArticleVO_interface dao;
	private ArtreVO_interface dao2;
	private ArtcolVO_interface daocol;
	private ArtcomVO_interface daocom;
	public ArticleService() {
		dao = new ArticleDAO();
		dao2 = new ArtreDAO();
		daocol = new ArtcolDAO();
		daocom = new ArtcomDAO();
	}
	
	public ArticleVO addArt(String mem_id , String art_tlt , String art_cnt , byte[]art_pic1,byte[] art_pic2 , byte[] art_pic3 ) {
		ArticleVO artVO = new ArticleVO();
		
		artVO.setMem_id(mem_id);
		artVO.setArt_tlt(art_tlt);
		artVO.setArt_cnt(art_cnt);
		artVO.setArt_pic1(art_pic1);
		artVO.setArt_pic2(art_pic2);
		artVO.setArt_pic3(art_pic3);
		
		dao.insert(artVO);
		
		return artVO;
	}
	public ArticleVO updateLike(String art_id,Integer like_num) {
		ArticleVO artVO = new ArticleVO();
		
		artVO.setArt_id(art_id);
		artVO.setLike_num(like_num);
		
		dao.updateLike(artVO);
		
		return artVO;
	}
	public ArtcomVO updatelikenum(String Art_msg_id,Integer Art_msg_like_num){
		ArtcomVO artcomVO = new ArtcomVO();
		
		artcomVO.setArt_msg_id(Art_msg_id);
		artcomVO.setArt_msg_like_num(Art_msg_like_num);
		
		daocom.updatelike(artcomVO);
		
		return artcomVO;
	}
	public List<ArticleVO> findByMem(String mem_id){
		return dao.findBymem(mem_id);
	}
	public List<ArtreVO> findreBymem(String mem_id){
		return dao2.getByMem(mem_id);
	}
	public ArticleVO findByArt_id(String art_id) {
		return dao.findByPrimaryKey(art_id);
	}
	public ArtcomVO  findByArt_msg_id(String art_msg_id){
		return daocom.findByPrimaryKey(art_msg_id);
	}
	public List<ArticleVO>getAll(){
		return dao.getAll();
	}
	public List<ArtcolVO> findColByMem_id(String mem_id){
		return daocol.findallByMem(mem_id);
	}
	public List<ArtcomVO> findAllByArt_id(String art_id){
		return daocom.getByartid(art_id);
	}
	public List<ArtreVO> getAllre(){
		return dao2.getAll();
	}
	public List<ArtreVO> getAllByArt(String art_id){
		return dao2.getByArt_id(art_id);
	}
	public ArtcomVO addCom(String art_id,String mem_id,String art_msg_cnt,byte[]art_msg_pic){
		ArtcomVO artcomVO = new ArtcomVO();
		artcomVO.setArt_id(art_id);
		artcomVO.setMem_id(mem_id);
		artcomVO.setArt_msg_cnt(art_msg_cnt);
		artcomVO.setArt_msg_pic(art_msg_pic);
		daocom.insert(artcomVO);
		return artcomVO;
	}
	public ArtreVO addRe(String art_id,String mem_id,String re_cnt,byte[] re_pic) {
		ArtreVO artreVO = new ArtreVO();
		artreVO.setArt_id(art_id);
		artreVO.setMem_id(mem_id);
		artreVO.setRe_cnt(re_cnt);
		artreVO.setRe_pic(re_pic);
		dao2.insert(artreVO);
		return artreVO;
	}
	public ArtcolVO addCol(String mem_id , String art_id){
		
		ArtcolVO artcolVO = new ArtcolVO();
		artcolVO.setMem_id(mem_id);
		artcolVO.setArt_id(art_id);
		
		daocol.insert(artcolVO);
		return artcolVO;
	}
	public ArticleVO cahgeSts(String art_id , String art_sts){
		ArticleVO artVO = new ArticleVO();
		artVO.setArt_id(art_id);
		artVO.setArt_sts(art_sts);
		
		dao.updatestatus(artVO);
		return artVO;
	}
	public void deleteCol(String mem_id , String art_id){
		daocol.delete(mem_id, art_id);
	}
	public ArtreVO updatereSts(String re_id , String re_sts){
		ArtreVO artreVO = new ArtreVO();
		artreVO.setRe_id(re_id);
		artreVO.setRe_sts(re_sts);
		dao2.updatests(artreVO);
		return artreVO;
	}

}
