package zju.ccnt.xptools.util;

import zju.ccnt.xptools.http.HttpUtil;
import zju.ccnt.xptools.http.TestConResponseHandler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {
	Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		// �������wifi�Ĵ���رգ���wifi�������޹�
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
			Log.e("H3c", "wifiState" + wifiState);
			switch (wifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				break;
			}
		}
		// �������wifi������״̬���Ƿ�������һ����Ч����·�ɣ����ϱ߹㲥��״̬��WifiManager.WIFI_STATE_DISABLING����WIFI_STATE_DISABLED��ʱ�򣬸�������ӵ�����㲥��
		// ���ϱ߹㲥�ӵ��㲥��WifiManager.WIFI_STATE_ENABLED״̬��ͬʱҲ��ӵ�����㲥����Ȼ�մ�wifi�϶���û�����ӵ���Ч������
		if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
			Parcelable parcelableExtra = intent
					.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (null != parcelableExtra) {
				NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
				State state = networkInfo.getState();
				boolean isConnected = state == State.CONNECTED;// ��Ȼ����߿��Ը���ȷ��ȷ��״̬
				Log.e("H3c", "isConnected" + isConnected);
				if (isConnected) {
					testConnection();
				} else {

				}
			}
		}
	}

	private void testConnection() {
		HttpUtil.get(ConfData.URL_CONNECTION_TEST, new TestConResponseHandler(
				mContext));
	}
}
