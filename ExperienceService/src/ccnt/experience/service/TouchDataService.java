package ccnt.experience.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ccnt.experience.bean.TouchDataModel;
import ccnt.experience.bean.TouchDataModel.PointData;

import ccnt.experience.var.DataBaseInfo;

public class TouchDataService {
	private Connection cnnConnection = null;

	// public static void main(String[] args) throws SQLException,
	// ClassNotFoundException {
	// TouchDataModel touchDataModel = new TouchDataModel();
	// touchDataModel.setId(1);
	// touchDataModel.setCurrent_activity("current_activity");
	// touchDataModel.setCurrent_app("current_app");
	// touchDataModel.setDevice_id(2);
	// saveTouchData(touchDataModel);
	// }

	/*
	 * ��ȡ���ݿ�����
	 */
	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DataBaseInfo.JDBC_DRIVER);
		cnnConnection = DriverManager.getConnection(DataBaseInfo.DATABASE_URL,
				DataBaseInfo.DB_UNAME, DataBaseInfo.DB_PWORD);
	}

	/*
	 * ����WebService��ͨ��
	 */
	public String testConnection() {
		return "Hello,word!";
	}

	/*
	 * ��������Ϣ�е�ArrayList<ArrayList<PointData>>���ո�ʽתΪString
	 * 
	 * @para:������Ϣ��Ӧ��trace_detail
	 */
	private String pointDataToString(
			ArrayList<ArrayList<PointData>> trace_detail) {
		StringBuffer resBuffer = new StringBuffer();
		// ���resBuffer
		resBuffer.setLength(0);
		int fingerCounts = trace_detail.size();
		resBuffer.append(fingerCounts);
		for (int i = 0; i < fingerCounts; i++) {
			resBuffer.append(trace_detail.get(i).size());
		}
		for (int i = 0; i < fingerCounts; i++) {
			for (int j = 0; j < trace_detail.get(i).size(); j++) {
				PointData pointData = trace_detail.get(i).get(j);
				resBuffer.append(pointData.calendar);
				resBuffer.append(pointData.x);
				resBuffer.append(pointData.y);
				resBuffer.append(pointData.velocity);
				resBuffer.append(pointData.xVelocity);
				resBuffer.append(pointData.yVelocity);
				resBuffer.append(pointData.pressure);
				resBuffer.append(pointData.extimateX);
				resBuffer.append(pointData.extimateY);
			}
		}
		return resBuffer.toString();
	}

	/*
	 * ��������Ϣ���붯����Ϣ��
	 * 
	 * ���豸��Ϣ�����豸��
	 */
	public int saveTouchData(TouchDataModel touchDataModel)
			throws SQLException, ClassNotFoundException {
		getConnection();

		String trace_detail = pointDataToString(touchDataModel
				.getTrace_detail());
		String insertTouch = "insert into " + DataBaseInfo.TOUCH_DATA_TABLE
				+ " " + DataBaseInfo.TOUCH_TABLE_FORM + " values ("
				+ touchDataModel.getId() + ",'" + trace_detail + "','"
				+ touchDataModel.getCurrent_app() + "','"
				+ touchDataModel.getCurrent_activity() + "',"
				+ touchDataModel.getDevice_id() + ",'"
				+ touchDataModel.getCurrent_package() + "')";
		Statement statement = cnnConnection.createStatement();
		int ret = statement.executeUpdate(insertTouch);
		System.out.println(insertTouch);
		cnnConnection.close();
		return ret;
	}

	/*
	 * �洢һϵ�еĶ�����������Ϣ��
	 * 
	 * @para:��������
	 */
	public int saveListTouchData(List<TouchDataModel> touchDataModelList) {
		int ret = 0;
		try {
			ret = 1;
			for (TouchDataModel touchDataModel : touchDataModelList) {
				if (saveTouchData(touchDataModel) == 0)
					ret = 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/*
	 * �洢һϵ�еĶ�����������Ϣ��
	 * 
	 * @para:һ���ж�����JSON��ʽ��String
	 */
	public int saveJsonTouchData(String touchDatalList)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<TouchDataModel> touchDataModelList = (List<TouchDataModel>) mapper
				.readValue(
						touchDatalList,
						mapper.getTypeFactory().constructParametricType(
								ArrayList.class, TouchDataModel.class));
		int ret = saveListTouchData(touchDataModelList);
		return ret;
	}
}
