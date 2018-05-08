package timeCount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class eBayCrawler {
	public List<eBayCrawlerVO> getEbay(){
		try {
			HashMap<String,String> cookies=new HashMap();
			List<eBayCrawlerVO> list=new ArrayList<eBayCrawlerVO>();
			Document doc=Jsoup.connect("https://www.ebay.com/globaldeals").cookies(cookies).get();
			if(doc!=null) {
				//先抓到商品div
				Elements elements=doc.select(".dne-itemtile");
				int count=1;
				while(count<10) {
					String name=elements.get(count).select(".dne-itemtile-title").text();
					String price=elements.get(count).select(".dne-itemtile-price").text();
					String img=elements.get(count).getElementsByTag("img").attr("src");

					if(name.trim()!=null&&price.length()>2&&img.trim()!=null) {
					eBayCrawlerVO ec=new eBayCrawlerVO();
					ec.setName(name);
					ec.setPrice(price);
					ec.setImg(img);
					list.add(ec);
					count++;
					}else {
						count++;
					}
					
				}
				
				
			    
			}
			return list;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	public static void main(String args[]) {
		eBayCrawler ec=new eBayCrawler();
		for(eBayCrawlerVO ev:ec.getEbay()) {
			System.out.println("name:"+ev.getName());
			System.out.println("price"+ev.getPrice());
			System.out.println("img"+ev.getImg());
			System.out.println("-------------------------------");
		}
	}
} 
