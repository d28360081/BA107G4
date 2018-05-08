package android.com.compant.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class ComPantLsService implements ComPantInterface{
	private ComPantLsDAOJNDI compantlsdaojndi=new ComPantLsDAOJNDI();
	@Override
	public ComPantLsVO select(String memid, String comid) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.select(memid, comid);
	}

	@Override
	public List<ComPantLsVO> selectSTS(String comid, String sts) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.selectSTS(comid, sts);
	}

	@Override
	public void insert(ComPantLsVO compantlsvo,Connection c) {
		compantlsdaojndi.insert(compantlsvo,c);
		
	}

	@Override
	public void update(ComPantLsVO compantlsvo) {
		compantlsdaojndi.update(compantlsvo);
		
	}

	@Override
	public void delete(String memid, String comid) {
		compantlsdaojndi.delete(memid, comid);
		
	}

	@Override
	public List<ComPantLsVO> selectAll(String mem_id) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.selectAll(mem_id);
	}

	@Override
	public List<ComPantLsVO> selectAllinCase(String com_id) {
		
		return compantlsdaojndi.selectAllinCase(com_id);
	}
	
	public HashMap<String,ComPantLsVO> selectUnVoted(String com_id){ 
		return compantlsdaojndi.selectUnVoted(com_id);
	}
	public List<ComPantLsVO> selectToVote(String com_id){
		return compantlsdaojndi.selectToVote(com_id);
		
	}

	@Override
	public boolean isAlreadyInPartList(String memid, String comid) {
		
		return compantlsdaojndi.isAlreadyInPartList(memid, comid);
	}

	@Override
	public List<ComPantLsVO> selectAllPartList(String com_id) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.selectAllinCase(com_id);
	}

	@Override
	public Boolean insert(ComPantLsVO compantlsvo) {
		return compantlsdaojndi.insert(compantlsvo);
		
	}

	@Override
	public void updateSts(String memid, String comid) {
		 compantlsdaojndi.updateSts(memid,comid);
	}

	

}
