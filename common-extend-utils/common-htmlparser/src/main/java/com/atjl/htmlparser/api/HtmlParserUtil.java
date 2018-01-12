package com.atjl.htmlparser.api;


import com.atjl.common.domain.KeyValue;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * html解析类
 */
public class HtmlParserUtil {

    /**
     *
     */
    public static String updateAttr(String html, UpdateElementAttrReq req) {
        if (CollectionUtil.isEmpty(req.getAttrs()) || StringCheckUtil.isEmpty(req.getTag())) {
            return html;
        }

        Document doc = Jsoup.parseBodyFragment(html);
        //Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

//        Element body = doc.body();
//        Element content = doc.getElementById("content");
        Elements elements = doc.getElementsByTag(req.getTag());
        if (CollectionUtil.isEmpty(elements)) {
            return html;
        }

        for (Element element : elements) {
            for (KeyValue attr : req.getAttrs()) {
//                String oldValue = element.attr(attr.getKey());
                element.attr(attr.getKey(), attr.getValue());
            }
//            System.out.println("border " + border);
//            String linkText = link.text();
        }
        return doc.body().html();
    }


    private HtmlParserUtil() {
        throw new UnsupportedOperationException();
    }


}
