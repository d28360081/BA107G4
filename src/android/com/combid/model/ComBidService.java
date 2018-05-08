package android.com.combid.model;

public class ComBidService {
	private ComBid_interface dao;
	
	public ComBidService() {
		dao=new ComBidDAO();
	}
	public Boolean isAlreadyIn(String memid, String comid) {
		return dao.isAlreadyIn(memid, comid);
	}
	public Boolean insert(ComBidVO combidvo) {
		return dao.insert(combidvo);
	}
	
}
