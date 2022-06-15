package com.oembed.sanghyunKim.Util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

public class OmebedUtil {

    String classNm = getClass().getName();
    ArrayList<String> hostList;

    //providers.json의 endpoints.url값 hostList setting
    public void providerData() throws IOException {

        hostList = new ArrayList<>();
        ClassPathResource classPathResource = new ClassPathResource("providers.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));

        JSONParser jsonParser = new JSONParser();
        JSONArray urlArray = new JSONArray();

        try {
            Object obj = jsonParser.parse(br);
            JSONArray providerArray = (JSONArray) obj;

            // endpoints.url 의 값 추출하는 과정
            for (Object jsonArr : providerArray) {
                JSONObject hostUrl = (JSONObject) jsonArr;
                String endpoints = hostUrl.get("endpoints").toString();

                urlArray.clear();
                urlArray = (JSONArray) jsonParser.parse(endpoints);

                JSONObject urlData = (JSONObject) urlArray.get(0);
                String url = (String) urlData.get("url");
                hostList.add(url);

            }
            System.out.println(hostList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // url 체크를 위해 Util 생성
    public  String hostCheck(String hostCheck) {

        String procNm = this.classNm + "hostCheck";

        String result = "";

//        Logger.getLogger(classNm, " param : {} " + hostCheck);

        try {

            URL url = new URL(hostCheck);

            // url 에서 host 획득 후 host를 리턴시키기 위해 만듦
            // ex) https://www.youtube.com/watch?v=-FplGfZXqGc
            String[] list = url.getHost().split("\\.");
            // ex) www.youtube.com/watch?v=-FplGfZXqGc 형식일경우 www.youtube.com 을 추출
            if (list.length == 2) {
                result = list[0];
            // ex) https://www.youtube.com/watch?v=-FplGfZXqGc 형식이면 www.youtube.com을 추출
            } else if (list.length == 3) {
                result = list[1];
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return result;
    }

    // url에서 http를 입력되지 않고 넘어왓을때 return
    public boolean httpCheck(String httpCheck){

        String result = "";
//        String httpCheck = "http://www.youtube.com/watch?v=-FplGfZXqGc";
        String[] http = {"http://","https://"};
        if(httpCheck.substring(0, http[0].length()).contains(http[0]) || httpCheck.substring(0, http[1].length()).contains(http[1]) ){
            result = "true";
            System.out.println(result);
            return true;
        }else{
            result = "false";
            System.out.println(result);
            return false;
        }
    }

    //oembed 메소드 실행전 uri create
    public String createURI(String host, String encodingStr) {
        //https://www.youtube.com/oembed?url=https%3A//youtube.com/watch%3Fv%3DM3r2XDceM6A&format=json
        // https://oembed.com/#section 에서 request 날릴땐 위의 포맷형식으로 request를 해야하기 때문에 url 만드는 작업진행

        String oembedUrl = "";
        if(hostList == null){
            try {
                providerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String hostUrl : hostList) {

            if (hostUrl.contains(host)) {
                // http://roomshare.jp/en/oembed.{format} oembed. 가 있을경우 formatting
                if (hostUrl.contains("oembed.")) {
                    if (hostUrl.contains("{format}"))
                        hostUrl.replace("{format}", "json");
                }

                oembedUrl = hostUrl + "?url=" + encodingStr;
            } else {
//                "https://www.socialexplorer.com/services/oembed/" 형식일경우 format설정.
                oembedUrl = hostUrl + "?format=json&url=" + encodingStr;
            }

        }
        System.out.println(oembedUrl);
        return  oembedUrl;

    }







}
