package com.atjl.validate.validator;

import com.atjl.validate.api.ValidateField;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.multiparam.NumRange;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;


public class NumRangeTest {


    @Test
    public void testChk() throws Exception {
        NumRange nr = new NumRange(100L);

        Long raw = 101L;
        ValidateForm form = null;
        ValidateField field = new ValidateField() {
            @Override
            public String getRawValue() {
                return String.valueOf(raw);
            }

            @Override
            public void setRawValue(String val) {

            }

            @Override
            public String getLabel() {
                return null;
            }

            @Override
            public List<Validator> getValidators() {
                return null;
            }
        };
        nr.validate(form, field);

    }

    @Test
    public void testValidate() throws Exception {

    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = NumRange.getClass().getMethod("init", Long.class, Long.class, String.class, boolean.class); 
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
