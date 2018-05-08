package android.com.com.model;

import java.util.List;

import android.com.compant.model.ComPantLsVO;
import android.com.member.model.MemberVO;

public class ComService {
	private ComDAO_interface dao;

	public ComService() {
		dao=new ComDAO();
	}
	public List<ComVO>  selectWithAllComCase(){
		return dao.selectWithAllComCase();
		
	}

    public  List<ComVO>  selectWithAllGroupBuyCase(){
		return dao.selectWithAllGroupBuyCase();

    }
    public ComPantLsVO insertCom(ComVO comvo,MemberVO memvo){
		return dao.selectinsertcom(comvo,memvo);
	}
    public ComVO update(ComVO comvo) {
    	return dao.update(comvo);
    }
    public ComVO updatePic(ComVO comvo) {
    	return dao.updatePic(comvo);
    }
    public List<ComVO> selectByMemID(String memid){
    	return dao.selectByMemID(memid);
    }
    public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list) {
    	return dao.selectJoinGroupFromComPantsLs(list);
    }
  
    

}
