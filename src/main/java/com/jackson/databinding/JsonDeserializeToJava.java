package com.jackson.databinding;

/**
 * Created by frankun on 2016/9/21.
 */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 将Json字符串反序列化为Java对象
 */
public class JsonDeserializeToJava {

    public static void main(String[] args) throws Exception{
        //ObjectMapper类用序列化与反序列化映射器
        ObjectMapper mapper = new ObjectMapper();
        File jsonToJava = new File("country.json");
        //当反序列化json时，未知属性会引起反序列化被打断，这里我们禁用未知属性打断反序列化
        //例如，json里有10个属性，而我们的bean中只定义了2个属性，其他8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //从json映射到java对象，得到country对象后就可以遍历查找
        Country country = mapper.readValue(jsonToJava, Country.class);
        System.out.println("country_id:" + country.getCountry_id());
        //设置时间格式，方便阅读
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = dateFormat.format(country.getBirthDate());
        System.out.println("birthDate:" + birthDate);

        List<Province> provinces = country.getProvinces();
        for (Province province : provinces){
            System.out.println("province:" + province.name + "\n" + "population:" + province.population);
        }
    }
}
