package com.jackson.treemodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by frankun on 2016/9/21.
 */
public class DeserializationTreeModel2 {

    public static void main(String[] args) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File("treeModelCountry.json"));

        JsonNode missingNode = node.path("test");
        if (missingNode.isMissingNode()){
            System.out.println("JsonNodeType : " + missingNode.getNodeType());
        }

        System.out.println("country_id : " + node.path("country_id").asText());

        JsonNode provinces = node.path("provinces");
        for (JsonNode provinceElement : provinces){
            Iterator<String> provinceElementFieldName = provinceElement.fieldNames();
            while (provinceElementFieldName.hasNext()){
                String fieldName = provinceElementFieldName.next();
                String province;
                if ("name".equals(fieldName)){
                    province = fieldName + ":" + provinceElement.path(fieldName).asText();
                }else {
                    province = fieldName + ":" + provinceElement.path(fieldName).asInt();
                }
                System.out.println(province);
            }
        }
    }
}
