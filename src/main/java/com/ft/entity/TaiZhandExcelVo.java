package com.ft.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author mask
 * @date 2020/11/27 18:42
 * @desc
 */
@Data
public class TaiZhandExcelVo {
    private List<String> headList;
    private List<Map<Integer, String>> dataList;
}
