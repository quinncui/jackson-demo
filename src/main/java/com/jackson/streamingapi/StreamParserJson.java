package com.jackson.streamingapi;

/**
 * Created by frankun on 2016/9/22.
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;

/**
 * Jackson API提供了token对每个Json对象，例如，Json开始符号“{”是token指向的第一个解析的对象，
 * key:value键值对是另一个单独的对象。这个API很强大，但也需要编写大量代码。不推荐使用，平时更多的是使用DataBinding和TreeModel来处理json
 */
public class StreamParserJson {

    public static void main(String[] args) throws JsonParseException,IOException{
        JsonFactory factory = new JsonFactory();
        //从JsonFactory创建JsonParser的实例
        JsonParser parser = factory.createParser(new File("streamingAPI.json"));

        while (!parser.isClosed()){
            //得到一个token，第一次遍历时，token指向json文件中的第一个符号“{”
            JsonToken token = parser.nextToken();
            if (token == null){
                break;
            }
            // 当key是province时，我们进入province，查找population
            if (JsonToken.FIELD_NAME.equals(token) && "provinces".equals(parser.getCurrentName())){
                token = parser.nextToken();
                if (!JsonToken.START_ARRAY.equals(token)){
                    break;
                }
                // 此时，token指向的应该是“{”
                token = parser.nextToken();
                if (!JsonToken.START_OBJECT.equals(token)){
                    break;
                }
                while (true){
                    token = parser.nextToken();
                    if (token == null){
                        break;
                    }
                    if (JsonToken.FIELD_NAME.equals(token) && "population".equals(parser.getCurrentName())){
                        token = parser.nextToken();
                        System.out.println(parser.getCurrentName() + " : " + parser.getIntValue());
                    }
                }
            }
        }
    }
}
