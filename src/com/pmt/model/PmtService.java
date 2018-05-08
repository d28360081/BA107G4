package com.pmt.model;

import java.sql.Timestamp;
import java.util.List;

import com.pmt_det.model.PmtDetVO;

public class PmtService {
	private PmtDAO_interface dao;

	public PmtService() {
		dao = new PmtDAO();
	}
	public void addPmt(PmtVO pmtVO) {
		dao.insert(pmtVO);		
	}
	public String addPmt(PmtVO pmtVO, List<PmtDetVO> pmtdetList) {
		return dao.insert(pmtVO, pmtdetList);
		

	}

	public PmtVO updatePmt(String pmt_id, String emp_no, String pmt_name, String pmt_intro, Timestamp pmt_s_date,
			Timestamp pmt_e_date, String pmt_sts, byte[] pmt_pic,Double pmt_discount) {

		PmtVO pmtVO = new PmtVO();
		pmtVO.setPmt_id(pmt_id);
		pmtVO.setEmp_no(emp_no);
		pmtVO.setPmt_name(pmt_name);
		pmtVO.setPmt_intro(pmt_intro);
		pmtVO.setPmt_s_date(pmt_s_date);
		pmtVO.setPmt_e_date(pmt_e_date);
		pmtVO.setPmt_sts(pmt_sts);
		pmtVO.setPmt_pic(pmt_pic);
		pmtVO.setPmt_discount(pmt_discount);
		dao.update(pmtVO);

		return pmtVO;
	}

	public void updatePmt(PmtVO pmtVO){
		dao.update(pmtVO);
	}
	
	public void update(String pmt_id, String pmt_sts) {
		dao.update(pmt_id, pmt_sts);
	}
	public void update(PmtVO pmtVO, List<PmtDetVO> pmtdetList) {
		dao.update(pmtVO, pmtdetList);
	}
	public void deletePmt(String pmt_id) {
		dao.delete(pmt_id);
	}

	public PmtVO getOnePmt(String pmt_id) {
		return dao.findByPrimaryKey(pmt_id);
	}

	public List<PmtVO> getAll() {
		return dao.getAll();
	}

	public List<PmtVO> getOnlineAll() {
		return dao.getOnlineAll();
	}
	
	public List<PmtVO> getByKeyword(String keyword) {
		return dao.getByKeyword(keyword);
	}
}
