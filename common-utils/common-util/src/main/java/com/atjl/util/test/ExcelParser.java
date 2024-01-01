package com.atjl.util.test;

import com.atjl.util.character.StringUtilEx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ExcelUtil parser
 * @author JasonLiu798
 * @date 2015/11/27 9:44
 */
public class ExcelParser {

    /*
    public List<TestCase> getTestCases(ExcelUtil e) throws ClassNotFoundException {
        List<List<String>> tclists = e.getSheet(0);
        List<TestCase> res = new ArrayList<>();

        String testClass = tclists.remove(0).get(1);// class	, UsersFavoritesService
        String testFunction  = tclists.remove(0).get(1);// function, addUserFavorites
        String testParameterClassName = tclists.remove(0).get(1);// parameterclass	,com.xxx.
        List<String> testParameterNames = tclists.remove(0);//[parametername, uid, gid, app, from, gtype, ip, local, site],
        testParameterNames.remove(0);//去掉 parametername 表头
        List<String> testParameterType = tclists.remove(0);//[parameterType, java.lang.Long, ..... ],
        testParameterType.remove(0);//去掉 parameterType 表头

        while(tclists.size()>0){
            String tcname = tclists.remove(0).get(0);//testcase name
            List<List<String>> tclist = this.getOneTestCase(tclists);
            if(tclist!=null){
                TestCase tc = processTestCase(tclist);
                tc.setName(tcname);
                tc.setTestClass(testClass);
                tc.setParameterType(testParameterType);
                tc.setParameterObjectClassName(testParameterClassName);
                tc.setParameterObjectClass(Class.forName(testParameterClassName));
                tc.setTestFunction(testFunction);
                tc.setParameterName(testParameterNames);
                Object parameterObject = this.generateParameter(testParameterClassName,testParameterNames,
                        testParameterType,tc.getParameterValue());
                tc.setParameterObject(parameterObject);
                res.add(tc);
            }else{
                break;
            }
        }
        return res;
    }

    private List<List<String>> getOneTestCase(List<List<String>> source ){
        if(source.size()<3){
            return null;
        }
        List<List<String>> res = new ArrayList<>();
        for(int i=0;i<3;i++){
            res.add(source.remove(0));
        }
        return res;
    }

    /**
     * List<List<String>> 转为 TestCase
     * @param testCase
     * @return
     */
    public TestCase processTestCase(List<List<String>> testCase ){
        TestCase tc = new TestCase();
        List<String> before = testCase.remove(0);//[t1, before, delete from js_users_favorites_1 where uf_uid=168220;]
        before.remove(0);//去掉 before 表头
        tc.setBefore(before);

        List<String> parameter = testCase.remove(0);
        parameter.remove(0);//去掉 parameter 表头
        tc.setParameterValue(parameter);

        String expect = testCase.remove(0).get(1);
        if(expect.equals(TestUtilConstant.EXPECT_SUCCESS_EXCEL)){
            expect = TestUtilConstant.EXPECT_SUCCESS;
        }
        tc.setExpect(expect);
        return tc;
    }

    /**
     * 生成参数dto
     * @param parameterObjectClass
     * @param parameterNames
     * @param parameterTypes
     * @param parameterValue
     * @return
     */
    public Object generateParameter(String parameterObjectClass,List<String> parameterNames,
                                  List<String> parameterTypes,List<String> parameterValue){
        Object res = null;
        try {
            Class<?> parameterObject = Class.forName(parameterObjectClass);

            for(int i =0;i< parameterNames.size();i++){
                String parameterName = parameterNames.get(i);
                String methodname = "set" + StringUtilEx.toUpperCaseFirstOne(parameterName);
                String paramTypeName  = parameterTypes.get(i);
                Class<?> paramTypeClass = Class.forName(paramTypeName);
                Object paramObject = null;
                String paramObjectStr = parameterValue.get(i);
                switch (paramTypeName){
                    case "java.lang.Long":
                        paramObject = Long.parseLong(paramObjectStr);
                        break;
                    case "java.lang.Integer":
                        paramObject  = Integer.parseInt(paramObjectStr);
                        break;
                    case "java.lang.String":
                        paramObject = paramObjectStr;
                        break;
                    default:
                        break;
                }
                Method setterMethod = parameterObject.getMethod(methodname, paramTypeClass);
                res = parameterObject.newInstance();
                setterMethod.invoke(res,paramObject);
            }
            System.out.println("GENERATE "+res);

//            String methodname = "set" + JStringUtil.upperFirstLetter("uid");
//            String type = "java.lang.Long";
//            Class<?> param1 = Class.forName(type);
//            Method m = parameterObject.getMethod(methodname,param1);
//            Object obj = parameterObject.newInstance();
//            m.invoke(obj,parameterValue.get(i));
//            System.out.println();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return res;
    }

}
