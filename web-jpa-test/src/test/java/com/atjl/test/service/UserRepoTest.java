package com.atjl.test.service;


import com.atjl.test.config.SpringConfig;
import com.atjl.test.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class UserRepoTest {

    private ApplicationContext ctx = null;
    private IUserService userService = null;

    @Before
    public void init() {
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        employeeRepository = ctx.getBean(UserRepository.class);
        userService = ctx.getBean(IUserService.class);
    }

    @After
    public void destroy() {
        ctx = null;
    }

    @Test
    public void entityManageFactoryTest() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = (LocalContainerEntityManagerFactoryBean) ctx.getBean(LocalContainerEntityManagerFactoryBean.class);
        Assert.assertNotNull(entityManagerFactory);
    }

    @Test
    public void add() {
        User u = new User();
        u.setId(100L);
        u.setAddress("aaaa1");
        u.setName("nnnn");
        userService.saveUser(u);

    }

    @Test
    public void findByNameTest() {
//        System.out.println(employeeRepository);
//        Employee employee = employeeRepository.findByName("cc");
//        if( null == employee){
//            System.out.println("查询数据为空");
//        }else{
//            System.out.println(employee.toString());
//        }
    }


}
