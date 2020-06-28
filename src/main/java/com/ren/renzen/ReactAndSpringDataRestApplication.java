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

import com.ren.renzen.Websockets.JythonTest;
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
        JythonTest.test();
    }
}
// end::code[]