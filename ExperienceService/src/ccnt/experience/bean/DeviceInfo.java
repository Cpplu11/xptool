package ccnt.experience.bean;

public class DeviceInfo {
	// ���ݿ��ͷ����
	public static String TABLE = "device_info";// ����
	public static String ID = "id";// �豸ID
	public static String X_SIZE = "xSize";// �ֱ���X����
	public static String Y_SIZE = "ySize";// �ֱ���y����
	public static String SIZE = "size";// �ֱ���
	public static String STORAGE = "storage";// �ڴ�
	public static String CPU_STYLE = "CPU_style";// CPU�ͺ�
	public static String GPU_STYLE = "GPU_style";// GPU�ͺ�
	public static String MODEL = "model";// �ͺ�
	public static String SYS_VERSION = "sys_version";// ϵͳ�汾
	public static String OUT_STORAGE = "out_storage";// Ӳ��

	// ���ݲ���
	private int id;
	private int xSize;
	private int ySIze;
	private int size;
	private int storage;
	private String CPU_style;
	private String GPU_style;
	private String model;
	private String sys_version;
	private int out_storage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySIze() {
		return ySIze;
	}

	public void setySIze(int ySIze) {
		this.ySIze = ySIze;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public String getCPU_style() {
		return CPU_style;
	}

	public void setCPU_style(String cPU_style) {
		CPU_style = cPU_style;
	}

	public String getGPU_style() {
		return GPU_style;
	}

	public void setGPU_style(String gPU_style) {
		GPU_style = gPU_style;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSys_version() {
		return sys_version;
	}

	public void setSys_version(String sys_version) {
		this.sys_version = sys_version;
	}

	public int getOut_storage() {
		return out_storage;
	}

	public void setOut_storage(int out_storage) {
		this.out_storage = out_storage;
	}

}
