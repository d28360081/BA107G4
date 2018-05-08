package com.member.controller;

import com.member.model.MemService;
import com.member.model.MemVO;

public class updateMemAuth {
	public MemVO updateAuth(String mem_id){
		
		MemService memSrc = new MemService();
		MemVO memVO = memSrc.getOneMem(mem_id);
		Integer bonus = memVO.getBonus();
		if(bonus<5){
			memSrc.updateAuth(0, memVO.getSt_auth(), memVO.getCom_auth(), memVO.getPar_auth(), mem_id);
		}
		else if(bonus>500 && memVO.getMem_eva()>3){
			memSrc.updateAuth(memVO.getPo_auth(), 1 , memVO.getCom_auth(), memVO.getPar_auth(), mem_id);
		}
		else if(bonus<5 || memVO.getMem_eva()<3){
			memSrc.updateAuth(memVO.getPo_auth(), memVO.getSt_auth(), 0 , memVO.getPar_auth(), mem_id);
		}
		else if(bonus<5 || memVO.getMem_eva()<2){
			memSrc.updateAuth(memVO.getPo_auth(), memVO.getSt_auth(), memVO.getCom_auth() , 0, mem_id);
		}
		return memVO;
		
	}
}
