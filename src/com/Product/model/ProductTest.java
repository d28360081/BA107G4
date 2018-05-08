package com.Product.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductTest {

	public static void main(String[] args) {
		ProductDAO dao =new ProductDAO();
		ProductVO productVO1 =new ProductVO();
		//新增
//		productVO1.setIt_num(5);
//		productVO1.setIt_name("雷姆");
//		productVO1.setIt_intro("只是個砲灰");
//		byte[] pic = null;
//		try {
//			pic = getPictureByteArray("C://Users/Java/Desktop/Rem/Rem2.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		productVO1.setIt_pic(pic);
//		productVO1.setIt_prc((double) 800);
//		productVO1.setIt_sts("上架");
//		dao.insert(productVO1);
//		
		//修改
//		ProductVO productVO2 =new ProductVO();
//		productVO2.setIt_num(6);
//		productVO2.setIt_name("雷姆");
//		productVO2.setIt_intro("是真實存在的");
//		productVO2.setIt_pic(null);
//		productVO2.setIt_prc((double) 6000);
//		productVO2.setIt_sts("下架");
//		productVO2.setIt_id("IT001");
		//dao.update(productVO2);
		//刪除
		//dao.delete("IT002");
		ProductVO productVO3 =dao.findByPrimaryKey("IT001");
		System.out.println(productVO3.getIt_id());
		System.out.println(productVO3.getSt_id());
		System.out.println(productVO3.getIt_num());
		System.out.println(productVO3.getIt_name());
		System.out.println(productVO3.getIt_intro());
		System.out.println(productVO3.getIt_pic());
		System.out.println(productVO3.getIt_prc());
		System.out.println(productVO3.getIt_sts());
		
	
	
		
	Map<String, ProductVO> map = dao.getAllProductMap();
	
	for(String ProductVO4:map.keySet()){
		System.out.println(ProductVO4.toString());
		System.out.println(map.get(ProductVO4));		
	}
	
	
	
	}
	

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	// Handle with byte array data
		public static void readPicture(byte[] bytes) throws IOException {
			FileOutputStream fos = new FileOutputStream("Output/rem.jpg");
			fos.write(bytes);
			fos.flush();
			fos.close();
		}

}
