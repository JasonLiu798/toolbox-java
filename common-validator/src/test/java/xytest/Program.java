package xytest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @author JianLong
 * @date 2016/3/9 10:53
 */
public class Program {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:applicationContext-validate.xml");
        Object tgt =  springContext.getBean("tgt");
        DataContext dc = new DataContext();
        Data d = new Data(1,"haha");
        dc.setValue(d);
//        tgt.setData(dc);
        Class tgtCls = Tgt.class;
        Method pm = tgtCls.getMethod("process");
        Object cachObj = Cache.getObj("tgt");
        pm.invoke(tgt);
        System.out.println("------------------");
        pm.invoke(cachObj);
//        tgt.process();

//        String name = "habcde";
//        System.out.println(name.substring(0, 3));
    }
}
