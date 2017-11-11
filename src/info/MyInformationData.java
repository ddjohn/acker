package info;

import util.MyUtil;

public class MyInformationData {
	static final int ERROR = 1;
	static final int WARNING = 2;
	static final int INFO = 3;
	static final int DEBUG = 4;
	
	String time;
	int type;
	String slogan;

	public MyInformationData(int type,  String slogan) {
		this.time = MyUtil.getTodaysDateAndTime();
		this.type = type;
		this.slogan = slogan;
	}
}
