package com.atjl.jython.util;


import com.atjl.util.collection.CollectionUtilEx;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class PythonExecuteUtil {

    public static void executeScript() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("print('hello')");
    }


    public static PyObject executeFile(String file, String func, String... params) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(file);
        /**
         # str='123456'
         # res = removeTagAfter(str)
         */
        PyFunction pyFunction = interpreter.get(func, PyFunction.class);
//        interpreter.set("str", new PyString("123456"));
        PyObject[] obj = null;//new PyObject();
        if (!CollectionUtilEx.isEmpty(params)) {
            obj = new PyObject[params.length];
            int i = 0;
            for (String param : params) {
                obj[i] = new PyString(param);
                i++;
            }
        }
        return pyFunction.__call__(obj); // 调用函数
    }
}
