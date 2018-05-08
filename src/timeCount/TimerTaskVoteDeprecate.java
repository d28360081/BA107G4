package timeCount;

import java.util.List;
import java.util.TimerTask;

import com.com.model.ComService;
import com.com.model.ComVO;
import com.compant.model.ComPantLsService;
import com.compant.model.ComPantLsVO;

public class TimerTaskVoteDeprecate extends TimerTask{
private String com_id;
private ComPantLsService compantlsservice=new ComPantLsService();
private ComService comservice=new ComService();
	public TimerTaskVoteDeprecate(){
		
	}
	public TimerTaskVoteDeprecate(String com_id){
		this.com_id=com_id;
	}
	
	
	@Override
	public void run() throws RuntimeException{
		try{
	    ComVO cv=new TimeCountTool().getTimecounter().getDaiGoCountDownMap().get(this.com_id);
	    List<ComPantLsVO> list=compantlsservice.selectToVote(com_id);
	    for(ComPantLsVO cplv:list){
	    	cplv.setMem_sts("未投票");
	    	compantlsservice.update(cplv);
	    	
	    }
	    cv.setCom_sts("投票結束");
	    
	    comservice.update(cv);
	    new TimeCountTool().getTimecounter().getCountDownMap().remove(cv.getCom_id());
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
