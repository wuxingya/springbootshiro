package com.util;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

/**
 * Created by Administrator on 2017/11/30.
 */
@Data
@XStreamAlias("DATASET")
public class DataSet {
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;
    @XStreamAlias("rmk")
    @XStreamAsAttribute
    private String rmk;

    @XStreamImplicit
    private List<com.util.Data> data;
}
