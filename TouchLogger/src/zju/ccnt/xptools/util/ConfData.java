package zju.ccnt.xptools.util;

public class ConfData {
	public static final Boolean IS_DEBUG_MODE = false;
	public static final String HTTP_URL_HEAD = "http://192.168.0.96:8080/axis2/services/";
	public static final String URL_CONNECTION_TEST=HTTP_URL_HEAD+"DeviceInfoService/testConnection";
	public static final String URL_SAVE_TOUCH_DATA = HTTP_URL_HEAD + "TouchDataService/saveJsonListTouchData";
	public static final String URL_SAVA_DEVICE_INFO = HTTP_URL_HEAD + "DeviceInfoService/saveDeviceInfoJson";
	// �������ݴ洢·��
	public static final String FILE_PATH = "/sdcard/xptools/";
	public static final String FILE_PATH_ = "/sdcard/zjw/data_secondary.txt";

	// SharedPreferences�����ļ�����ר�ű����û����õ�����
	public static final String CHECK_FILE = "Check_file";
	public static final String FIRSTUSE = "FirstUse";// �Ƿ��һ��ʹ��
}
