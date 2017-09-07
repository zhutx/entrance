package com.xier.sesame.attence.enums;

public enum RecognizeUserType {
	
	MEMBER("成员",1),
	
	VISITOR("访客",2),
	
	STANGER("陌生人", 3);
	
	private String desc;
	
	private int value;

	RecognizeUserType(String desc ,int value){
		this.value = value;
		this.desc =desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public static String getDesc(int value) {
		for (RecognizeUserType type : RecognizeUserType.values()) {
			if (type.getValue() == value) {
				return type.desc;
			}
		}
		return null;
	}
	
	public static int getValue(String desc) {
		for (RecognizeUserType type : RecognizeUserType.values()) {
			if (type.getDesc().equals(desc)) {
				return type.value;
			}
		}
		return 0;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
