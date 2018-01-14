package com.atjl.fmt.util;


//import org.apache.poi.hpsf.Variant;

public class Html2WordUtil {
    private Html2WordUtil() {
        throw new UnsupportedOperationException();
    }


//    public static void htmlToWord(String html, String wordFile) {
//        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
//        try {
//            app.setProperty("Visible", new Variant(false));
//            Dispatch wordDoc = app.getProperty("Documents").toDispatch();
//            wordDoc = Dispatch.invoke(wordDoc, "Add", Dispatch.Method, new Object[0], new int[1]).toDispatch();
//            Dispatch.invoke(app.getProperty("Selection").toDispatch(), "InsertFile", Dispatch.Method, new Object[] { html, "", new Variant(false), new Variant(false), new Variant(false) }, new int[3]);
//            Dispatch.invoke(wordDoc, "SaveAs", Dispatch.Method, new Object[] {wordFile, new Variant(1)}, new int[1]);
//            Dispatch.call(wordDoc, "Close", new Variant(false));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            app.invoke("Quit", new Variant[] {});
//        }
//    }

}
