package zju.ccnt.xptools;

import zju.ccnt.xptools.http.HttpUtil;
import zju.ccnt.xptools.http.TestConResponseHandler;
import zju.ccnt.xptools.receiver.NetworkStateReceiver;
import zju.ccnt.xptools.util.ConfData;
import zju.ccnt.xptools.util.FileUtil;
import zju.ccnt.xptools.util.NetWorkStateUtil;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * The Application class which saves some whole APP variables.
 * 
 * @author zhongjinwen
 * 
 */
public class TouchDataApplication extends Application {
	Context mContext;
	/*SyncManager syncManager;
	private SyncManager.SyncComponent syncComponent;*/
	
	public TouchDataApplication getInstance(){
		return (TouchDataApplication)mContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		FileUtil.createDir(ConfData.FILE_PATH);
		BroadcastReceiver receiver = new NetworkStateReceiver();
		// ע��BroadCastReciver,���ü�����Ƶ��������filter�е�
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
		if (NetWorkStateUtil.checkNetWorkState(mContext) == NetWorkStateUtil.WIFI) {
			/*
			 * if (firstUse) { writeDeviceInfo(); registerDeviceModel(); }
			 * registerModels();
			 */
			Log.e("NetWorkState", "Wifi connected");
//			testConnection();
		}
		Log.e("NetWorkState", "no wifi");
		Log.d("TouchLogger", "app created");
		
	}
	
	/**
	 * 
	 * @Title: testConnection 
	 * @Description: �������������ͨ�� 
	 * @return void    �������� 
	 * @throws
	 */
	private void testConnection() {
		String url = ConfData.URL_CONNECTION_TEST;
		HttpUtil.get(url, new TestConResponseHandler(mContext));
	}
}
