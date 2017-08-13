package com.atjl.email.service;

import com.atjl.email.api.MailConstant;
import com.atjl.email.api.MailService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class MailServiceImplTest {


    @Resource(name = MailConstant.MAIL_SERVICE_USE_GENERAL_PLAT)
    MailService mailService;

    @Test
    public void testSendMail() throws Exception {
//        SendMailDto send = new SendMailDto();
        String add = "sfuat888@sfuat.com";
//        String add = "test@test.com";
//        send.setUserId(add);
//        send.setContent("content");
//        send.setSubject("test-title");
        String file = "D:\\a.pdf";
        mailService.sendMail(add, "test_title", "testcontent", file, "aaa.pdf");
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
