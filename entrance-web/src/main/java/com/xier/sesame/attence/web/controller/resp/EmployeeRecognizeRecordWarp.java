package com.xier.sesame.attence.web.controller.resp;

import com.xier.sesame.attence.web.model.PageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/4/18.
 */
public class EmployeeRecognizeRecordWarp  extends PageData {

    private List<EmployeeRecognizeRecord> recognizeList = new ArrayList<>();

    public List<EmployeeRecognizeRecord> getRecognizeList() {
        return recognizeList;
    }

    public void setRecognizeList(List<EmployeeRecognizeRecord> recognizeList) {
        this.recognizeList = recognizeList;
    }


}
