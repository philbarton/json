package uk.maclon.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.jayway.jsonpath.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonPatchTest {
    private static final Logger LOG = LoggerFactory.getLogger(JsonPatchTest.class);

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
    public void patch1() {

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String bicycleColor = com.jayway.jsonpath.JsonPath.read(document, "$.store.bicycle.color");

        ObjectMapper mapper = new ObjectMapper();
        try {

            String patchStr = "[{ \"op\": \"add\", \"path\": \"/partitionId\", \"value\": \""+ bicycleColor + "\" }]";
            JsonPatch patch = JsonPatch.fromJson(mapper.readTree(patchStr));

            JsonNode orig = mapper.readTree(json);
            JsonNode patched = patch.apply(orig);

            LOG.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patched));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonPatchException e) {
            e.printStackTrace();
        }
    }
}