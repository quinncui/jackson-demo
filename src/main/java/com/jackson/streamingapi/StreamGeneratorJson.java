package com.jackson.streamingapi;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by frankun on 2016/9/22.
 */
public class StreamGeneratorJson {

    public static void main(String[] args) throws Exception{
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(new FileWriter(new File("streamingAPI.json")));

        generator.writeStartObject();
        generator.writeFieldName("country_id");
        generator.writeString("China");
        generator.writeFieldName("provinces");
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeStringField("name", "Taiwan");
        generator.writeNumberField("population", 23449287);
        generator.writeEndObject();
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
    }
}
