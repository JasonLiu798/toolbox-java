package com.atjl.util.test;

import java.util.List;

/**
 * 测试用例
 * @author JasonLiu798
 * @date 2015/11/27 9:50
 */
public class TestCase {

    private String name;
    private String testClass;
    private String testFunction;
    private String parameterObjectClassName;//参数
    private Class<?> parameterObjectClass;
    private Object parameterObject;
    private List<String> before;
    private List<String> parameterValue;
    private List<String> parameterName;
    private List<String> parameterType;
    private String expect;

    public TestCase() {
    }

    public void setParameterObject(Object parameterObject) {
        this.parameterObject = parameterObject;
    }

    public Object getParameterObject() {
        return parameterObject;
    }

    public void setParameterType(List<String> parameterType) {
        this.parameterType = parameterType;
    }

    public List<String> getParameterType() {
        return parameterType;
    }

    public void setParameterObjectClassName(String parameterObjectClassName) {
        this.parameterObjectClassName = parameterObjectClassName;
    }

    public void setParameterObjectClass(Class<?> parameterObjectClass) {
        this.parameterObjectClass = parameterObjectClass;
    }

    public String getParameterObjectClassName() {
        return parameterObjectClassName;
    }

    public Class<?> getParameterObjectClass() {
        return parameterObjectClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public void setTestFunction(String testFunction) {
        this.testFunction = testFunction;
    }

    public String getTestClass() {
        return testClass;
    }

    public String getTestFunction() {
        return testFunction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParameterName(List<String> parameterName) {
        this.parameterName = parameterName;
    }

    public List<String> getParameterName() {
        return parameterName;
    }

    public void setBefore(List<String> before) {
        this.before = before;
    }

    public void setParameterValue(List<String> parameterValue) {
        this.parameterValue = parameterValue;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public List<String> getBefore() {
        return before;
    }

    public List<String> getParameterValue() {
        return parameterValue;
    }

    public String getExpect() {
        return expect;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "name='" + name + '\'' +
                ", testClass='" + testClass + '\'' +
                ", testFunction='" + testFunction + '\'' +
                ", parameterObjectClassName='" + parameterObjectClassName + '\'' +
                ", parameterObjectClass=" + parameterObjectClass +
                ", before=" + before +
                ", parameterValue=" + parameterValue +
                ", parameterName=" + parameterName +
                ", parameterType=" + parameterType +
                ", expect='" + expect + '\'' +
                '}';
    }
}
