package com.oembed.sanghyunKim.controller;

import com.oembed.sanghyunKim.Util.OmebedUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Controller
@RequestMapping("/*")
public class MainController {

    @GetMapping("/home.do")
    public String home(Model model){

        return "home";
    }

    @GetMapping("oEmbed.do")
    @ResponseBody
    public String oEmbedRequest(@RequestParam("requestUrl") String requestUrl){

        OmebedUtil util = new OmebedUtil();

        // return을 위해 result 선언.
        String result ="";
        String encode = URLEncoder.encode(requestUrl, StandardCharsets.UTF_8);
        String host = util.hostCheck(requestUrl);
//        String oembedUrl = createAddr(host, encode);

        // httpclients를 생성해 데이터를 받아온다
        CloseableHttpClient hc = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(oembedUrl);

        // param Check
        Logger.getLogger("param {}", requestUrl);



        return "";
    }

}
