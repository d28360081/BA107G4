package com.pmt_det.model;

import java.sql.Connection;
import java.util.List;

public class PmtDetService{
	private PmtDetDAO_interface pmtdetDao;
	
	public PmtDetService(){
		pmtdetDao = new PmtDetDAO();
	}
	
	public void addPmtDet(PmtDetVO pmtdetVO){
		pmtdetDao.insert(pmtdetVO);
	}
	public void addPmtDet2(PmtDetVO pmtdetVO, Connection con){
		pmtdetDao.insert(pmtdetVO);
	}
	
	public List<PmtDetVO> getByPMT_ID(String pmt_id){
		
		return pmtdetDao.getByPMT_ID(pmt_id);
	}
	public List<PmtDetVO> getByIT_ID(String it_id){
		return pmtdetDao.getByIT_ID(it_id);
	}
	public void deletePmtDet(String pmt_id,String it_id){
		pmtdetDao.delete(pmt_id, it_id);
	}

	public List<PmtDetVO> getAll(){
		return pmtdetDao.getAll();
	}
	public List<PmtDetVO> getAllAvaliable(){
		PmtDetDAO pmtdetDao2=new PmtDetDAO();
		return pmtdetDao2.getAllAvaliable();
	}
	
}