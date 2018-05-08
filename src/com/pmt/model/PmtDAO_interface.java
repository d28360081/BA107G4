package com.pmt.model;

import java.util.List;

import com.pmt_det.model.PmtDetVO;

public interface PmtDAO_interface {
	public void insert(PmtVO pmtVO);
	public String insert(PmtVO pmtVO, List<PmtDetVO> pmtdetList);
	public void update(PmtVO pmtVO);
	public void update(PmtVO pmtVO, List<PmtDetVO> pmtdetList);
	public void update(String pmt_id, String pmt_sts);
	public void delete(String pmt_id);
	public PmtVO findByPrimaryKey(String pmt_id);
//	public List<PmtVO> findByPrimaryKey(String pmt_id);
	public List<PmtVO> getByKeyword(String keyword);
	public List<PmtVO> getAll();
	public List<PmtVO> getOnlineAll();
	
	

}
