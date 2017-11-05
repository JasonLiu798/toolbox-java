package com.atjl.validate.validator.param;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.validator.param.form.FormBeanExist;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class BeanExistTest {


    @Test
    public void testValidate() throws Exception {

        ValidateForm vf = ValidateFormFactory.build(FormBeanExist.class);
        Map<String, String> param = new HashMap<>();
        param.put("f1", "dataSource");
        vf.setValue(param);
        if (!vf.validate()) {
            System.out.println("check fail:" + vf.getErrors());
        } else {
            System.out.println("check pass");
        }
    }

    @Test
    public void testParse() throws Exception {

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
