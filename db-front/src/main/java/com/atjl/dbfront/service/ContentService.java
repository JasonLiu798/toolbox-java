package com.atjl.dbfront.service;



import com.atjl.dbfront.domain.biz.ContentDomain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ContentService {
    void printHtml(String name, String ver, HttpServletRequest request, HttpServletResponse resp);

    void printJs(String name, String ver, HttpServletResponse resp);

    int addOrUpdateContent(String type, String name, String ver, String content);

    ContentDomain getContent(String type, String name, String ver);

    void rollBack(String ver);

    String pubHtml(String content, boolean first);
    //void getJs(String name);
    //void getHtml(String name);
    //void getCss();

}
