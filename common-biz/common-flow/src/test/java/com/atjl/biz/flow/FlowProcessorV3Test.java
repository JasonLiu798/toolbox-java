package com.atjl.biz.flow;

import com.atjl.biz.flow.cache.SpanCache;
import com.atjl.biz.flow.api.FlowRequest;
import com.atjl.biz.flow.api.Flow;
import com.atjl.biz.flow.api.FlowManager;
import com.atjl.biz.flow.core.SpanExecutor;
import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.log.api.LogUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

public class FlowProcessorV3Test {
    private static Logger LOG = LoggerFactory.getLogger(FlowProcessorV3Test.class);

    @Test
    public void test1() {
    }

    @Test
    public void testAddFuncFlow() throws Exception {

        //flow1
        Flow f1 = new Flow() {
            @Override
            public void action() {
                FlowRequest req = this.getRequest();
                ResponseDataDto resp = new ResponseDataDto();
                resp.setResult("func resp");
                Object glo = this.getGlobal();
                LogUtil.debug("############ flow 1 action,param {},glo {},will resp {}", req,glo ,resp);
//                this.setResponse(resp);
            }
        };

        //flow2 noparam
        Flow f2 = new Flow() {
            @Override
            public void action() {
                Object glo = this.getGlobal();
                LogUtil.debug("############ flow 2 action,param no,glo {}",glo);
                this.setGlobal("glob2");
                this.setContextData("user","testUser");
                this.setSuccessWithData("flow2 resp");
            }
        };

        Flow f3 = new Flow() {
            @Override
            public void action() {
                Object req = this.getRequestParam();
                String glob = (String) this.getGlobal();
                Object userData = this.getContextData("user");
                LogUtil.debug("############ flow 3 process,req {},glob {},user {},will resp {}", req, glob,userData,"final res");
                this.setSuccessWithData("final res");
            }
        };

        FlowManager flowManager = new FlowManager();
        flowManager.setSequenceCache(new SpanCache());
        flowManager.setSpanExecutor(new SpanExecutor());

        FlowRequest req = new FlowRequest("testReq");
        String glo = "initGlo";

        ResponseDataDto resp = flowManager.execute(req, glo, f1, f2, f3);
        LogUtil.debug("res {}", resp);
        Set<String> ids = flowManager.getSeqs();
        for (String id : ids) {
            String g = flowManager.getGraph(id);
            System.out.println("graph: "+g);
        }
    }

    /*
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
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
