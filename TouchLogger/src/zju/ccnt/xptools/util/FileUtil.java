package zju.ccnt.xptools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import zju.ccnt.xptools.mode.DeviceInfo;
import zju.ccnt.xptools.mode.TouchDataModel;

/**
 * 
 * @ClassName: FileUtil
 * @Description: ���ļ���ع�����
 * @author Zhouqj
 * @date 2014-4-22 ����2:11:01
 * 
 */
public class FileUtil {
	/*
	 * private Context context; // SD���Ƿ���� private boolean hasSD = false; //
	 * SD����·�� private String SDPATH; // ��ǰ�������·�� private String FILESPATH;
	 * 
	 * public FileUtil(Context context) { this.context = context; this.hasSD =
	 * Environment.getExternalStorageState().equals(
	 * android.os.Environment.MEDIA_MOUNTED); this.SDPATH =
	 * Environment.getExternalStorageDirectory().getPath(); this.FILESPATH =
	 * this.context.getFilesDir().getPath(); }
	 */
	/**
	 * 
	 * �����ļ�
	 * 
	 * @param @param fileName
	 * @param @throws IOException �趨�ļ�
	 * @return File ��������
	 */
	public static File createFile(String fileName) throws IOException {
		// File file = new File(SDPATH + "//" + fileName);
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 
	 * ����ļ�����
	 * 
	 * @param fileName
	 * @return void ��������
	 * @throws
	 */
	public static void clearFile(String fileName) {
		// File file = new File(SDPATH + "//" + fileName);
		File file = new File(fileName);
		if (!file.exists() || file.isDirectory()) {
			return;
		}
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ��׷����ʽ��TouchDataModel����д���ļ�
	 * 
	 * @param fileName
	 * @param content
	 * @return void
	 */
	public static void writeFile(String fileName, TouchDataModel data) {
		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file, true);
			ObjectOutputStream oos = null;
			if (file.length() < 1) {
				oos = new ObjectOutputStream(fos);
			} else {
				oos = new MyObjectOutputStream(fos);
			}
			oos.writeObject(data);
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: writeDeviceInfo
	 * @Description: ��DeviceInfoд���ļ�
	 * @param @param fileName
	 * @param @param data �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	public static void writeDeviceInfo(String fileName, DeviceInfo data) {
		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = null;
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ���ļ��ж�ȡTouchDataModel����
	 * 
	 * @param fileName
	 * @return List<TouchDataModel> ��������
	 * @throws
	 */
	public static List<TouchDataModel> readFile(String fileName) {
		List<TouchDataModel> list = new ArrayList<TouchDataModel>();
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = null;
			while (fis.available() > 0) {
				object = ois.readObject();
				if (object instanceof TouchDataModel) {
					list.add((TouchDataModel) object);
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: readDeviceInfo 
	 * @Description: ���ļ��ж�ȡDeviceInfo����
	 * @param @param fileName
	 * @param @return    �趨�ļ� 
	 * @return DeviceInfo    �������� 
	 * @throws
	 */
	public static DeviceInfo readDeviceInfo(String fileName) {
		DeviceInfo deviceInfo = new DeviceInfo();
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object object = null;
			while (fis.available() > 0) {
				object = ois.readObject();
				if (object instanceof DeviceInfo) {
					deviceInfo = (DeviceInfo) object;
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return deviceInfo;
	}

}
