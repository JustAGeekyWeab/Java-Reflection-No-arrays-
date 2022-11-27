package org.example;
//https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/code/Reflection0/GetFields.java
import java.lang.reflect.Field;
// java --add-opens java.base/java.lang=ALL-UNNAMED
public class Test {

    //For some reason this is not working at all in intellij but is fine outside

    public static void main (String[]args){

        try{
            Object myObject = new MyClass();
            System.out.println("Object: "+myObject);
            Class classObject = myObject.getClass();
            System.out.println("Class: "+classObject);
            System.out.println(classObject.getDeclaredFields()[0]);
            System.out.println(classObject.getDeclaredFields()[1]);
            Field f = null;
            f = classObject.getDeclaredField("x");
            f.setAccessible(true);
            Object value = f.get(myObject);
            System.out.println(value);

            Object myObject2 = "Hello, world!";
            Class classObject2 = myObject2.getClass();
            System.out.println(classObject2.getDeclaredFields()[0]);
            System.out.println(classObject2.getDeclaredFields()[1]);
            Field f2 = null;
            f2 = classObject2.getDeclaredField("value");
            f2.setAccessible(true);
            Object value2 = f2.get(myObject2);
            System.out.println(new String((byte[]) value2));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
