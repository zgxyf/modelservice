package com.fengyu.modelservice.util;

import com.fengyu.modelservice.expection.ThrowErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 请求第三方URL
 */
public class RequestDataUtil {
    /**
     * 日子记录.
     */
    public static final Logger LOG = LoggerFactory.getLogger(RequestDataUtil.class);
    public static final Integer SUCCESS_CODE = 200;

    public static RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ThrowErrorHandler());
        return restTemplate;
    }

    /**
     * 返回泛型，需要判空
     * @param url
     * @param params
     * @param header
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T exchange(String url, Map<String, Object> params,
                                 HttpHeaders header, Class<T> tClass) {
    try {
        RestTemplate restTemplate = restTemplate();
        header.set("Content-Type", "application/json");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, header);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, tClass);
        T t = response.getBody();
        int responseStatusCodeValue = response.getStatusCodeValue();
        if (responseStatusCodeValue != SUCCESS_CODE) {
            LOG.error("request failed, http error code: ", responseStatusCodeValue);
        }
        return t;
    } catch (Exception e){
        LOG.error("request failed to get data----------");
    }
        return null;
    }

    /**
     *
     * @param url
     * @param params
     * @param pathMap
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T post(String url, Map<String, Object> params,
                             Map<String, Object> pathMap, Class<T> tClass) {
    try {
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<T> response = restTemplate.postForEntity(url, httpEntity, tClass, pathMap);
        T t = response.getBody();
        int responseStatusCodeValue = response.getStatusCodeValue();
        if (responseStatusCodeValue != SUCCESS_CODE) {
            LOG.error("request failed, http error code: ", responseStatusCodeValue);
        }
        return t;
    } catch (Exception e){
        LOG.error("request failed to get data----------");
    }
        return null;
    }

}
