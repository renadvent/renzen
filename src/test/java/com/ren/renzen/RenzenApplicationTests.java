//package com.ren.renzen;
//
//import org.junit.jupiter.api.Test;
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.script.ScriptContext;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.SimpleScriptContext;
//import java.io.FileReader;
//import java.io.StringWriter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class RenzenApplicationTests {
//
////	@Test
////	void contextLoads() {
////	}
//
////	@Test
////	public void givenPythonScriptEngineIsAvailable_whenScriptInvoked_thenOutputDisplayed() throws Exception {
////		StringWriter writer = new StringWriter();
////		ScriptContext context = new SimpleScriptContext();
////		context.setWriter(writer);
////
////		ScriptEngineManager manager = new ScriptEngineManager();
////		ScriptEngine engine = manager.getEngineByName("python");
////
////		engine.eval(new FileReader("C:/Users/erick/OneDrive/Documents/renzen_reloaded/renzen/src/main/python/helloWorld.py"), context);
////		//engine.eval(new FileReader(resolvePythonScriptPath("hello.py")), context);
////		//assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", writer.toString().trim());
////	}
//
////	@Test
////	public void givenPythonInterpreter_whenNumbersAdded_thenOutputDisplayed() {
////		try (PythonInterpreter pyInterp = new PythonInterpreter()) {
////			pyInterp.exec("x = 10+10");
////			PyObject x = pyInterp.get("x");
////			System.out.println(x.asInt());
////			//assertEquals("x: ", 20, x.asInt());
////		}
////	}
//
//}
