package com.incubyte.stepdefination;

import io.cucumber.java.en.Given;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Stepdefs {


    @Given("user executes method {string} from page {string}")
    public void userExecutesMethodFromPage(String methodName, String className) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.incubyte.pages." + className);

        // Find the method with no parameters
        Method method = clazz.getDeclaredMethod(methodName);

        // Create an instance of the class (assuming it has a no-argument constructor)
        Object instance = clazz.getDeclaredConstructor().newInstance();

        // Invoke the method on the instance
        method.invoke(instance);

        System.out.println("Successfully invoked method: " + methodName + " from class: " + className);
    }
}
