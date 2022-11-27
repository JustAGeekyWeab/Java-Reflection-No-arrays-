package org.example;



/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson
 */

//https://docs.oracle.com/javase/9/docs/api/java/lang/reflect/AccessibleObject.html
//Added to allow for Field and Modifier reading
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.AccessibleObject;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import java.lang.reflect.Array;

public class Driver1b {

    public void inspect(Object obj, boolean recursive)
            throws ClassNotFoundException, NoSuchFieldException
    {
        Class c = obj.getClass();
        System.out.println("\nDeclaring Class name = "+c.getName());
        inspectClass(c, obj, recursive, 0);
        //If I use just c, it prints out class Class A
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth)
    //throws ClassNotFoundException, NoSuchFieldException
    {
        String tabber = "";
        for(int i = 0; i < depth; i++){
            tabber = tabber + "\t";

        }
        //System.out.println("Depth level "+(depth));


        //Get the superclass
        Class sc = c.getSuperclass();
        //Class newClass = obj.getClass();

        //CLASS SECTION::
        System.out.println(tabber+"CLASS");
        System.out.println(tabber+"Class: "+c.getName());

        //SUPERCLASS SECTION::
        //System.out.println(tabber+":SUPERCLASS");
        //Boolean b = true;

        if((sc != null)){
            //System.out.println(tabber+"Superclass performed"); //Debugger
            System.out.println(tabber+"SUPERCLASS -> Recursively Inspect");
            System.out.println(tabber+"SuperClass: "+sc.getName());
            inspectClass(sc, obj, recursive, depth+1);
        }
        else{
            System.out.println(tabber+"SuperClass: NONE");
        }

        //INTERFACE SECTION::
        System.out.print(tabber+"INTERFACES");////////
        System.out.println("( "+c.getName()+" )");
        Class[] allInterfaces = c.getInterfaces();
        System.out.print(tabber+"Interfaces->");
        if (allInterfaces.length==0){
            System.out.println(" NONE");
        }
        else{
            System.out.println();
            //System.out.println(scThe number of Interfaces in this is "+allInterfaces.length);
            for (int i = 0; i < allInterfaces.length; i++) {
                String name = allInterfaces[i].getName();
                System.out.println(tabber+" INTERFACE -> Recursively Inspect");
                System.out.println(tabber + " " + name);
                inspectClass(allInterfaces[i], obj, recursive, depth + 1);


                //Interface recursion
            }
        }


        //CONSTRUCTOR SECTION::
        System.out.println(tabber+"CONSTRUCTORS( "+c.getName()+" )");
        //"("+c.getName()+")"
        System.out.print(tabber+"Constructors->");
        //System.out.println(tabber+"  CONSTRUCTORS");//////////
        //Class c
        Constructor[] allConstructors = c.getDeclaredConstructors();
        //Class cnew = classObject.getConstructor();
        //Class[] allClasses = c.getDeclaredClasses();
        //System.out.println(tabber+"The number of Declared Constructors in this is "+allConstructors.length);
        if(allConstructors.length!=0){
            System.out.println();
            for (int i = 0; i < allConstructors.length; i++) {
                //System.out.println(tabber+c.getDeclaredConstructors()[i]);
                System.out.println(tabber + " CONSTRUCTOR");
                System.out.println(tabber + "  Name: " + allConstructors[i].getName());
                //Constructor c = classObject.getConstructor(String.class);

                System.out.print(tabber + "  Exceptions-> ");
                //HARDCODE MARKER
                Class[] excep = allConstructors[i].getExceptionTypes();
                if (excep.length == 0)
                    System.out.println("NONE");
                else {
                    System.out.println("\n" + tabber + "  " + excep[0]);
                }
                //System.out.println(tabber+"   Exceptions -> NONE");
                //Parameters and Modifiers
                Parameter[] allParameters = allConstructors[i].getParameters();
                System.out.println(tabber + "  Parameter types:");
                //System.out.println(tabber+"The number of Parameters in this is "+allParameters.length);
                for (int j = 0; j < allParameters.length; j++) {
                    System.out.println(tabber + "   " + allParameters[j].getParameterizedType());
                    //System.out.println(tabber + "2/    " + allParameters[j].getType());
                }
                //To string method
                String myModifier = Modifier.toString(allConstructors[i].getModifiers());
                System.out.print(tabber + "  Modifiers: ");
                if(allConstructors[i].getModifiers() == 0){
                    System.out.println("NONE");}
                else
                    System.out.println(myModifier);
            }
        }
        else{
            System.out.println(" NONE");
        }

        //METHOD SECTION::
        System.out.println(tabber+"METHODS( "+c.getName()+" )");////
        //methods / name / exceptions thrown /parameter types / return type / modifiers
        Method[] allMethods = c.getDeclaredMethods();
        //System.out.println(tabber+"The number of Methods in this is "+allMethods.length);

        System.out.print(tabber+"Methods->");
        if(allMethods.length != 0){
            System.out.println();
            for (int i = 0; i < allMethods.length; i++) {
                System.out.println(tabber + " METHOD");
                String name = allMethods[i].getName();
                System.out.println(tabber + "  Name: " + name);
                System.out.print(tabber + "  Exceptions->");
                //HARDCODE MARKER
                Class[] excep = allMethods[i].getExceptionTypes();
                if (excep.length == 0)
                    System.out.println("NONE");
                else {
                    System.out.println("\n" + tabber + "  " + excep[0]);
                }

                Parameter[] allParameters = allMethods[i].getParameters();
                System.out.print(tabber + "  Parameter types->");
                //System.out.println(tabber+"The number of Parameters in this is "+allParameters.length);
                if (allParameters.length == 0) {
                    System.out.println(" NONE");
                } else {
                    System.out.println();
                    for (int j = 0; j < allParameters.length; j++) {
                        //System.out.println(tabber + "  "+allParameters[j].getParameterizedType());
                        System.out.println(tabber + "  " + allParameters[j].getType());
                    }
                }
                //Return type
                System.out.println(tabber + "  Return type: " + allMethods[i].getReturnType());
                //Modifiers
                String myMod = Modifier.toString(allMethods[i].getModifiers());
                System.out.print(tabber + "  Modifiers: ");
                if(allMethods[i].getModifiers() == 0){
                    System.out.println("NONE");}
                else
                    System.out.println(myMod);

                //System.out.println(tabber+"  Method type: "+type);
            }
        }
        else{
            System.out.println(" NONE");
        }


        //FIELD SECTION::
        //https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/code/Reflection0/MainFields.java
        System.out.println(tabber+"FIELDS( "+c.getName()+" )");
        System.out.print(tabber+"Fields->");
        int temp = c.getDeclaredFields().length;
        if (temp == 0) {
            System.out.println(" NONE");
        } else {
            System.out.println();
            //System.out.println(tabber+"  has this amount of fields: "+temp);
            for (Field f : c.getDeclaredFields()) {
                System.out.println(tabber + " FIELD");
                String name = f.getName();
                Class type = f.getType();
                int modif = f.getModifiers();
                String mod = Modifier.toString(modif);
                System.out.println(tabber + "  Name: " + name);
                System.out.println(tabber + "  Type: " + type);
                System.out.println(tabber + "  Modifiers: " + mod);

                boolean arrayornot = type.isArray();
                ///System.out.println("Is {"+name+"} is an array? "+arrayornot);


                try{
                    Field fx = null;
                    fx = obj.getClass().getDeclaredField(name);
                    fx.setAccessible(true);
                    Object temporary = fx.get(obj);

                    //System.out.println("SIGH"+temporary.getClass());
                    if (arrayornot) {
                        //Class tempClass = fx.getClass(); //Class lass [Ljava.io.ObjectStreamField;
                        //Same as getType
                        //Goal is class java.io.ObjectStreamField
                        //System.out.println("!!!!"+tempClass);

                        //System.out.println("owo"+type.getComponentType());

                        Class compType = type.getComponentType();
                        int arraylength = Array.getLength(temporary);
                        //Field[] lastX = compType.getDeclaredFields();



                        System.out.println(tabber + "  Component Type: "+compType);
                        System.out.println(tabber + "  Length: " + arraylength);
                        System.out.println(tabber + "  Entries->");
                        //System.out.println("I hate this "+compType.getDeclaredFields());
                        //System.out.println(type.getElement(type.getObjArray(), 0));
                        for(int ii = 0; ii < arraylength;ii++){
                            System.out.print(tabber + "   Value: ");
                            System.out.println(ii);
                        }

                        /*
                        for (Field xxx : compType.getDeclaredFields()) {
                            System.out.print(tabber + "   Value: ");
                            System.out.println(arraylength);
                        }//*/
                    }
                    else{
                        System.out.print(tabber + "  Value");
                        Boolean checker = type.isPrimitive();
                        if (!checker) {
                            System.out.println(" (ref): " + temporary);
                            System.out.println(tabber + "    -> Recursively inspect");
                            //Figure this out, currently ends in a loop
                            //inspectClass(c, temporary, recursive, depth+1);
                        } else {
                            System.out.println(": " + temporary);
                        }
                    }
                    if(recursive){
                        ///inspectClass(temporary, obj, recursive, depth+1);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        //Field f3 = c.getDeclaredField("number");
        //newClass;
        //System.out.println("Field status = ");
        //Field[] allFields = c.getDeclaredFields();
    }

}
