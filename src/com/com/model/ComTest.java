package com.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.compant.model.ComPantLsVO;

public class ComTest {
	public static void main(String args[]){
ComVO cv=new ComVO();
ComDaoJdbc cdj=new ComDaoJdbc();


cv.setMem_id("M000001");
cv.setLmt_atd_clv(0);
cv.setLmt_auc_lv(0);
cv.setLmt_del_prc(0);
cv.setCom_prc(0);
cv.setLmt_m_prc(0);
cv.setCom_mx_num(0);
cv.setCom_min_num(0);
cv.setBns_number(0);
////���շs�W��k
cv=cdj.insert(cv);
//System.out.println(cv.getCom_id());
////���լd�ߤ�k
cdj.select(cv.getCom_id());
System.out.println("com_id:_"+cv.getCom_id()+"_meme_id: "+cv.getMem_id());
////���խק��k
cv.setCom_cnt("test");
cdj.update(cv);
////���էR����k
cdj.delete(cv.getCom_id());


		
	}

}
