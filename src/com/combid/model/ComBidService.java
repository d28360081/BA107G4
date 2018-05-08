package com.combid.model;

import java.util.HashMap;
import java.util.List;

public class ComBidService implements ComBidInterface{
private ComBidDAOJndi combiddaojndi=new ComBidDAOJndi();

	
	public ComBidService() {
	super();
	System.out.println("con");
}

	@Override
	public ComBidVO select(String memid, String comid) {
		// TODO Auto-generated method stub
		return combiddaojndi.select(memid, comid);
	}

	@Override
	public void insert(ComBidVO combidvo) {
		combiddaojndi.insert(combidvo);
		
	}

	@Override
	public void update(ComBidVO combidvo) {
		combiddaojndi.update(combidvo);
		
	}

	@Override
	public void delete(String memid, String comid) {
		combiddaojndi.delete(memid, comid);
		
	}

	@Override
	public List<ComBidVO> selectAll(String comid) {
		// TODO Auto-generated method stub
		return combiddaojndi.selectAll(comid);
	}
	public List<ComBidVO> selectVoteCandidate(String com_id){
		
		return combiddaojndi.selectVoteCandidate(com_id);
	}
	public List<ComBidVO> selectWinner(String mem_id){
		return combiddaojndi.selectWinner(mem_id);
	}
    public ComBidVO selectBiddingWinner(String com_id) {
    	
    	System.out.println(combiddaojndi.selectBiddingWinner(com_id).getMem_id());
    	return combiddaojndi.selectBiddingWinner(com_id);
    	
    }
    public HashMap<String,ComBidVO> toMap(List<ComBidVO> list){
    	
    	HashMap<String,ComBidVO> HM=new HashMap<String,ComBidVO>();
    	for(ComBidVO cbv :list){
    		
    		HM.put(cbv.getCom_id(), cbv);
    	
    	}
    	return HM;
    }
 public HashMap<String,ComBidVO> toMap2(List<ComBidVO> list){
    	
    	HashMap<String,ComBidVO> HM=new HashMap<String,ComBidVO>();
    	for(ComBidVO cbv :list){
    		HM.put(cbv.getMem_id(), cbv); 	  
    	}
    	return HM;
    }
}
