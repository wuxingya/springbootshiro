package com.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.*;

/**
 * Created by Administrator on 2017/11/30.
 */
@lombok.Data
@XStreamAlias("ITEM")
public class Item {
    @XStreamAlias("key")
    @XStreamAsAttribute
    private String key;

    @XStreamAlias("val")
    @XStreamAsAttribute
    private String val;

    @XStreamAlias("rmk")
    @XStreamAsAttribute
    private String rmk;
}
