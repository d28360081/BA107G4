package android.com.comnof.model;

import java.util.List;

public class ComNofService implements ComNofInterface{
private ComNofJndi comnofjndi=new ComNofJndi();
	@Override
	public ComNofVO select(String nof_id) {
		
		return comnofjndi.select(nof_id);
	}

	@Override
	public void delete(String nof_id) {
		comnofjndi.delete(nof_id);
		
	}

	@Override
	public ComNofVO insert(ComNofVO comnofvo) {
		
		return comnofjndi.insert(comnofvo);
	}

	@Override
	public List<ComNofVO> selectAll(String mem_id) {
		// TODO Auto-generated method stub
		return comnofjndi.selectAll(mem_id);
	}

	@Override
	public void update(ComNofVO comnofvo) {
		comnofjndi.update(comnofvo);
		
	}

	public List<ComNofVO> selectAllUnread(String mem_id){
		return comnofjndi.selectAllUnread(mem_id);
	}
	@Override
	public List<ComNofVO> selectCom_Nof(String com_id) {
		// TODO Auto-generated method stub
		return comnofjndi.selectCom_Nof(com_id);
	}

}
