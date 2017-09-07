package com.xier.sesame.attence.enums;

public enum SubjectStatus {
	
	USEABLE("可用",1),
	
	DELETE("已删除",2);
	
	private String desc;
	
	private int value;

	SubjectStatus(String desc ,int value){
		this.value = value;
		this.desc =desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public static String getDesc(int value) {
		for (SubjectStatus type : SubjectStatus.values()) {
			if (type.getValue() == value) {
				return type.desc;
			}
		}
		return null;
	}
	
	public static int getValue(String desc) {
		for (SubjectStatus type : SubjectStatus.values()) {
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
