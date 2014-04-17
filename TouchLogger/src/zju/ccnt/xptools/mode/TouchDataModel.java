/********************************************************************************
 * Copyright (c) CCNT
 * Version: 1.0
 * Author:zhouqj
 * Modified Time:2014-4-17 
 *******************************************************************************/
package zju.ccnt.xptools.mode;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @ClassName TouchDataModel
 * @Description �켣����
 * @author zhouqj
 * @date date 2014-4-16 ����10:52:39 
 */
public class TouchDataModel {
	public static String TABLE = "touch_data";
	public static String ID = "id";
	public static String DEVICE_ID="device_id";
	public static String TRACE_DETAIL="trace_detail";
	//��ǰapp
	public static String CURRENTAPP = "current_app";
	//��ǰactivity
	public static String CURRENTACTIVITY = "current_activity";
	
	private int id;
	private String current_app;
	private String current_activity;
	private ArrayList<ArrayList<PointData>> trace_detail;
	
	public void TouchDataModel(){
		trace_detail=new ArrayList<ArrayList<PointData>>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurrent_app() {
		return current_app;
	}
	public void setCurrent_app(String current_app) {
		this.current_app = current_app;
	}
	public String getCurrent_activity() {
		return current_activity;
	}
	public void setCurrent_activity(String current_activity) {
		this.current_activity = current_activity;
	}
	
	private ArrayList<ArrayList<PointData>> getTrace_detail() {
		return trace_detail;
	}
	private void setTrace_detail(ArrayList<ArrayList<PointData>> trace_detail) {
		this.trace_detail = trace_detail;
	}


	/**
	 * ÿ�����ص��λ�á��ٶȡ�ѹ������Ϣ
	 */
	public class PointData{
		public Calendar calendar;
		public float x;
		public float y;
		public float velocity;
		public float xVelocity;
		public float yVelocity;
		public float pressure;
		public float extimateX;
		public float extimateY;
	}
}
