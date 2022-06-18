package com.oembed.sanghyunKim;

import com.oembed.sanghyunKim.Util.OembedUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanghyunKimApplication {

	OembedUtil util = new OembedUtil();
	public static void main(String[] args) {

		SpringApplication.run(SanghyunKimApplication.class, args);

	}


}
