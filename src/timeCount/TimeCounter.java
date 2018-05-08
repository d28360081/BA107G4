package timeCount;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.Session;

import com.com.model.ComDaoJdbc;
import com.com.model.ComService;
import com.com.model.ComVO;



public class TimeCounter {
//context創建後,所有尚未成形的團購案件
private ComService comservice;
private static TimeCounter timecounter;
private static HashMap<String,ComVO> VoteMap;//鳩團完成後,將countdownMap 的案件放置此處, 通知使用者
private static HashMap<String,Session> onlineMap;
private static HashMap<String,ComVO> DaiGoCountDownMap;
private static HashMap<String,ComVO> countDownMap;	
	public static HashMap<String, Session> getOnlineMap() {
	return onlineMap;
}

public static void setOnlineMap(HashMap<String, Session> onlineMap) {
	TimeCounter.onlineMap = onlineMap;
}

	public static HashMap<String, ComVO> getVoteMap() {
	return VoteMap;
}

public static void setVoteMap(HashMap<String, ComVO> voteMap) {
	VoteMap = voteMap;
}

	private TimeCounter(){
		countDownMap=new HashMap();
		VoteMap=new HashMap();
		onlineMap=new HashMap();
		comservice=new ComService();
		DaiGoCountDownMap=new HashMap<String,ComVO>();
//		ComDaoJdbc cdj=new ComDaoJdbc();
		
		//先將團購案件都放入countDownMap倒數
		for(ComVO c:comservice.selectAll()){
			countDownMap.put(c.getCom_id(), c);
		}	
		//並把代購案件放入map倒數
		for(ComVO c:comservice.selectDaiGo()) {
			DaiGoCountDownMap.put(c.getCom_id(), c);
		}
		
	}
	
	public static HashMap<String, ComVO> getDaiGoCountDownMap() {
		return DaiGoCountDownMap;
	}

	public static void setDaiGoCountDownMap(HashMap<String, ComVO> daiGoCountDownMap) {
		DaiGoCountDownMap = daiGoCountDownMap;
	}

	public static synchronized TimeCounter getInstance(){
		if(timecounter==null){
			timecounter=new TimeCounter();
			return timecounter;
		}
		return timecounter;
	}
	
	
	public HashMap<String, ComVO> getCountDownMap() {
		return countDownMap;
	}

	public void setCountDownMap(HashMap<String, ComVO> countDownMap) {
		this.countDownMap = countDownMap;
	}

	public ComService getComservice() {
		return comservice;
	}

	public void setComservice(ComService comservice) {
		this.comservice = comservice;
	}

	public static TimeCounter getTimecounter() {
		return timecounter;
	}

	public static void setTimecounter(TimeCounter timecounter) {
		TimeCounter.timecounter = timecounter;
	}



}
