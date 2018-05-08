package android.com.member.model;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao=new MemberDAO();
	}
	public Boolean isMember(String account,String password){
		return dao.isMember(account, password);
		
	}
	public MemberVO findByAccandPsw(String account,String password) {
		return dao.findByAccandPsw(account, password);
		
	}
	public Boolean update(MemberVO member) {
		return dao.update(member);
	}
	public MemberVO findById(String userid) {
		return dao.findById(userid);
		
	}
	public MemberVO selectPic(String userId) {
		return dao.selectPic(userId);
	}
	

}
