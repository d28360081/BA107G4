package timeCount;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.com.model.ComClickJdbc;
import com.com.model.ComDaoJdbc;
import com.com.model.ComService;
import com.com.model.ComVO;

import redis.clients.jedis.Jedis;

public class JedisManager {
private static Jedis jedis;  
ComClickJdbc comservice;	
TimeCountTool timecounttool=new TimeCountTool();
	public  JedisManager() {
		this.comservice=new ComClickJdbc();	
		jedis=new Jedis();
		//團購新增
		List<ComVO> list=comservice.selectAll();
		
		//代購新增
		List<ComVO> list2=comservice.selectDaiGo();
		//代購加入JEDIS
		for(ComVO cv:list2) {
			jedis.zadd("DaiGOMap", 0, cv.getCom_id());
		}
		//團購加入JEDIS
		for(ComVO cv:list) {
			jedis.zadd("TuanGOMap", 0, cv.getCom_id());
		}
		
	}
	//新增代購典籍
	public void addDaiGOClick(String com_id) {
		if(jedis.isConnected()) {
			//代表不存在
			System.out.println("number DaiGOMap"+jedis.zscore("DaiGOMap", com_id));
			System.out.println(jedis.zcount("DaiGOMap", 0, -1));
			if(jedis.zrank("DaiGOMap", com_id)==null) {
				jedis.zadd("DaiGOMap", 0 ,com_id);
			//存在將數量向上增加
				
			}else {
				jedis.zincrby("DaiGOMap", 1, com_id);
			}
			
		}
		
	}
	

	
	//新增團購典籍
	public void addTuanGOClick(String com_id) {
		if(jedis.isConnected()) {
			//代表不存在
			System.out.println("number TuanGOMap"+jedis.zscore("TuanGOMap", com_id));
			if(jedis.zrank("TuanGOMap", com_id)==null) {
				jedis.zadd("TuanGOMap", 0 ,com_id);
			//存在將數量向上增加
				
			}else {
				jedis.zincrby("TuanGOMap", 1, com_id);
			}
			
		}
		
	}
	//回傳排序團購陣列
	public List<ComVO> toTuanGoListFromClick(){
		Set<String> sortedSet= jedis.zrevrange("TuanGOMap", 0, -1);
		List<ComVO> tuanGOlist=timecounttool.toList();
		List<ComVO> SortedList=new ArrayList<ComVO>();
		int count=0;
		System.out.println("size__:"+sortedSet.size());
				for(String s:sortedSet) {
					for(ComVO CV:tuanGOlist) {
						if(s.equals(CV.getCom_id())) {
							SortedList.add(comservice.select(s));	
						}
				
					}
				}
		return SortedList;
	}
	//回傳排序代購List
	public List<ComVO> toDaiGoListFromClick(){
		Set<String> sortedSet= jedis.zrevrange("DaiGOMap", 0, -1);
		List<ComVO> SortedList=new ArrayList<ComVO>();
		
		for(String s:sortedSet) {
			SortedList.add(this.comservice.select(s));
		}
		return SortedList;
	}
//	
//	
//	
//	
//	public static void main(String args[]){
//					JedisManager jm=new JedisManager();
////					jm.addTuanGOClick("aa");
////					jm.addTuanGOClick("bb");
////					jm.addTuanGOClick("bb");
////					jm.addTuanGOClick("bb");
////					jm.addTuanGOClick("bb");
////					jm.addDaiGOClick("cc");
//					System.out.println(jm.jedis.zcard("TuanGOMap"));
//					System.out.println(jm.jedis.zcard("DaiGOMap"));
//					
//					System.out.println(jm.jedis.zrank("TuanGOMap", "bb"));
//					System.out.println(jm.jedis.zrevrange("TuanGOMap", 0, -1));
//                     for(ComVO cv:jm.toTuanGoListFromClick()){
//                    	
//                     }
//					
//			
//			   }
}
