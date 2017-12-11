package com.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
@XStreamAlias("MESSAGE")
public class Message {
    @XStreamAlias("DATASET")
    private DataSet dataSet;
}
