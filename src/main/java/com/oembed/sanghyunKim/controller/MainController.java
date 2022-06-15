package com.oembed.sanghyunKim.controller;

import com.oembed.sanghyunKim.Util.OmebedUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Controller
@RequestMapping("/*")
public class MainController {

    @GetMapping("/home.do")
    public String home(Model model) {

        return "home";
    }

    @GetMapping("oEmbed.do")
    @ResponseBody
    public String oEmbedRequest(@RequestParam("requestUrl") String requestUrl) {

        OmebedUtil util = new OmebedUtil();

        // return을 위해 result 선언.
        String result = "";
        try {
        // requestUrl 형식이 http 프로토콜인지 체크 후 다음로직 진행.
        if (util.httpCheck(requestUrl)) {
            String encode = URLEncoder.encode(requestUrl, StandardCharsets.UTF_8);
            String host = util.hostCheck(requestUrl);
            String oembedUrl = util.createURI(host, encode);

            // httpclients를 생성해 데이터를 받아온다
            CloseableHttpClient hc = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(oembedUrl);

            // httpGet Header에 content type을 지정해주고
            httpGet.addHeader("Content-Type", "application/json");

            // request 요청해서 Response 받는다
            CloseableHttpResponse httpResponse = hc.execute(httpGet);


                result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }



        }catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        System.out.println("result : " +result.toString());
        return result;
    }

}
