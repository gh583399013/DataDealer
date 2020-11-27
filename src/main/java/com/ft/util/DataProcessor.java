package com.ft.util;

import com.alibaba.excel.EasyExcel;
import com.ft.entity.TaiZhandExcelVo;
import com.ft.entity.ZongZhandExcelVo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mask
 * @date 2020/11/27 18:00
 * @desc
 */
public class DataProcessor {

    private String finalFilePath;
    private String taiZhangFilePath = null;
    private String zongZhangFilePath = null;

    public String getFinalFilePath() {
        return finalFilePath;
    }

    public void setFinalFilePath(String finalFilePath) {
        this.finalFilePath = finalFilePath;
    }

    public String getTaiZhangFilePath() {
        return taiZhangFilePath;
    }

    public void setTaiZhangFilePath(String taiZhangFilePath) {
        this.taiZhangFilePath = taiZhangFilePath;
    }

    public String getZongZhangFilePath() {
        return zongZhangFilePath;
    }

    public void setZongZhangFilePath(String zongZhangFilePath) {
        this.zongZhangFilePath = zongZhangFilePath;
    }

    private TaiZhandExcelVo taiZhandExcelVo = null;
    private ZongZhandExcelVo zongZhandExcelVo = null;

    public void dealDataAndWriteToExcel(){
        EasyExcel.read(taiZhangFilePath, new TaiZhangDataListener(this)).sheet().doRead();
        EasyExcel.read(zongZhangFilePath, new ZongZhangDataListener(this)).sheet().doRead();


        if(taiZhandExcelVo == null || taiZhandExcelVo.getDataList() == null || taiZhandExcelVo.getDataList().size() == 0 ||
                zongZhandExcelVo == null || zongZhandExcelVo.getDataList() == null || zongZhandExcelVo.getDataList().size() == 0){
            return ;
        }
        Map<String, Map<Integer, String>> zongZhangVoMap = new HashMap<>(1024000);
        Map<String, Map<Integer, String>> taiZhangVoMap = new HashMap<>(1024000);

        for (Map<Integer, String> tmp : taiZhandExcelVo.getDataList()) {
            taiZhangVoMap.put(tmp.get(0), tmp);
        }
        for (Map<Integer, String> tmp : zongZhandExcelVo.getDataList()) {
            zongZhangVoMap.put(tmp.get(0), tmp);
        }
        
        List<List<String>> finalList = new ArrayList<>(taiZhandExcelVo.getDataList().size());
        int taiZhangRowNum = taiZhandExcelVo.getHeadList().size();
        int zongZhangRowNum = zongZhandExcelVo.getHeadList().size();
        for (Map<Integer, String> tmp : taiZhandExcelVo.getDataList()) {
            Map<Integer, String> resultMap = tmp;
            //如果总账中含有台账中的编码
            String uniqueKey = resultMap.get(0);
            if(zongZhangVoMap.containsKey(uniqueKey)) {
                for (int i = 1; i <= zongZhangRowNum; i++) {
                    Integer newRowIndex = taiZhangRowNum + i - 1;
                    resultMap.put(newRowIndex, zongZhangVoMap.get(uniqueKey).get(i));
                }
            } else {
                Integer newRowIndex = taiZhangRowNum + zongZhangRowNum - 1;
                resultMap.put(newRowIndex, "该记录在总账中不存在");
            }
            finalList.add(sortValueMap(resultMap));
        }
        String name = "处理结果_" + new SimpleDateFormat("YYYY_MM_dd").format(new Date());
        String fileName = finalFilePath + name + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName)
                .head(initHeadList(taiZhandExcelVo.getHeadList(), zongZhandExcelVo.getHeadList()))
                .sheet(name)
                .doWrite(finalList);
    }

    private List<String> sortValueMap(Map<Integer, String> valueMap) {
        List<Integer> sortedHeadList = new ArrayList<>(valueMap.keySet());
        Collections.sort(sortedHeadList);
        List<String> sortedValueList = new ArrayList<>();
        for (Integer integer : sortedHeadList) {
            sortedValueList.add(valueMap.get(integer));
        }
        return sortedValueList;
    }

    private List<List<String>> initHeadList(List<String> taiZhangHeadList, List<String> zongZhangHeadList) {
        List<List<String>> finalHeadList = new ArrayList<>();
        for (String headName : taiZhangHeadList) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(headName);
            finalHeadList.add(tmp);
        }
        for (int i = 1; i < zongZhangHeadList.size(); i++) {
            String headName = zongZhangHeadList.get(i);
            List<String> tmp = new ArrayList<String>();
            tmp.add(headName);
            finalHeadList.add(tmp);
        }
        return finalHeadList;
    }

//    public static void main(String[] args) {
//        DataProcessor dataProcessor = new DataProcessor();
//        try {
//            String taiZhang = "C:\\\\Users\\\\mask\\\\Desktop\\\\新建文件夹\\\\台账模板11.27.xlsx";
//            String zongZhang = "C:\\\\Users\\\\mask\\\\Desktop\\\\新建文件夹\\\\总账模板11.27.xlsx";
//            dataProcessor.dealDataAndWriteToExcel();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void saveTaiZhangData(Map<Integer, String> realHeadMap, List<Map<Integer, String>> list) {
        taiZhandExcelVo = new TaiZhandExcelVo();
        List<Integer> sortedHeadList = new ArrayList<>(realHeadMap.keySet());
        Collections.sort(sortedHeadList);
        List<String> headNameList = new ArrayList<>();
        for (Integer integer : sortedHeadList) {
            headNameList.add(realHeadMap.get(integer));
        }
        taiZhandExcelVo.setHeadList(headNameList);
        taiZhandExcelVo.setDataList(list);
    }

    public void saveZongZhangData(Map<Integer, String> realHeadMap, List<Map<Integer, String>> list) {
        zongZhandExcelVo = new ZongZhandExcelVo();
        List<Integer> sortedHeadList = new ArrayList<>(realHeadMap.keySet());
        Collections.sort(sortedHeadList);
        List<String> headNameList = new ArrayList<>();
        for (Integer integer : sortedHeadList) {
            headNameList.add(realHeadMap.get(integer));
        }
        zongZhandExcelVo.setHeadList(headNameList);
        zongZhandExcelVo.setDataList(list);
    }
}
