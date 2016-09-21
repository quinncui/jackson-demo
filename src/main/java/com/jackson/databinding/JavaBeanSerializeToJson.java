package com.jackson.databinding;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Map;

/**
 * Created by frankun on 2016/9/20.
 */
public class JavaBeanSerializeToJson {

    public static void convert() throws Exception {
        //用ObjectMapper来转化对象为Json
        ObjectMapper mapper = new ObjectMapper();
        //添加功能，让时间格式更具可读性
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);

        Country country = new Country("China");
        country.setBirthDate(dateFormat.parse("1949-10-01"));
        country.setLakes(new String[] { "Qinghai Lake", "Poyang Lake", "DongTing Lake", "Taihu Lake" });

        List<String> nation = new ArrayList<String>();
        nation.add("Han");
        nation.add("Meng");
        nation.add("Hui");
        nation.add("WeiWuEr");
        nation.add("Zang");
        country.setNation(nation);

        Province province1 = new Province();
        province1.name = "Taiwan";
        province1.population = 23449287;

        Province province2 = new Province();
        province2.name = "Zhejiang";
        province2.population = 55080000;

        List<Province> provinces = new ArrayList<Province>();
        provinces.add(province1);
        provinces.add(province2);
        country.setProvinces(provinces);

        country.addTraffic("Train(KM)", 112000);
        country.addTraffic("HighWay(KM)", 4240000);

        //如下代码只是增加可读性，生产中并不需要，因为会增加Json内容
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //配置mapper忽略空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //默认情况，Jackson使用Java属性字段名称作为Json属性名称，也可以使用Jackson annotations改变Json属性名称
        mapper.writeValue(new File("dataBindingCountry.json"), country);
    }

    public static void main(String[] args) throws Exception{
        convert();
    }
}
