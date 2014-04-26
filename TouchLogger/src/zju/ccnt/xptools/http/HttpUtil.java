package zju.ccnt.xptools.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
/**
 * 
 * @ClassName: HttpUtil 
 * @Description: Http���󹤾���
 * @author Zhouqj 
 * @date 2014-4-24 ����2:38:05 
 *
 */
public class HttpUtil {
	private static AsyncHttpClient client = new AsyncHttpClient(); // ʵ��������
	static {
		client.setTimeout(10000); // �������ӳ�ʱ����������ã�Ĭ��Ϊ10s
	}

	// ʹ��url��ȡString����
	public static void get(String url, AsyncHttpResponseHandler res) {
		client.get(url, res);
	}

	// url���������
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.get(url, params, res);
	}

	// ������������ȡjson�����������
	public static void get(String url, JsonHttpResponseHandler res) {
		client.get(url, res);
	}

	// ����������ȡjson�����������
	public static void get(String url, RequestParams params,
			JsonHttpResponseHandler res) {
		client.get(url, params, res);
	}

	// ��������ʹ�ã��᷵��byte����
	public static void get(String url, BinaryHttpResponseHandler res) {
		client.get(url, res);
	}

	// �ϴ�
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.post(url, params, res);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}
}
