package com.jackson.treemodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;

/**
 * Created by frankun on 2016/9/21.
 */
public class DeserializationTreeModel1 {

    public static void main(String[] args) throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        //jackson提供一个树节点JsonNode，ObjectMapper提供方法读取json作为JsonNode的根节点
        JsonNode node = mapper.readTree(new File("treeModelCountry.json"));
        //看根节点类型
        System.out.println("node JsonNodeType: " + node.getNodeType());
        //是否为容器
        System.out.println("is the node a container? " + node.isContainerNode());
        //得到node节点所有子节点名称
        System.out.println("==========得到node节点所有子节点名称==========");
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()){
            String fieldName = fieldNames.next();
            System.out.print(fieldName + "  ");
        }

        //asText的作用是有值的返回值，无值的返回空字符串
        System.out.println("\n==============================================");

        JsonNode countryId = node.get("country_id");
        System.out.println("country_id: " + countryId.asText() + ", JsonNodeType: " + countryId.getNodeType());

        JsonNode birthDate = node.get("birthDate");
        System.out.println("birthData: " + birthDate.asText() + ", JsonNodeType: " + birthDate.getNodeType());

        JsonNode nations = node.get("nations");
        System.out.println("nations: " + nations + ", JsonNodeType: " + nations.getNodeType());

        JsonNode lakes = node.get("lakes");
        System.out.println("lakes: " + lakes + ", JsonNodeType: " + lakes.getNodeType());

        JsonNode provinces = node.get("provinces");
        System.out.println("provinces: " + provinces + ", JsonNodeType: " + provinces.getNodeType());

        JsonNode traffic = node.get("traffic");
        System.out.println("traffic: " + traffic + ", JsonNodeType: " + traffic.getNodeType());

        //为了避免provinceElements多次打印，用flag控制打印，能体现provinceElements的JsonNodeType就可以了
        boolean flag = true;
        for (JsonNode provinceElement : provinces){

            if (flag) {
                System.out.println("JosnNodeType: " + provinceElement.getNodeType());
                System.out.println("is it a container node? " + provinceElement.isContainerNode());
                flag = false;
            }

            Iterator<String> provinceElementFieldName = provinceElement.fieldNames();
            while (provinceElementFieldName.hasNext()){
                String fieldName = provinceElementFieldName.next();
                String province;
                if ("population".equals(fieldName)){
                    province = fieldName + ": " + provinceElement.get(fieldName).asInt();
                }else {
                    province = fieldName + ": " + provinceElement.get(fieldName).asText();
                }
                System.out.println(province);
            }
        }
    }
}
