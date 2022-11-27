package org.example;


/// This will be the Java code to implement

/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson
 */
//Added to allow for Field and Modifier reading
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import java.lang.reflect.Array;

//For constructors later
import java.lang.reflect.Constructor;

//Rename to public class Inspector once done
public class Driver1a {

    public void inspect(Object obj, boolean recursive)
            throws ClassNotFoundException, NoSuchFieldException
    {
        Class c = obj.getClass();
        System.out.println("\nDeclaring Class name = "+c.getName());
        inspectClass(c, obj, recursive, 0);
        //If I use just c, it prints out class Class A
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth)
    ///throws ClassNotFoundException, NoSuchFieldException
    {
        String tabber = "";
        for(int i = 0; i < depth; i++){
            tabber = tabber + "    ";

        }

        System.out.println(tabber+"CLASS");

        //Foundation of superclass
        //Use class C
        Class sc = c.getSuperclass();

        //c.getName();
        System.out.println(tabber+"Current Class: "+c.getName());


        //Does the superclass have anything?
        //Recurse if so
        if(sc != null){
            //If this was just sc != null, it will count
            //SuperClass name: java.lang.Object
            //Current Depth = 3
            //if(sc.getSuperclass()!=null){
            System.out.println(tabber+"SuperClass -> Recursively Inspect");
            System.out.println(tabber+"SuperClass: "+sc.getName());
            System.out.println(tabber+"Depth level: "+(depth));
            inspectClass(sc, obj, recursive, depth+1);
        }
        else{
            System.out.println(tabber+"Superclass : NONE");
        }


        //////INTERFACES
        System.out.print(tabber+"INTERFACES ");////////
        System.out.println("("+c.getName()+")");
        Class[] allInterfaces = c.getInterfaces();

        //System.out.println(scThe number of Interfaces in this is "+allInterfaces.length);
        for (int i = 0; i < allInterfaces.length;i++){
            String name = allInterfaces[i].getName();
            System.out.println(tabber+"Interface name: "+name);
            inspectClass(allInterfaces[i], obj, recursive, depth+1);
        }
        if (allInterfaces.length==0){
            System.out.println("NONE");
        }


        System.out.println(tabber+"CONSTRUCTOR ("+c.getName()+")");
        //"("+c.getName()+")"
        System.out.println(tabber+"Constructors->");
        //System.out.println(tabber+"  CONSTRUCTORS");//////////
        //Class c
        Constructor[] allConstructors = c.getDeclaredConstructors();
        //Class cnew = classObject.getConstructor();
        //Class[] allClasses = c.getDeclaredClasses();
        //System.out.println(tabber+"The number of Declared Constructors in this is "+allConstructors.length);
        try {
            for (int i = 0; i < allConstructors.length; i++) {
                //System.out.println(tabber+c.getDeclaredConstructors()[i]);
                System.out.println(tabber+"   CONSTRUCTORS");
                System.out.println(tabber+"   Name: "+allConstructors[i].getName());
                //Constructor c = classObject.getConstructor(String.class);

                //HARDCODE MARKER
                //System.out.println(tabber+"   Exceptions -> NONE");
                //Parameters and Modifiers
                Parameter[] allParameters = allConstructors[i].getParameters();
                System.out.println(tabber + "   Parameter Types: ");
                //System.out.println(tabber+"The number of Parameters in this is "+allParameters.length);
                for (int j = 0; j < allParameters.length; j++){
                    System.out.println(tabber + "     "+allParameters[j].getParameterizedType());
                }
                //To string method
                System.out.println(tabber+ "   Modifiers: "+Modifier.toString(allConstructors[i].getModifiers()));
            }
            //HARDCODE MARKER
            //System.out.println(tabber+"  Exceptions -> None");
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(tabber+"The number of Classes in this is "+allClasses.length);

        System.out.println(tabber+"METHODS ("+c.getName()+")");////
        //methods / name / exceptions thrown /parameter types / return type / modifiers
        Method[] allMethods = c.getMethods();
        //System.out.println(tabber+"The number of Methods in this is "+allMethods.length);

        System.out.println(tabber+"Methods->");
        for (int i = 0; i < allMethods.length;i++){
            System.out.println(tabber+"  METHOD");
            String name = allMethods[i].getName();
            System.out.println(tabber+"    Method name: "+name);
            //HARDCODE MARKER

            Parameter[] allParameters = allMethods[i].getParameters();
            System.out.print(tabber + "    Parameter Types->");
            //System.out.println(tabber+"The number of Parameters in this is "+allParameters.length);
            if(allParameters.length == 0){
                System.out.println(" NONE");
            }else{
                System.out.println();
                for (int j = 0; j < allParameters.length; j++){
                    System.out.println(tabber + "    "+allParameters[j].getParameterizedType());
                }
            }
            //Return type
            System.out.println(tabber+ "    Return Type: "+allMethods[i].getReturnType());
            //Modifiers
            System.out.println(tabber+ "    Modifiers: "+Modifier.toString(allMethods[i].getModifiers()));

            //System.out.println(tabber+"  Method type: "+type);
        }

        System.out.print(tabber+"Fields->");
        Field[] allFields = c.getDeclaredFields();
        //System.out.println(tabber+"The number of fields in this is "+allFields.length);

        //Class allF2 = c.getFields();


        int xx = 0;
        if(allFields.length != 0){
            for (Field f : c.getDeclaredFields()) {
                xx = xx + 1;
                //System.out.println("#"+xx+":"+f);
                //if(f.getName().equals("value"))
                //    System.out.println(tabber+"VALUE EXISTS");

                System.out.println();
                System.out.println(tabber + "  FIELD");
                String name = f.getName();
                Class type = f.getType();
                int modif = f.getModifiers();
                String mod = Modifier.toString(modif);
                System.out.println(tabber + "  Field name: " + name);
                System.out.println(tabber + "  Field type: " + type);
                System.out.println(tabber + "  Modifiers: " + mod);

                //System.out.println("YYY" + f);

                if (f.getName().equals(name)) {
                    f.setAccessible(true);

                    Field temp = null;
                    Object value;
                    //f.get(Object);
                    //temp = c.getDeclaredField(name);
                    //f = allFields.getDeclaredField("value");
                    //Object v2 = f.get(c);
                    //System.out.println("Major thing that doesn't work but why?");
                    //String currentValue = (String) f.get("x");
                    try {
                        //String currentValue = (String) f.get("x");
                        //System.out.println("Success here");
                    } catch (Exception e) {
                        //System.out.println("Error caught again.");
                    }
                }
            }
        }
        else{
            System.out.println(" NONE!");
            //Utilize this - https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/code/Reflection0/MainFields.java




        }
    }

}