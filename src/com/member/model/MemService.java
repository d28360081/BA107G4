package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao ;
	
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String acc, String psw , Date bir_dt ,String address,String mem_pay,String mem_email,String mem_name,byte[] mem_photo) {
		MemVO memVO = new MemVO();
		
		memVO.setAcc(acc);
		memVO.setPsw(psw);
		memVO.setBir_dt(bir_dt);
		memVO.setAddress(address);
		memVO.setMem_pay(mem_pay);
		memVO.setMem_email(mem_email);
		memVO.setMem_name(mem_name);
		memVO.setMem_photo(mem_photo);
		
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateMem(String acc,String psw,Date bir_dt,String address,String mem_pay,byte[]mem_photo,String mem_id,String mem_email,String mem_name) {
		MemVO memVO = new MemVO();
		
		memVO.setAcc(acc);
		memVO.setPsw(psw);
		memVO.setBir_dt(bir_dt);
		memVO.setAddress(address);
		memVO.setMem_pay(mem_pay);
		memVO.setMem_photo(mem_photo);
		memVO.setMem_email(mem_email);
		memVO.setMem_name(mem_name);
		memVO.setMem_id(mem_id);
		
		dao.update(memVO);
		
		return memVO;
	}
	
	public void deleteMem(String mem_id) {
		dao.delete(mem_id);
	}
	
	public MemVO getOneMem(String mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public List<MemVO>getAll(){
		return dao.getAll();
	}
	
	public String authuser(String acc,String psw) {
		
		MemVO memVO = new MemVO();
		memVO.setAcc(acc);
		memVO.setPsw(psw);
		
		return dao.authuser(memVO);
	}
	
	public boolean isUserIdExist(String acc) {
		
		return dao.isUserIdExist(acc);
	}
	
	public void readPic(String acc) {
		dao.readPic(acc);
	}
	public MemVO getByAcc(String acc) {
		return dao.findByAcc(acc);
	}
	public MemVO updateBonus(String mem_id , Integer bonus){
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setBonus(bonus);
		
		dao.updateBonus(memVO);
		
		return memVO;
	}
	public MemVO updateAuth(Integer po_auth,Integer st_auth,Integer com_auth,Integer par_auth,String mem_id){
		MemVO memVO = new MemVO();
		
		memVO.setPo_auth(po_auth);
		memVO.setSt_auth(st_auth);
		memVO.setCom_auth(com_auth);
		memVO.setPar_auth(par_auth);
		memVO.setMem_id(mem_id);
		
		dao.updateAuth(memVO);
		return memVO;
	}
	public MemVO updateMemAuth(String mem_id){
		
		MemService memSrc = new MemService();
		MemVO memVO = memSrc.getOneMem(mem_id);
		Integer bonus = memVO.getBonus();
		if(bonus<5){
			memSrc.updateAuth(0, 0, 0, 0, mem_id);
		}
		else if(bonus>=400){
			memSrc.updateAuth(1, 1 , 1, 1, mem_id);
		}
		else if(bonus>=300){
			memSrc.updateAuth(1, 0, 1 ,1, mem_id);
		}
		else if(bonus>=200){
			memSrc.updateAuth(1, 0, 0 , 1, mem_id);
		}
		else if(bonus<200){
			memSrc.updateAuth(1, 0, 0 , 0, mem_id);
		}
		return memVO;
		
	}
}
