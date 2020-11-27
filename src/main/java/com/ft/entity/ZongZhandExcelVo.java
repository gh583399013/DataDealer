package com.ft.entity;

import lombok.Data;

import java.util.List;

/**
 * @author mask
 * @date 2020/11/27 18:42
 * @desc
 */
@Data
public class ZongZhandExcelVo {
    private List<String> headList;
    private List<ZongZhangVo> dataList;
}
