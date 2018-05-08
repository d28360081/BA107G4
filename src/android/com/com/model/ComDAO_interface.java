package android.com.com.model;

import java.util.List;



import android.com.compant.model.ComPantLsVO;
import android.com.member.model.MemberVO;



public interface ComDAO_interface {
		public List<ComVO>  selectWithAllComCase();

	    public  List<ComVO>  selectWithAllGroupBuyCase();

	    public  ComVO insert(ComVO comvo);

	    public  String joinselect(String s, String s1);


	    public  ComVO  update(ComVO comvo);
	    public  ComVO  updatePic(ComVO comvo);
		public ComPantLsVO selectinsertcom(ComVO comvo,MemberVO memvo);
		
		
		public List<ComVO> selectByMemID(String memid);
		public  ComVO selectDelete(ComVO comvo);
		public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list);
		
		public List<ComVO> selectJoinBuyFromComPantsLs(List<ComPantLsVO> list);
		
		public List<ComVO> selectHistory(String mem_id);
	


}
