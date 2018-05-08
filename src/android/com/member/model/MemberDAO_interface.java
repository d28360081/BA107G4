package android.com.member.model;

public interface MemberDAO_interface {
	boolean isMember(String userId, String password);
	MemberVO findByAccandPsw(String account, String password);

    boolean update(MemberVO membervo);

    MemberVO findById(String userId);
    MemberVO selectPic(String userId);
	
}
