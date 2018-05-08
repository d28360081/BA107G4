package com.news.model;

import java.util.List;

public class NewsService {

	private NewsDAO_interface dao;
	
	public NewsService(){
		dao = new NewsDAO();
	}
	
	public String add(String emp_no, String ns_tit, String ns_cnt,String ns_cdate){
		NewsVO nsVO = new NewsVO();
		nsVO.setEmp_no(emp_no);
		nsVO.setNs_tit(ns_tit);
		nsVO.setNs_cnt(ns_cnt);
		nsVO.setNs_cdate(ns_cdate);
		String pk = dao.add(nsVO);
		return pk;
	}
	public NewsVO update(String ns_tit, String ns_cnt, String ns_sts, String ns_id){
		NewsVO nsVO = new NewsVO();
		nsVO.setNs_tit(ns_tit);
		nsVO.setNs_cnt(ns_cnt);
		nsVO.setNs_sts(ns_sts);
		nsVO.setNs_id(ns_id);
		dao.update(nsVO);
		return nsVO;
		
	}
	public NewsVO updatests(String ns_id, String ns_sts){
		NewsVO nsVO = new NewsVO();
		nsVO.setNs_id(ns_id);
		nsVO.setNs_sts(ns_sts);
		dao.updatests(nsVO);
		return nsVO;
	}
	public NewsVO updatelike(String ns_id, Integer ns_like){
		NewsVO nsVO = new NewsVO();
		nsVO.setNs_id(ns_id);
		nsVO.setNs_like(ns_like);
		dao.updatelike(nsVO);
		return nsVO;
	}
	public List<NewsVO> getAll(){
		return dao.getAll();
	}
	public NewsVO findByPk(String ns_id){
		return dao.findByPK(ns_id);
	}
	public void delete(String ns_id){
		 dao.delete(ns_id);
	}
	public List<NewsVO> selectsts(String ns_sts){
		return dao.selectsts(ns_sts);
	}
}
