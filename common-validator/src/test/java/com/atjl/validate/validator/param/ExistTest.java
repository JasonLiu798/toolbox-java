package com.atjl.validate.validator.param;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ExistTest {


    @Test
    public void testValidate() throws Exception {
        ValidateForm vf = ValidateFormFactory.build(FormExist.class);
        Map<String, String> param = new HashMap<>();
        param.put("tkey", "test");
        vf.setValue(param);
        if (!vf.validate()) {
            System.out.println("check fail:" + vf.getErrors());
        } else {
            System.out.println("check pass:" + vf.getErrors());
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
