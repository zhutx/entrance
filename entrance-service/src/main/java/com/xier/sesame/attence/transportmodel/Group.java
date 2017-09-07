package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

/**
 * Created by xxu on 2017/3/23.
 */
@Data
public class Group {

    @XStreamAsAttribute
    private String groupName;

    @XStreamAsAttribute
    private String groupCode;

    @XStreamAsAttribute
    private Long groupId;


    @XStreamAlias("weekdays")
    private String weekdays;

    @XStreamAlias("begintime")
    private String beginTime;

    @XStreamAlias("endtime")
    private String endTime;
}
