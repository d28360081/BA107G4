package com.pmt.thread;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.pmt.model.PmtService;
import com.pmt.model.PmtVO;
import com.pmt.websocket.PmtEchoServer;

public class PmtThread {
	PmtEchoServer pmtEchoServer = new PmtEchoServer();
	PmtService pmtSvc = new PmtService();
	public PmtThread() {
	}
	
	public void autoPushPMT(String pmt_id, String isOn, Timestamp time){
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				PmtVO pmtVO = pmtSvc.getOnePmt(pmt_id);
				String pmt_sts = pmtVO.getPmt_sts();
				if("online".equals(isOn)){
					pmtSvc.update(pmt_id, "上架");
					pmtEchoServer.broadcast("online");
				}else if("offline".equals(isOn)){
					pmtSvc.update(pmt_id, "下架");
					pmtEchoServer.broadcast("offline");
				}
			}
			
		};
		Timer timer = new Timer();
		timer.schedule(task, time);
		System.out.println("促銷編號:"+pmt_id+"　排程器已啟動");
	}
}
