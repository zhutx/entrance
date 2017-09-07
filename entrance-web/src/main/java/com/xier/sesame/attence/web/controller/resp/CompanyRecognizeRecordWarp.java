package com.xier.sesame.attence.web.controller.resp;

import com.xier.sesame.attence.web.model.PageData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/4/18.
 */
@Data
public class CompanyRecognizeRecordWarp extends PageData {

    private List<CompanyRecognizeRecord> recognizeList = new ArrayList<>();

}
