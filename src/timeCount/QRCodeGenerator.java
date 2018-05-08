package timeCount;
import com.comauth.model.ComQrService;
import com.comauth.model.ComQrVO;
import com.compant.model.ComPantLsService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.member.model.MemService;
import com.member.model.MemVO;

import StoreOrderController.MailService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class QRCodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "C:\\Users\\Java\\Desktop\\MyQRCode.png";
    private final static int height=350;
    private final static int width=350;
    
    private static BufferedImage generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);     
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    private static byte[] returnByte(String text) {
    	  try {
    	
    	
    		  BufferedImage bi=generateQRCodeImage(text,width,height,QR_CODE_IMAGE_PATH);
    		  ByteArrayOutputStream bao=new ByteArrayOutputStream();
    		  ImageIO.write(bi, "png", bao);
    		  
    	      return bao.toByteArray();
    	  
    	  
    	  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public static void pswGenerate(String ContextPath,String com_id,String mem_id){
    	try {
    	StringBuffer sb=new StringBuffer();
    	StringBuffer sb2=new StringBuffer();
    	StringBuffer sb3=new StringBuffer();
        MemService ms=new MemService();
        MemVO mv=ms.getOneMem(mem_id);
    	
    	
    	sb.append(ContextPath);
    	sb.append("/ComAuthQrController?com_id="+com_id);
    	sb.append("&mem_id="+mem_id);
    	sb.append("&auth_number=");
    	
    	//密碼部分
    	
    	
    	 for(int i=0;i<5;i++){
    		char a=(char)((int)(Math.random()*26) + 65);
    		sb2.append(a);
    	  }
    	 
    	//寫入資料庫
    	
    	ComQrVO comqrvo=new ComQrVO();
    	ComQrService comqrservice=new ComQrService();
    	comqrvo.setCom_id(com_id);
    	comqrvo.setMem_id(mem_id);
    	comqrvo.setAuth_number(sb2.toString());
    	
    	
    	sb.append(sb2.toString());
    	byte[] authNumberBytes=returnByte(sb.toString());
    	comqrvo.setQr_code(authNumberBytes);
    	comqrservice.insert(comqrvo);
    	MailService mailservice=new MailService();
    	
    	String messageText="賣家已經出貨,收貨的同時可以掃描QR CODE或是輸入認證碼進行收貨,認證碼如下:"+sb2.toString();
		
		mailservice.sendMail(mv.getMem_email(), "取獲通知", messageText);
    	
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    public static void main(String[] args) {
        
//        	System.out.println(pswGenerate("http://localhost:8081/BA107G4/ComGroupEditController","COM001"));
//            generateQRCodeImage("https://www.callicoder.com/generate-qr-code-in-java-using-zxing/", 350, 350, "C:\\Users\\Java\\desktop\\img\\1.png");
//            System.out.println(returnByte("https://www.callicoder.com/generate-qr-code-in-java-using-zxing/").length);
//            
//        } catch (WriterException e) {
//            System.out.println("Could not generate QR Code, WriterException :12312312: " + e.getMessage());
//        } catch (IOException e) {
//            System.out.println("Could not generate QR Code, IOException :1232323232323: " + e.getMessage());
//        }
    }
}
