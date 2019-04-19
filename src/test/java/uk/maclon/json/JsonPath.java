package uk.maclon.json;

import com.jayway.jsonpath.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonPath {
    private static final Logger LOG = LoggerFactory.getLogger(JsonPath.class);

    private String json = "{\n" +
            "    \"store\": {\n" +
            "        \"book\": [\n" +
            "            {\n" +
            "                \"category\": \"reference\",\n" +
            "                \"author\": \"Nigel Rees\",\n" +
            "                \"title\": \"Sayings of the Century\",\n" +
            "                \"price\": 8.95\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"Evelyn Waugh\",\n" +
            "                \"title\": \"Sword of Honour\",\n" +
            "                \"price\": 12.99\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"Herman Melville\",\n" +
            "                \"title\": \"Moby Dick\",\n" +
            "                \"isbn\": \"0-553-21311-3\",\n" +
            "                \"price\": 8.99\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"J. R. R. Tolkien\",\n" +
            "                \"title\": \"The Lord of the Rings\",\n" +
            "                \"isbn\": \"0-395-19395-8\",\n" +
            "                \"price\": 22.99\n" +
            "            }\n" +
            "        ],\n" +
            "        \"bicycle\": {\n" +
            "            \"color\": \"red\",\n" +
            "            \"price\": 19.95\n" +
            "        }\n" +
            "    },\n" +
            "    \"expensive\": 10\n" +
            "}";

    @Test
    public void parse1() {
        List<String> authors = com.jayway.jsonpath.JsonPath.read(json, "$.store.book[*].author");
        LOG.info(authors.toString());
    }

    @Test
    public void parse2() {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String author0 = com.jayway.jsonpath.JsonPath.read(document, "$.store.book[0].author");
        LOG.info(author0);

        String author1 = com.jayway.jsonpath.JsonPath.read(document, "$.store.book[1].author");
        LOG.info(author1);
    }

    @Test
    public void parse3() {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String bicycleColor = com.jayway.jsonpath.JsonPath.read(document, "$.store.bicycle.color");
        LOG.info(bicycleColor);
    }
}
