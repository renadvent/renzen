//package com.ren.renzen.Websockets;
//
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;
//
//import javax.script.ScriptContext;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.SimpleScriptContext;
//import java.io.FileReader;
//import java.io.StringWriter;
//
//public class JythonTest {
//
//    public static void test(){
//        try {
//            PythonInterpreter pyInterp = new PythonInterpreter();
//            pyInterp.exec("x = 10+10");
//            PyObject x = pyInterp.get("x");
//            System.out.println(x.asInt());
//
//            pyInterp.execfile("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py");
//
//            //assertEquals("x: ", 20, x.asInt());
//        } catch (Exception e) {
//
//            System.out.println(e);
//
//        }
//
//
//        try {
//            StringWriter writer = new StringWriter();
//            ScriptContext context = new SimpleScriptContext();
//            context.setWriter(writer);
//
//            ScriptEngineManager manager = new ScriptEngineManager();
//            ScriptEngine engine = manager.getEngineByName("python");
//            Object x = engine.eval(new FileReader("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py"), context);        //assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", writer.toString().trim());
//
//            System.out.println(x);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
