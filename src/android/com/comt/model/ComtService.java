package android.com.comt.model;

import java.util.List;



public class ComtService {
	private ComtDAO_interface dao;
	public ComtService() {
		dao=new ComtDAO();
		}


    public  List<ComComtVO>  selectAll(String comId){
		return dao.selectAll(comId);

    }
    public ComComtVO insert(ComComtVO comtvo) {
		return dao.insert(comtvo);
	}
		
	
	

}
