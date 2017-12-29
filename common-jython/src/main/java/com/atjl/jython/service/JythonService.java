package com.atjl.jython.service;


import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PyType;
import org.python.util.PythonInterpreter;


public class JythonService {


    public static void main(String[] args) {


        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\project\\python\\python-libs\\util\\string\\stringutil.py");

        /**
         # str='123456'
         # res = removeTagAfter(str)
         */
        PyFunction pyFunction = interpreter.get("removeTagAfter", PyFunction.class);
//        interpreter.set("str", new PyString("123456"));
        PyObject param = new PyString("123453336");
//        PyObject param = new PyObject("123456");
        PyObject pyObject = pyFunction.__call__(param); // 调用函数

        System.out.println(pyObject);


    }
}
