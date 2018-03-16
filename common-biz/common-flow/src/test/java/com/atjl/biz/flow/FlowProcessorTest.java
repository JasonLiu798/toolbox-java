package com.atjl.biz.flow;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class FlowProcessorTest {

    @Test
    public void test1() {
    }
/*
    @Test
    public void testAddFuncFlow() throws Exception {
        String[] ids = new String[]{"func1", "func2", "func3"};
        List<String> idl = CollectionHelper.array2List(ids);

        //func1 noparam
        IFunctionNoParam funcNoParam = (IFunctionNoParam<String>) () -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func1's resp");
            System.out.println("func1 action,param no,res " + resp);
            return resp;
        };

        //func2,use func1's resp
        IFunctionParam<String, String> funcParam = param -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func2's resp " + param);
            System.out.println("func2 action,param " + param + ",res " + resp);
            return resp;
        };

        //func3,use global param,use func2's resp
        IFunctionGlobalParam<String, String, String> funcParam2 = (param, globalParam) -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func3's resp ");
            String msg = String.format("func3 action,param %s,global param %s,res %s", param, globalParam, resp);
            System.out.println(msg);
            return resp;
        };
        FlowOptionGen gen = new FlowOptionGen();
        int option = gen.getNormalOption();
        FlowProcessor flowProcessor = new FlowProcessor();//ids);
//        flowProcessor.addSnippet(ids[0], funcNoParam, option);
//        flowProcessor.addSnippet(ids[1], funcParam);
//        flowProcessor.addSnippet(ids[2], funcParam2, option);
        boolean res = flowProcessor.addSnippet(funcNoParam);
        System.out.println("##########add 1 "+res);
        res = flowProcessor.addSnippet(funcParam);
        System.out.println("##########add 2 "+res);
        res = flowProcessor.addSnippet(funcParam2);
        System.out.println("##########add 3 "+res);
        String graph = flowProcessor.getFlowExecuteGraph();
        System.out.println("############## graph ############");
        System.out.println(graph);
        System.out.println("############## start execute ############");
        FlowResponse resp = flowProcessor.process(ids);
        System.out.println("############## final result " + resp);

    }

    @Test
    public void testExceptionBreak() {
        String[] ids = new String[]{"func1", "func2"};
        //func1 noparam
//        FlowFuncNoParam<String> func1 = new FlowFuncNoParam<>(new IFunctionNoParam<String>(){
        IFunctionNoParam funcNoParam = (IFunctionNoParam<String>) () -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func1 resp string");
            System.out.println("func1 action,param no,res " + resp);
            throw new RuntimeException();
            //return resp;
        };
        //func2,use func1's resp
//        FlowFuncParam<String,String> func2 = new FlowFuncParam<>(new IFunctionParam<String,String>(){

        IFunctionParam<String, String> funcParam = param -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func2 resp " + param);
            System.out.println("func2 action,param " + param + ",res " + resp);
            return resp;
        };

        FlowProcessor flowProcessor = new FlowProcessor();//ids);
//        flowProcessor.addSnippet(ids[0], funcNoParam);
//        flowProcessor.addSnippet(ids[1], funcParam);
        flowProcessor.addSnippet(funcNoParam);
        flowProcessor.addSnippet(funcParam);

        String graph = flowProcessor.getFlowExecuteGraph();
        System.out.println(graph);

        FlowResponse resp = flowProcessor.process(ids);
        System.out.println(resp);
    }

    @Test
    public void testForceGoon() {
        String[] ids = new String[]{"func1", "func2"};
        //func1 noparam
//        FlowFuncNoParam<String> func1 = new FlowFuncNoParam<>(new IFunctionNoParam<String>(){
        IFunctionNoParam funcNoParam = (IFunctionNoParam<String>) () -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func1 resp string");
            System.out.println("func1 action,param no,res " + resp);
            throw new RuntimeException();
            //return resp;
        };
        //func2,use func1's resp
//        FlowFuncParam<String,String> func2 = new FlowFuncParam<>(new IFunctionParam<String,String>(){

        IFunctionParam<String, String> funcParam = param -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func2 resp " + param);
            System.out.println("func2 action,param " + param + ",res " + resp);
            return resp;
        };

        FlowProcessor flowProcessor = new FlowProcessor();//ids);
//        flowProcessor.addFlowForceGoon(ids[0], funcNoParam);
//        flowProcessor.addSnippet(ids[1],funcParam );
        flowProcessor.addFlowForceGoon(funcNoParam);
        flowProcessor.addSnippet(funcParam);

        String graph = flowProcessor.getFlowExecuteGraph();
        System.out.println(graph);

        FlowResponse resp = flowProcessor.process(ids);
        System.out.println(resp);
    }

    @Test
    public void testGolbal() {
        String[] ids = new String[]{"func1", "func2"};
        //func1 noparam
//        FlowFuncNoParam<String> func1 = new FlowFuncNoParam<>(new IFunctionNoParam<String>(){
        IFunctionNoParam funcNoParam = (IFunctionNoParam<String>) () -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func1 resp string");
            System.out.println("func1 action,param no,res " + resp);
            return resp;
        };
        //func2,use func1's resp
//        FlowFuncParam<String,String> func2 = new FlowFuncParam<>(new IFunctionParam<String,String>(){

        IFunctionGlobalParam<String, String, String> funcParam = (param, globalParam) -> {
            FlowResponse<String> resp = new FlowResponse<>();
            resp.setData("func2 resp " + param);
            String msg = String.format("func2 action,param %s,res %s,global param %s", param, resp, globalParam);
            System.out.println(msg);
            return resp;
        };

        FlowProcessor flowProcessor = new FlowProcessor();//ids);
//        flowProcessor.addFlowForceGoon(ids[0], funcNoParam);
//        flowProcessor.addSnippet(ids[1],funcParam );
//        flowProcessor.addSnippet(ids[1],funcParam );

        String graph = flowProcessor.getFlowExecuteGraph();
        System.out.println(graph);

        String golbalParam = "global";
        FlowResponse resp = flowProcessor.process(ids, golbalParam);
        System.out.println(resp);

    }

    @Test
    public void testAddProcFlow() throws Exception {

    }

    @Test
    public void testProcess() throws Exception {

    }
*/

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
//        LogContext.enableDev();
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
