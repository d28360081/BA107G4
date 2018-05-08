package pictureWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.sql.BLOB;

public class pictureWriter {
  public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException{
	  try{
	    Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BA107G4","123");
		PreparedStatement prst=c.prepareStatement("UPDATE COMMISION SET COM_PIC1=? , COM_PIC2=?  WHERE COM_ID=?");

		String path="C:\\G4_2\\BA107G4Final1.5\\WebContent\\img\\jdbcPic";
		File f=new File(path);
	    String[] files=f.list();
	    int count =0;
	    for(String d:files){
	    	
	    	StringBuffer sb=new StringBuffer();
	    	sb.append(path);
	    	sb.append("\\"+d);
	    	
	    	File f2=new File(sb.toString());
	    	String[] pics=f2.list();
	    	for(int i=0;i<pics.length;i++){
	    		
	    		String picName=pics[i];
	    		File pic=new File(sb.toString()+"\\"+picName);
	    		
	    		FileInputStream fop=new FileInputStream(pic);
	    		prst.setBinaryStream(i+1, fop, pic.length());
	    		
	    		
	    	}
	    	
	    	prst.setString(3, files[count]);
	    
	    	prst.executeUpdate();
	    	
	    	count++;
	    }
	    //更新員工照片
	    
	    PreparedStatement prst2=c.prepareStatement("update employee set EMP_PIC=? where EMP_NO=?");
	    String empPath="C:\\G4_2\\BA107G4Final1.5\\WebContent\\img\\EmpPic";
	    String empno="EMP00";
	    File empFile=new File(empPath);
	    String[] empPicArray=empFile.list();
	    int count2=1;
	    for(String empPic:empPicArray){
	    	StringBuffer sb=new StringBuffer();
	    	sb.append(empPath+"\\"+empPic);
	    	FileInputStream empPicFile=new FileInputStream(new File(sb.toString()));
	    	byte[] empPicByte=new byte[empPicFile.available()];
	    	System.out.println(empPic.substring(0, 5));
	    	empPicFile.read(empPicByte,0,empPicFile.available());
	    	prst2.setBytes(1, empPicByte);
	    	prst2.setString(2, empno+count2);
	    	prst2.executeUpdate();
	    	count2++;
	    }
	    c.commit();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	    
	    
	    
  }
}
