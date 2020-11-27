package com.ft.entity;

import java.util.List;
import java.util.Map;

/**
 * @author mask
 * @date 2020/11/27 18:42
 * @desc
 */
//@Data
public class TaiZhandExcelVo {
    private List<String> headList;
    private List<Map<Integer, String>> dataList;

    public List<String> getHeadList() {
        return headList;
    }

    public void setHeadList(List<String> headList) {
        this.headList = headList;
    }

    public List<Map<Integer, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<Integer, String>> dataList) {
        this.dataList = dataList;
    }
}
