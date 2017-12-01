package com.atjl.retry.service;

import com.atjl.retry.api.RetryDispatch;
import com.atjl.retry.eg.Cond;
import com.atjl.retry.eg.TestConstant;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class SimpleBatchTest {

    @Resource
    private RetryDispatch retryDispatch;

    @Test
    public void testRegiste() throws Exception {

        retryDispatch.registe(TestConstant.SIMPLE_BATCH_SERVICE);

        List status = retryDispatch.getOptions();
        System.out.println("res:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));

        retryDispatch.executeAll();

        retryDispatch.executeService(TestConstant.SIMPLE_BATCH_SERVICE, new Cond("xxx"));

//        retryDispatch.executeAll();


    }

    @Test
    public void testTimeUp() throws Exception {
        retryDispatch.registe("testRetryService");
//        retryDispatch.registe("testRetryService2");

        List status = retryDispatch.getOptions();
        System.out.println("res:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(status)));

        retryDispatch.executeAll();
    }


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
