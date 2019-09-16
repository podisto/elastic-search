package com.simba.elasticsearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simba.elasticsearch.domain.ProfileDocument;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-09-16
 */
@Service
@Slf4j
public class ProfileService {
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    private static final String INDEX = "lead";
    private static final String TYPE = "lead";

    public ProfileService(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.client = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    public String createProfile(ProfileDocument document) {
        IndexResponse indexResponse = new IndexResponse();
        try {
            UUID uuid = UUID.randomUUID();
            document.setId(uuid.toString());
            Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);
            IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, document.getId())
                    .source(documentMapper);
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("--- exception when creating profile with root cause: {} ---", e.getMessage());
        }
        return indexResponse.getResult().name();
    }

    public ProfileDocument findById(String id) {
        log.info("--- find document with id = {} --- ", id);
        ProfileDocument profileDocument = new ProfileDocument();
        try {
            GetRequest request = new GetRequest(INDEX, TYPE, id);
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            Map<String, Object> resultMap = response.getSource();
            profileDocument = objectMapper.convertValue(resultMap, ProfileDocument.class);
        } catch (IOException e) {
            log.info("--- exception when find document with root cause: {} --- ", e.getMessage());
        }
        return profileDocument;
    }
}
