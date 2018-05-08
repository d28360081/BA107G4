package com.FavoriteProduct.model;

import java.util.List;


public class FavoriteProductTest {

	public static void main(String[] args) {
		FavoriteProductDAO dao=new FavoriteProductDAO();
		FavoriteProductVO favoriteProductVO1=new FavoriteProductVO();
		
		favoriteProductVO1.setMem_id("M000001");
		favoriteProductVO1.setIt_id("IT001");
		dao.insert(favoriteProductVO1);
		
		FavoriteProductVO favoriteProductVO2=new FavoriteProductVO();//		
		favoriteProductVO2.setIt_id("IT002");
		favoriteProductVO2.setMem_id("M000001");
		dao.update(favoriteProductVO2);		
		
		//dao.delete("M000002");
		FavoriteProductVO favoriteProductVO3=dao.findByPrimaryKey("M000001");
		System.out.println(favoriteProductVO3.getMem_id());
		System.out.println(favoriteProductVO3.getIt_id());

		
		
		List<FavoriteProductVO>list=dao.getAll();
		for(FavoriteProductVO favoriteProductVO4:list){
			System.out.println(favoriteProductVO4.getMem_id());
			System.out.println(favoriteProductVO4.getIt_id());		
		}
		
	
		
	}

}
