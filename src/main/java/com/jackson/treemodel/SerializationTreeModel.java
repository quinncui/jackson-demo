package com.jackson.treemodel;

/**
 * Created by frankun on 2016/9/21.
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileWriter;

/**
 * tree model方式生成json
 */
public class SerializationTreeModel {

    public static void main(String[] args) throws Exception{
        //创建一个节点工厂，为我们创建所有节点
        JsonNodeFactory factory = new JsonNodeFactory(false);
        //创建一个json工厂，来写tree model为json
        JsonFactory jsonFactory = new JsonFactory();
        //创建一个json生成器
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(new FileWriter(new File("treeModelCountry.json")));
        //默认情况下对象映射器不会指定根节点，下面指定country为根节点
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode country = factory.objectNode();

        country.put("country_id", "China");
        country.put("birthDate", "1949-10-01");

        //在Java中，List和Array转化为json后对应的格式符号都是"obj:[]"
        ArrayNode nations = factory.arrayNode();
        nations.add("Han").add("Qiang").add("Hui").add("Miao").add("Tujia").add("Zang");
        country.set("nations", nations);

        ArrayNode lakes = factory.arrayNode();
        lakes.add("Qinghai Lake").add("Poyang Lake").add("DongTing Lake").add("Taihu Lake");
        country.set("lakes", lakes);

        ArrayNode provinces = factory.arrayNode();
        ObjectNode province1 = factory.objectNode();
        ObjectNode province2 = factory.objectNode();
        province1.put("name", "Taiwan");
        province1.put("population", 23449287);
        province2.put("name", "Zhejiang");
        province2.put("population", 55080000);
        provinces.add(province1).add(province2);
        country.set("provinces", provinces);

        ObjectNode traffic = factory.objectNode();
        traffic.put("HighWay(KM)", 4240000);
        traffic.put("Train（KM）", 112000);
        country.set("traffic", traffic);

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeTree(jsonGenerator, country);
    }
}
