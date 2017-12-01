package com.atjl.retry.manager;

import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.eg.Cond;
import com.atjl.retry.eg.TestRetryService;
import com.atjl.util.collection.CollectionUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({DateUtil.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class RetryDispatchManagerTest {


    @Resource
    private PageProcessorManager retryDispatchManager;
    @Resource
    TestRetryService testRetryService;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testProcessItem() throws Exception {
        RetryServiceItem retryServiceItem = new RetryServiceItem();
        retryServiceItem.setRetryService(testRetryService);

        RetryOption opt = new RetryOption();
        opt.setGetDataType(GetDataType.DEFAULT);
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(3L);
        opt.setExceptionInstanceRetry(false);
        opt.setServiceName("aaaa");
        opt.setPageSize(2);

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");
        meta.setOtherConds("SEND_TM > unix_timestamp(now())-3600*24*2 ");
        meta.setDataCols(CollectionUtil.newSet("SEND_TOPIC", "SEND_CONTENT"));

//        meta.setOtherConds("SEND_TM < unix_timestamp(now())-3600*24*2 ");
        opt.setRetryTabMeta(meta);


        retryServiceItem.setPageOption(opt);

        retryDispatchManager.pageProcess(retryServiceItem, new Cond("123"));
    }

    @Test
    public void testIsTimeUp() throws Exception {


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
