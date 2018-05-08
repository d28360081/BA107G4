package pictureWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;



public class PICTest {
//	public static void main(String args[]){
//		Jedis a=new Jedis("localhost");
//		List<String> listmv=new ArrayList<String>();
//		MemVO m=new MemVO();
//		m.setMem_id("M000001");
//		m.setBonus(12375);
//		listmv.add(m.getMem_id());
//		try {
//			ByteArrayOutputStream bao=new ByteArrayOutputStream();
//			ObjectOutputStream oos=new ObjectOutputStream(bao);
//			oos.writeObject(m);
//			oos.flush();
//			byte[] obarray=bao.toByteArray();
//			a.set(m.getMem_id().getBytes(), obarray);
//			
//			
//			for(String s:listmv){
//				byte[] value=a.get(s.getBytes());
//				ByteArrayInputStream bso=new ByteArrayInputStream(value);
//				ObjectInputStream ois=new ObjectInputStream(bso);
//				MemVO b=(MemVO)ois.readObject();
//				System.out.println(b.getMem_id());
//				System.out.println(b.getBonus());
//			}
//			
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
