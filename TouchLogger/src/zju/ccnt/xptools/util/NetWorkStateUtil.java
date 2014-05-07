package zju.ccnt.xptools.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkStateUtil {
	public static final int DISCONNECT = -1;
	public static final int GPRS = 0;
	public static final int WIFI = 1;

	/**
	 * �����������״̬
	 * 
	 * @param context
	 * @return ��������״̬�������֣�δ���ӡ�GRPS/3G��WIFI
	 */
	public static int checkNetWorkState(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return DISCONNECT;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			return GPRS;
		}
		if (nType == ConnectivityManager.TYPE_WIFI) {
			return WIFI;
		}
		return DISCONNECT;
	}
}
