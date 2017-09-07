package com.xier.sesame.attence.web.controller.resp;

import com.xier.sesame.attence.web.model.PageData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/5/5.
 */

@Data
public class AttenceRecognizeRecordWarp extends PageData {

    private List<AttenceRecognizeRecord> recognizeList = new ArrayList<>();
}
