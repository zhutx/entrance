package com.xier.sesame.attence.web.controller.resp;

import java.util.ArrayList;
import java.util.List;

import com.xier.sesame.attence.web.model.PageData;

public class RecognizeDataWarp extends PageData{
	
	private List<RecognizeData> recognizeList = new ArrayList<>();

	public List<RecognizeData> getRecognizeList() {
		return recognizeList;
	}

	public void setRecognizeList(List<RecognizeData> recognizeList) {
		this.recognizeList = recognizeList;
	}

}
