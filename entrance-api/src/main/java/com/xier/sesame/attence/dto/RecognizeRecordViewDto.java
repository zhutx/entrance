package com.xier.sesame.attence.dto;

public class RecognizeRecordViewDto extends RecognizeRecordDto {
	
	private static final long serialVersionUID = 9187572867277406617L;
	
	private Long searchBeginDate;
	private Long searchEndDate;
	
	public Long getSearchBeginDate() {
		return searchBeginDate;
	}
	public void setSearchBeginDate(Long searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}
	public Long getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(Long searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

}
