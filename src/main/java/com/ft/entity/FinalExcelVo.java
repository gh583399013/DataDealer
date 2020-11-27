package com.ft.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * @author mask
 * @date 2020/11/27 17:40
 * @desc
 */
@Data
public class FinalExcelVo {
    @ExcelProperty(value = "房号", index = 0)
    private String uniqueKey;

    @ExcelProperty(value = "列A", index = 1)
    private String value1;

    @ExcelProperty(value = "列B", index = 2)
    private String value2;

    @ExcelProperty(value = "列C", index = 3)
    private String value3;

    @ExcelProperty(value = "列D", index = 4)
    private String value4;

    @ExcelProperty(value = "列E", index = 5)
    private String value5;

    @ExcelProperty(value = "列F", index = 6)
    private String value6;

    @ExcelProperty(value = "列G", index = 7)
    private String value7;

    @ExcelProperty(value = "列H", index = 8)
    private String value8;

    @ExcelProperty(value = "列I", index = 9)
    private String value9;

    @ExcelProperty(value = "列J", index = 10)
    private String value10;

    @ExcelProperty(value = "列K", index = 11)
    private String value11;

    @ExcelProperty(value = "列L", index = 12)
    private String value12;

    @ExcelProperty(value = "列M", index = 13)
    private String value13;

    @ExcelProperty(value = "列N", index = 14)
    private String value14;

    @ExcelProperty(value = "列O", index = 15)
    private String value15;

    @ExcelProperty(value = "列P", index = 16)
    private String value16;

    @ExcelProperty(value = "列Q", index = 17)
    private String value17;

    @ExcelProperty(value = "列R", index = 18)
    private String value18;

    @ExcelProperty(value = "列S", index = 19)
    private String value19;

    @ExcelProperty(value = "列T", index = 20)
    private String value20;

    @ExcelProperty(value = "列U", index = 21)
    private String value21;

    @ExcelProperty(value = "列V", index = 22)
    private String value22;

    @ExcelProperty(value = "列W", index = 23)
    private String value23;

    @ExcelProperty(value = "列X", index = 24)
    private String value24;

    @ExcelProperty(value = "列Y", index = 25)
    private String value25;

    @ExcelProperty(value = "列Z", index = 26)
    private String value26;

    @ExcelProperty(value = "列AA", index = 27)
    private String value27;

    @ExcelProperty(value = "列AB", index = 28)
    private String value28;

    @ExcelProperty(value = "列AC", index = 29)
    private String value29;

    @ExcelProperty(value = "列AD", index = 30)
    private String value30;

    @ExcelProperty(value = "列AE", index = 31)
    private String value31;

    @ExcelProperty(value = "列AF", index = 32)
    private String value32;

    @ExcelProperty(value = "列AG", index = 33)
    private String value33;

    @ExcelProperty(value = "列AH", index = 34)
    private String value34;

    @ExcelProperty(value = "列AI", index = 35)
    private String value35;

    @ExcelProperty(value = "列AJ", index = 36)
    private String value36;

    @ExcelProperty(value = "列AK", index = 37)
    private String value37;

    @ExcelProperty(value = "列AL", index = 38)
    private String value38;

    @ExcelProperty(value = "列AM", index = 39)
    private String value39;

    @ExcelProperty(value = "列AN", index = 40)
    private String value40;

    @ExcelProperty(value = "列AO", index = 41)
    private String value41;

    @ExcelProperty(value = "列AP", index = 42)
    private String value42;

    @ExcelProperty(value = "列AQ", index = 43)
    private String value43;

    @ExcelProperty(value = "列AR", index = 44)
    private String value44;

    @ExcelProperty(value = "列AS", index = 45)
    private String value45;

    @ExcelProperty(value = "列AT", index = 46)
    private String value46;

    @ExcelProperty(value = "列AU", index = 47)
    private String value47;

    @ExcelProperty(value = "列AV", index = 48)
    private String value48;

    @ExcelProperty(value = "列AW", index = 49)
    private String value49;

    @ExcelProperty(value = "列AX", index = 50)
    private String value50;

    @ExcelProperty(value = "列AY", index = 51)
    private String value51;

    @ExcelProperty(value = "列AZ", index = 52)
    private String value52;

    public static void main(String[] args) {
        String asd = "    @ExcelProperty(index = #2)\n" +
                "    private String value#3;";

        char a = 'A';

        //A=65
        System.out.println(Integer.valueOf(a));
        for (int i=0;i<=51;i++){
            char rowNameChar = (char) (65 + i%26);
            String rowName = String.valueOf(rowNameChar);
            if(i>25){
                rowName = 'A' + rowName;
            }
            rowName = "列" + rowName;
            System.out.println(asd.replaceAll("#1", rowName).replaceAll("#2", String.valueOf(i+1)).replaceAll("#3", String.valueOf(i+1)));
            System.out.println();
        }
    }

}
