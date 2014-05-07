package zju.ccnt.xptools.util;

import zju.ccnt.xptools.mode.DeviceInfo;
import zju.ccnt.xptools.mode.TouchDataModel;
import zju.ccnt.xptools.sync.DeviceInfoSyncRunnable;
import zju.ccnt.xptools.sync.SyncManager;
import zju.ccnt.xptools.sync.TouchDataSyncRunnable;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {
	SyncManager syncManager;
	private SyncManager.SyncComponent syncComponent;
	Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// �������wifi�Ĵ���رգ���wifi�������޹�
			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
			Log.e("H3c", "wifiState" + wifiState);
			switch (wifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				break;
			//
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
					SharedPreferences check = context.getSharedPreferences(
							SharedData.CHECK_FILE, 0);
					boolean firstUse = check.getBoolean(SharedData.FIRSTUSE,
							true);
					syncManager = SyncManager.getInstance();
					syncComponent = SyncManager.getInstance().find(
							DeviceInfo.class.getName());
					if (firstUse) {
						writeDeviceInfo();
						registerDeviceModel();
					}
					registerModels();
				} else {

				}
			}
		}
	}

	private void writeDeviceInfo() {
		DeviceInfo deviceInfo = new DeviceInfo();
		DeviceInfoUtil util = new DeviceInfoUtil(mContext);
		deviceInfo.setId(util.getDeviceId());
		deviceInfo.setxSize(util.getXSize());
		deviceInfo.setySize(util.getYSize());
		deviceInfo.setCPU_info(util.getCpuInfo());
		deviceInfo.setStorage(util.getStorage());
		deviceInfo.setModel(util.getModel());
		deviceInfo.setSys_Version(util.getSysVersion());
		deviceInfo.setOut_Storage(util.getOut_Storage());
		syncComponent.writeModel(deviceInfo);
	}

	private void registerDeviceModel() {
		DeviceInfoSyncRunnable disr = new DeviceInfoSyncRunnable(
				DeviceInfo.class, mContext);
		syncManager.register(disr);
	}

	/**
	 * Register models that need synchronization.
	 */
	private void registerModels() {
		TouchDataSyncRunnable tdsr = new TouchDataSyncRunnable(
				TouchDataModel.class);
		syncManager.register(tdsr);
	}

}
