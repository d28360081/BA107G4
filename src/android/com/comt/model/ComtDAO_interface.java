package android.com.comt.model;

import java.util.List;


public interface ComtDAO_interface {
//	public  ComComtVO selectByPrimaryKey(String s, String s1);

    public  ComComtVO insert(ComComtVO comcomtvo);
//
//    public  void delete(String s, String s1);
//
//    public  void update(ComComtVO comcomtvo);

    public  List selectAll(String comId);
	

}
