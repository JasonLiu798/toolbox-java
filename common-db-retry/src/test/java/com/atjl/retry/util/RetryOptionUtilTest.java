package com.atjl.retry.util;

import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.IntervalCoefficientOption;
import com.atjl.retry.api.option.IntervalCoeffientType;
import com.atjl.util.common.DateUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DateUtil.class})
public class RetryOptionUtilTest {

    /**
     * 固定间隔
     *
     * @throws Exception
     */
    @Test
    public void testIsTimeUpFix() throws Exception {
        PowerMockito.mockStatic(DateUtil.class);
        when(DateUtil.getNowTS()).thenReturn(5000L);

        RetryOption opt = new RetryOption();
        RetryData d = new RetryData();
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        d.setLastRetriedTs(1000L);
        /**
         * 1000+ 10* 5* 60  = 1000+3000 = 4000
         *
         */
        assertEquals(true, OptionUtil.isTimeUp(opt, d));


        when(DateUtil.getNowTS()).thenReturn(3000L);

//        retryDispatchManager = new RetryDispatchManager();
        opt = new RetryOption();
        d = new RetryData();
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        d.setLastRetriedTs(1000L);
        /**
         * 1000+ 10* 5* 60  = 1000+3000 = 4000
         *
         */
        assertEquals(false, OptionUtil.isTimeUp(opt, d));
    }


    /**
     * 可变间隔
     *
     * @throws Exception
     */
    @Test
    public void testIsTimeUpVariable() throws Exception {
        PowerMockito.mockStatic(DateUtil.class);

        /**
         * now 时间 变动
         */
        for (long t = 1550; t < 1650; t++) {
            when(DateUtil.getNowTS()).thenReturn(t);

            RetryOption opt = new RetryOption();
            RetryData d = new RetryData();
            IntervalCoefficientOption copt = opt.getIntervalCoefficientOption();
            copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE);
            copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});
//        copt.setIntervalCoefficient(10);
            d.setLastRetriedTs(1000L);
            d.setRetriedCnt(2L);
            /**
             * 1000+ 2* (5* 60)  = 1000+600 = 1600
             * now 1601
             */
            System.out.println("t:" + t);
            if (t > 1600) {

                assertEquals(true, OptionUtil.isTimeUp(opt, d));
            } else {

                assertEquals(false, OptionUtil.isTimeUp(opt, d));
            }
        }


        /**
         * 重试次数递增，当前时间不变
         * 但需要当前时间越来越大
         */
        for (long cnt = 1; cnt <= 3; cnt++) {
            when(DateUtil.getNowTS()).thenReturn(1700L);

            RetryOption opt = new RetryOption();
            RetryData d = new RetryData();
            IntervalCoefficientOption copt = opt.getIntervalCoefficientOption();
            copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE);
            copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});
//        copt.setIntervalCoefficient(10);
            d.setLastRetriedTs(1000L);
            d.setRetriedCnt(cnt);
            /**
             * 1000+ 1* (5* 60)  = 1000+300 = 1300
             * 1000+ 2* (5* 60)  = 1000+600 = 1600
             * 1000+ 3* (5* 60)  = 1000+1800 = 2800
             * now 1601
             */
            System.out.println("cnt:" + cnt);
            if (cnt > 2) {

                assertEquals(false, OptionUtil.isTimeUp(opt, d));
            } else {

                assertEquals(true, OptionUtil.isTimeUp(opt, d));
            }
        }


//        when(DateUtil.getNowTS()).thenReturn(1500L);
////        retryDispatchManager = new RetryDispatchManager();
//        opt = new RetryOption();
//        copt = opt.getIntervalCoefficientOption();
//        copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE);
//        copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});
//
//        d = new RetryData();
//        d.setLastRetriedTs(1000L);
//        d.setRetriedCnt(2L);
//        /**
//         * 1000+ 2* (5* 60)  = 1000+600 = 1600
//         * now 1500
//         */
//        assertEquals(false, OptionUtil.isTimeUp(opt, d));
    }


    @Test
    public void testV(){
        PowerMockito.mockStatic(DateUtil.class);
        when(DateUtil.getNowTS()).thenReturn(1700L);

        RetryOption opt = new RetryOption();
        RetryData d = new RetryData();
        IntervalCoefficientOption copt = opt.getIntervalCoefficientOption();
        copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE);
        copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});
//        copt.setIntervalCoefficient(10);
        d.setLastRetriedTs(1000L);
        d.setRetriedCnt(2L);
        /**
         * 1000+ 1* (5* 60)  = 1000+300 = 1300
         * 1000+ 2* (5* 60)  = 1000+600 = 1600
         * 1000+ 3* (5* 60)  = 1000+1800 = 2800
         * now 1601
         */
        assertEquals(false, OptionUtil.isTimeUp(opt, d));
    }


    /**
     * 可变+固定系数
     * @throws Exception
     */
    @Test
    public void testIsTimeUpVariableCoff() throws Exception {
        PowerMockito.mockStatic(DateUtil.class);
        when(DateUtil.getNowTS()).thenReturn(2801L);

        RetryOption opt = new RetryOption();
        RetryData d = new RetryData();
        IntervalCoefficientOption copt = opt.getIntervalCoefficientOption();
        copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE_MULTIPLE_FIX);
        copt.setIntervalCoefficient(2);
        copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});
//        copt.setIntervalCoefficient(10);
        d.setLastRetriedTs(1000L);
        d.setRetriedCnt(3L);
        /**
         * 1000+ 2*3* (5* 60)  = 1000+1800 = 2800
         * now 2801
         */
        assertEquals(true, OptionUtil.isTimeUp(opt, d));


        when(DateUtil.getNowTS()).thenReturn(1500L);
//        retryDispatchManager = new RetryDispatchManager();
        opt = new RetryOption();
        copt = opt.getIntervalCoefficientOption();
        copt.setIntervalCoeffientType(IntervalCoeffientType.VARIABLE_MULTIPLE_FIX);
        copt.setIntervalCoefficient(2);
        copt.setExponentialBackoffArray(new int[]{1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200});

        d = new RetryData();
        d.setLastRetriedTs(1000L);
        d.setRetriedCnt(3L);
        /**
         * 1000+ 3* (5* 60)  = 1000+1800 = 2800
         * now 1500
         */
        assertEquals(false, OptionUtil.isTimeUp(opt, d));
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
