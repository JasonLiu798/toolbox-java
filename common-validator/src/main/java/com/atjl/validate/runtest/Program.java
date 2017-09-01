package com.atjl.validate.runtest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JianLong
 * @date 2016/3/9 10:53
 */
public class Program {

    private static Logger logger = LoggerFactory.getLogger(Program.class);

    /*
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:applicationContext-validate.xml");
        Helper tgt = (Helper) springContext.getBean("tgt");

        LoadValidate.readConfiguration();
        Map<String, ActionValidate> map = LoadValidate.getValidateMap();
        System.out.println("validatemap");
        for(String key:map.keySet()){
            logger.info("key--------:" + key + ",v:" + map.get(key));
        }
        int i=0;
//        for(int i=0;i<10;i++){
            MobileBindMessage mbm = new MobileBindMessage();
            mbm.setUid(123L);
            mbm.setMobile("1519913354"+i);
            mbm.setIsvest(1);
            mbm.setOldmobile("151665445"+i);
            CrossPlatformClientMsgEvent res = tgt.check(mbm);
            if(res!=null) {
                logger.info("res-------------" + res.getCode() + "," + res.getMessage());
//            }
        }
/*
        long t1 = System.currentTimeMillis();
        CrossPlatformClientMsgEvent result = new CrossPlatformClientMsgEvent(
                1,"aaa"
//                Integer.parseInt(String.valueOf(map.get(ValidateConstants.SYSTEM_CODE_STRING))),
//                String.valueOf(map.get(ValidateConstants.SYSTEM_MESSAGE_STRING))
        );
        logger.debug("cost "+(System.currentTimeMillis()-t1));
*/
//    }
}
