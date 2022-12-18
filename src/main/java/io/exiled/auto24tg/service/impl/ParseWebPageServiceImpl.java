package io.exiled.auto24tg.service.impl;

import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class ParseWebPageServiceImpl {
    private final String url = "https://https://www.auto24.ee/kasutatud/nimekiri.php?b=12&ae=8&bw=154&f2=2011&f1=2005&ssid=77114461";

    @PostConstruct
    public void init() {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome/51.0.2704.103")
                    .timeout(5000)
                    .get();
            System.err.println(doc.body());
            System.err.println(doc);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Exception is thrown");
        }
        //https://stackoverflow.com/questions/19964677/how-to-download-a-web-page-source-using-selenium
        //This will prly solve the situation
    }
}
