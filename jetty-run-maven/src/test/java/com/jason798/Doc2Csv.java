package com.jason798;



import com.atjl.util.character.RegexUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.file.FileUtil;

import java.util.List;

public class Doc2Csv {
    public static void main(String[] args) {
//        String s = "|CONF_INTERVAL_TM|bigint(20)|间隔时间，单位毫秒|";
//        String[] res = s.split("\\|");
//        System.out.println("res "+ Arrays.toString(res));


        String filepath = "D:\\pdb.md";
        List<String> lines = FileUtil.cat2list(filepath,1);
        //System.out.println(lines);

        String tabName = "";
        String tabCnName = "";
        int i = 1;

        for(String l:lines){
            String head2 = l.substring(0,2);
            if(StringCheckUtil.equalExist(head2,"<a","|字","|:") ){
//                System.out.println("passed "+l);
                continue;
            }
            if(StringCheckUtil.equal("* ",head2)){
                int idx = l.indexOf("表");
                int lidx = l.indexOf(")");
                tabName = l.substring(1,idx);
                tabCnName = l.substring(idx+2,lidx);
//                System.out.println("tab "+tabName+",cn "+tabCnName);
            }else{
                //other column
                String[] columns = l.split("\\|");
                //序号,表名,表名注释,字段名,数据类型,长度	默认值	主键?	非空?	unsigned	自增?	zerofill?	注释
                String name = columns[1];
                String types = columns[2];
                String memo = "";
                if(columns.length>=4) {
                    memo = columns[3];
                }

                String len = RegexUtil.getDigits(types);
                if(StringCheckUtil.isEmpty(len)){
                    len = "-";
                }
                String isPk = "否";
                String isNull = "否";
                if( columns[2].equals("主键")){
                    isPk = "是";
                    isNull = "是";
                }

                String s = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        i,tabName,tabCnName,
                        name,types,len,
                        "-",isPk,isNull,
                        "-","否","否",
                        memo);
                System.out.println(s);
                i++;
            }

        }
    }
}
