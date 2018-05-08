package com.cocmt.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.com.model.ComDaoJdbc;
import com.com.model.ComVO;

import oracle.sql.CLOB;

public class Com_ComtTest {
public static void main(String args[]){
ComComtDAOJDBC s=new ComComtDAOJDBC();
ComComtVO a=new ComComtVO();

//���շs�W
a.setCom_id("COM005");
a.setMem_id("M000007");
a.setComt_cnt("test");
a=s.insert(a);
System.out.println(a.getComt_cnt());
System.out.println(a.getComt_id());



//���լd��
//s.selectByPrimaryKey(a.getCom_id(),"COM_COMT015");
//System.out.println(s.selectByPrimaryKey(a.getCom_id(),"COM_COMT023").getComt_cnt());
//
////���է�s

a.setComt_cnt("test2");
s.update(a);
//
//
////���էR��
s.delete("COM0015", "COM_COMT001");

//���ե���
//List<ComComtVO> list=s.selectAll();
//for(ComComtVO c:list){
//	System.out.println(c.getCom_id());
//	System.out.println(c.getComt_id());
//	System.out.println(c.getMem_id());
//}



}
}
