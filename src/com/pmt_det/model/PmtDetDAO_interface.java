package com.pmt_det.model;

import java.sql.Connection;
import java.util.List;

import com.emp.model.EmpVO;

public interface PmtDetDAO_interface {
	public void insert(PmtDetVO pmtdetVO);
	//一個促銷方案對一個商品 會比較好對 資料庫也是一條條存入
//	public void update(PmtDetVO pmtdetVO);//裡面的商品只能新增 刪除
	public void delete(String pmt_id,String it_id);
	public PmtDetVO findByPrimaryKey(String pmt_id,String it_id);
	public List<PmtDetVO> getByPMT_ID(String pmt_id);
	public List<PmtDetVO> getByIT_ID(String it_id);
	public List<PmtDetVO> getAll(); 
	public void insert2(PmtDetVO pmtdetVO, Connection con);
	public void delete2(String pmt_id, Connection con);	
	
}
