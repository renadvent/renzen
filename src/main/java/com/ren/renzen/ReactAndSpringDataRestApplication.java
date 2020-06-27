/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ren.renzen;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@SpringBootApplication
public class ReactAndSpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.ren.renzen.ReactAndSpringDataRestApplication.class, args);

        PythonInterpreter interpreter = null;
        String path = "C:\\Users\\erick\\.m2\\repository\\org\\python\\jython\\2.7.2\\jython-2.7.2.jar";
//
//		try{
//			Properties p = new Properties();
////			p.setProperty("python.path", path);
////			p.setProperty("python.home", path);
////			p.setProperty("python.prefix", path);
//			//p.setProperty("python.path", "PATH TO jython-standalone-2.7.0.jar");
//			PythonInterpreter.initialize(System.getProperties(), p, new String[] {});
//			interpreter = new PythonInterpreter();
//		}catch (Exception e){
//			System.out.println(e);
//		}
//
        try {
            PythonInterpreter pyInterp = new PythonInterpreter();
            pyInterp.exec("x = 10+10");
            PyObject x = pyInterp.get("x");
            System.out.println(x.asInt());

            pyInterp.execfile("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py");

            //assertEquals("x: ", 20, x.asInt());
        } catch (Exception e) {

            System.out.println(e);

        }


        try {
            StringWriter writer = new StringWriter();
            ScriptContext context = new SimpleScriptContext();
            context.setWriter(writer);

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("python");
            Object x = engine.eval(new FileReader("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py"), context);        //assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", writer.toString().trim());

            System.out.println(x);

        } catch (Exception e) {
            System.out.println(e);
        }
//
//
//		StringWriter writer = new StringWriter();
//		ScriptContext context = new SimpleScriptContext();
//		context.setWriter(writer);
//
//		ScriptEngineManager manager = new ScriptEngineManager();
//		ScriptEngine engine = manager.getEngineByName("python");
//
//		try {
//
//			engine.eval(new FileReader("src/main/python/helloWorld.py"), context);
//			//engine.eval(new FileReader("src/main/python/helloWorld.py"), context);
//			//engine.eval(new FileReader("src/main/python/helloWorld.py"), context);
//			//engine.eval(new FileReader("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py"), context);
//		}catch(Exception e){
//			System.out.println("FAILED");
//			System.out.println(e);
//		}
    }
}
// end::code[]