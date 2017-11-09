package com.jason798;

import com.atjl.jetty.util.ClassPathUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class ClassPathUtilTest {

    static ClassPathUtil cpu = new ClassPathUtil("D:\\project\\6041");

    @Test
    public void testGetJars() throws Exception {
        List<String> files = cpu.getJars("lib", new String[]{"dev-only"});
        System.out.println(files);
    }

    @Test
    public void testRecuGetModule() throws IOException {
        List<String> moduels = new LinkedList<>();
        cpu.recuGetModule("inv-fetcher-oms-rest-webapp", moduels, 2, 0);
        for (int i = 0; i < moduels.size(); i++) {
            System.out.println(moduels.get(i));
        }

    }

    @Test
    public void testRecuGetModuleTree() throws IOException {
        List<String> moduels = cpu.getTree("inv-fetcher-oms-rest-webapp");
        for (int i = 0; i < moduels.size(); i++) {
            System.out.println(moduels.get(i));
        }
    }

    @Test
    public void testGetModuleClassPath() throws IOException {
        String module = "inv-fetcher-oms-rest-webapp";
        List<String> modules = new LinkedList<>();
        modules.add(module);
        List<String> cps = cpu.getModuleClasspath(modules);
        System.out.println(cps);
        String[] execludes = new String[]{"inv-hbase", "inv-kafka"};
        cps = cpu.getModuleClasspath(modules);
        //cps = cpu.getModuleClasspath("inv-fetcher-oms-rest-webapp");
        System.out.println(cps);
    }

    @Test
    public void testGetModuleResource() throws IOException {
        String module = "inv-fetcher-oms-rest-webapp";
        List<String> modules = new LinkedList<>();
        modules.add(module);
        List<String> cps = cpu.getModuleResource(modules);
        System.out.println(cps);
        String[] execludes = new String[]{"inv-hbase", "inv-kafka"};
        cps = cpu.getModuleResource(modules);
        //cps = cpu.getModuleResource("inv-fetcher-oms-rest-webapp", execludes);
        System.out.println(cps);
    }

    @Test
    public void testGetCps() throws IOException {
        String module = "inv-fetcher-oms-rest-webapp";
        List<String> cps = cpu.getCps(module, null);
        System.out.println(cps);
    }

    @Test
    public void testGetCpStr() throws IOException {
        String module = "inv-fetcher-oms-rest-webapp";
        String cps = cpu.getCpStr(module, null);
        System.out.println(cps);

    }

    @Test
    public void testGetJar() {

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
