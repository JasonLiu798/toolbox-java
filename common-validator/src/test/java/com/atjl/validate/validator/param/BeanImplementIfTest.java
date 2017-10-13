package com.atjl.validate.validator.param;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class BeanImplementIfTest {

    @Resource
    private DataSource dataSource;


    @Test
    public void testValidate() throws Exception {
        ValidateForm vf = ValidateFormFactory.build(FormBeanImplementIfExist.class);
        Map<String, Object> param = new HashMap<>();
        param.put("f1", "dataSource");
        param.put("f2", "dataSource");
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


    @Test
    public void testInnerInit() throws Exception { 
                /* 
                try { 
                   Method method = BeanImplementIf.getClass().getMethod("innerInit", Class.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testInnerChk() throws Exception { 
                /* 
                try { 
                   Method method = BeanImplementIf.getClass().getMethod("innerChk"); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
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
