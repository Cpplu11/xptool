package ccnt.experience.var;

public final class DataBaseInfo {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // ���ݿ�����
	public static final String DATABASE_URL = "jdbc:mysql://192.168.0.96:3306/experience_computing"; // ����
	public static final String DEVICE_INFO_TABLE = "device_info";// �豸����
	public static final String TOUCH_DATA_TABLE = "touch_data";// ���ݱ���
	public static final String DEVICE_TABLE_FORM = "(id,xSize,ySize,size,storage,CPU_style,GPU_style,model,sys_version,out_storage)";// �豸������
	public static final String TOUCH_TABLE_FORM = "(ID,trace_detail,current_app,current_activity,device_Id)";// ���ݱ�����
	public static final String DB_UNAME = "root"; // ���ݿ��û���
	public static final String DB_PWORD = "12345678"; // ���ݿ��û�����
}
