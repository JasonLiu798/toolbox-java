package com.atjl.observer.core;

import com.atjl.observer.api.EventManager;
import com.atjl.observer.api.ListenerStatus;
import com.atjl.util.json.JSONFastJsonUtil;
import event.EventA;
import listener.TestListner;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class EventManagerImplTest {

    @Resource
    EventManager eventManager;

    @Resource
    TestListner testListner;

    @Test
    public void testInit() throws Exception {

        eventManager.register(testListner);

        List<ListenerStatus> status = eventManager.getListenerStatus();

        System.out.println("bf:" + JSONFastJsonUtil.objectToJson(status));


        EventA e = new EventA();
        e.setA(123);
        eventManager.fire(e);
        eventManager.fire(e);

        status = eventManager.getListenerStatus();

        System.out.println("af:" + JSONFastJsonUtil.objectToJson(status));


    }

    @Test
    public void testDestroy() throws Exception {

    }

    @Test
    public void testGetListenerStatus() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testUnregister() throws Exception {

    }

    @Test
    public void testFire() throws Exception {

    }

    @Test
    public void testUnregisterAll() throws Exception {

    }

    @Test
    public void testSetPushState() throws Exception {

    }

    @Test
    public void testGetPushState() throws Exception {

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
