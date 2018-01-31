package com.atjl.validate.validator.param;

import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.validator.param.form.FormNotExistWith;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class NotExistWithTest {


    @Test
    public void testValidate() throws Exception {
        ValidateForm vf = ValidateFormFactory.build(FormNotExistWith.class);
        Map<String, String> param = new HashMap<>();
        param.put("tkey", "aa");
        param.put("valid", "Y");

        vf.setValue(param);
        if (!vf.validate()) {
            System.out.println("check fail:" + vf.getErrors());
        } else {
            System.out.println("check pass:" + vf.getErrors());
        }

    }

    @Test
    public void testParse() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
