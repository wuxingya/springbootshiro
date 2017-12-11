package com.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
@lombok.Data
@XStreamAlias("DATA")
public class Data {

    @XStreamImplicit
    private List<Item> item;
}
