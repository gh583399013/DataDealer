package com.ft.util;

import com.alibaba.excel.EasyExcel;
import com.ft.entity.TaiZhandExcelVo;
import com.ft.entity.TaiZhangVo;
import com.ft.entity.ZongZhangVo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mask
 * @date 2020/11/27 18:00
 * @desc
 */
public class DataProcessor {

    public List<TaiZhangVo> getAllTaizhangVoList(String fileName){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<TaiZhangVo> list = EasyExcel.read(fileName).head(TaiZhangVo.class).sheet(0).doReadSync();

        TaiZhandExcelVo taiZhandExcelVo = new TaiZhandExcelVo();
        taiZhandExcelVo.setDataList(list);


        return list;
    }

    public List<ZongZhangVo> getAllZongzhangVoList(String fileName){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ZongZhangVo> list = EasyExcel.read(fileName).head(ZongZhangVo.class).sheet(0).doReadSync();
        return list;
    }

    public void dealDataAndWriteToExcel(){
        Map<String, ZongZhangVo> zongZhangVoMap = new HashMap<>(1024000);
        Map<String, TaiZhangVo> taiZhangVoMap = new HashMap<>(1024000);
        List<TaiZhangVo> taiZhangVoList = getAllTaizhangVoList("");
        List<ZongZhangVo> zongZhangVoList = getAllZongzhangVoList("");

        for (TaiZhangVo taiZhangVo : taiZhangVoList) {
            taiZhangVoMap.put(taiZhangVo.getUniqueKey(), taiZhangVo);
        }
        for (ZongZhangVo zongZhangVo : zongZhangVoList) {
            zongZhangVoMap.put(zongZhangVo.getUniqueKey(), zongZhangVo);
        }


    }
}
