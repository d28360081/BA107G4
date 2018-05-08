package android.com.promotion.model;

import java.util.List;

public class PromotionService {
	private PromotionDAO_interface dao;
	public PromotionService(){
		dao=new PromotionDAO();
	}
	public List<PromotionVO> getAll(){
		return dao.getAll();
	}
}
