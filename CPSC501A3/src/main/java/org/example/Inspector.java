package org.example;
/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson
 */

//All necessary in order to do
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Array;

public class Inspector {
    //Refactor 1 - Global variable for easier use
    public String tabber;

    //Starter section.. Catches exceptions
    public void inspect(Object obj, boolean recursive)
            throws ClassNotFoundException, NoSuchFieldException
    {
        tabber = "";
        int depth = 0;
        updateTabber(depth);
        Class c = obj.getClass();
        //System.out.println("\nDeclaring Class name = "+c.getName()); // Turn off comments to debug
        inspectClass(c, obj, recursive, depth);
    }

    //Refactor 2 - Separated the Tabber to keep it 'updated'
    public void updateTabber(int depth)
    {
        tabber = "";
        //System.out.println(depth + " is the current depth"); //Turn off to debug
        for(int i = 0; i < depth; i++)
        {
            tabber = tabber + "\t";
        }
    }

    //Get tabber for the sake of J unit testing
    public String getTabber(){
        return tabber;
    }

    //Refactor 3. Handles Super classes
    public void superClassFix(Class sc, Object obj, boolean recursive, int depth){
        //Sets Tabber to the current depth
        updateTabber(depth);
        //If there is no superclass, we dont do anything and simply say there is none.
        if((sc != null))
        {
            //If there is we say the name and keep on going deeper into it
            System.out.println(tabber+"SUPERCLASS -> Recursively Inspect");
            System.out.println(tabber+"SuperClass: "+sc.getName());
            inspectClass(sc, obj, recursive, depth+1);
        }
        else
            System.out.println(tabber+"SuperClass: NONE");
    }

    //Refactor 4. Handles interfaces
    public void interfaceFix(Class c, Object obj, boolean recursive, int depth){
        //Reset depth
        updateTabber(depth);
        System.out.print(tabber+"INTERFACES");
        System.out.println("( "+c.getName()+" )");
        //Creates an array of interfaces via Get interfaces
        Class[] allInterfaces = c.getInterfaces();
        System.out.print(tabber+"Interfaces->");
        //If there is none, we simply say theer is none
        if (allInterfaces.length==0)
            System.out.println(" NONE");
        else
        //If there is some, we print them out
        {
            System.out.println();
            for (int i = 0; i < allInterfaces.length; i++)
            {
                //Update the tabber
                updateTabber(depth);
                //Get the names and now print them out
                String name = allInterfaces[i].getName();
                System.out.println(tabber + " INTERFACE -> Recursively Inspect");
                System.out.println(tabber + " " + name);
                //We then go deeper to explore the interface. Depth +1
                inspectClass(allInterfaces[i], obj, recursive, depth + 1);
            }
        }
    }

    //Refactor 5. Now we get methods
    public void methodFix (Class c, int depth){
        //reset
        updateTabber(depth);
        //Get the name of the current method
        System.out.println(tabber+"METHODS( "+c.getName()+" )");
        //Get an array of them
        Method[] allMethods = c.getDeclaredMethods();
        System.out.print(tabber+"Methods->");
        //If the array is not empty we explore it
        if(allMethods.length != 0){
            System.out.println();
            //Print out all the methods, their names
            for (int i = 0; i < allMethods.length; i++) {
                System.out.println(tabber + " METHOD");
                String name = allMethods[i].getName();
                //Print out names + exceptions
                System.out.println(tabber + "  Name: " + name);
                System.out.print(tabber + "  Exceptions->");
                Class[] excep = allMethods[i].getExceptionTypes();
                if (excep.length == 0)
                    System.out.println(" NONE");
                else
                    System.out.println("\n" + tabber + "  " + excep[0]);
                //Get the parameters afterwards. Don't print them if none
                Parameter[] allParameters = allMethods[i].getParameters();
                System.out.print(tabber + "  Parameter types->");
                if (allParameters.length == 0)
                    System.out.println(" NONE");
                //If there's some we print them out
                else
                {
                    System.out.println();
                    for (int j = 0; j < allParameters.length; j++) {
                        //Keep for debugging
                        //System.out.println(tabber + "  "+allParameters[j].getParameterizedType());//More inaccurate
                        System.out.println(tabber + "  " + allParameters[j].getType());
                    }
                }
                //Now get the return types
                System.out.println(tabber + "  Return type: " + allMethods[i].getReturnType());
                //NOTE - Fix te tostring. Use something else. Might be too late
                String myMod = Modifier.toString(allMethods[i].getModifiers());
                System.out.print(tabber + "  Modifiers: ");
                //Methods empty? Dont print
                //Methods have something? Print them out.
                if(allMethods[i].getModifiers() == 0)
                    System.out.println("NONE");
                else
                    System.out.println(myMod);
            }
        }
        else
            //No methods
            System.out.println(" NONE");
    }

    //Refactor 6. Constructors
    public void constructorFix(Class c, int depth){
        //Reset
        updateTabber(depth);
        System.out.println(tabber+"CONSTRUCTORS( "+c.getName()+" )");
        System.out.print(tabber+"Constructors->");
        //Get array of constructors
        Constructor[] allConstructors = c.getDeclaredConstructors();
        if(allConstructors.length!=0)
        {
            //if length is not 0 keep going
            System.out.println();
            for (int i = 0; i < allConstructors.length; i++) {
                //Print values
                System.out.println(tabber + " CONSTRUCTOR");
                System.out.println(tabber + "  Name: " + allConstructors[i].getName());
                System.out.print(tabber + "  Exceptions->");
                //Get array of exceptions
                Class[] excep = allConstructors[i].getExceptionTypes();
                if (excep.length == 0)
                    System.out.println(" NONE");
                else
                    System.out.println("\n" + tabber + "  " + excep[0]);
                //Parameters and Modifiers this time
                Parameter[] allParameters = allConstructors[i].getParameters();
                System.out.println(tabber + "  Parameter types:");
                //Loop to display all parameters
                for (int j = 0; j < allParameters.length; j++)
                {
                    System.out.println(tabber + "   " + allParameters[j].getParameterizedType());
                }
                //NOTE - Fix te tostring. Use something else. Might be too late
                String myModifier = Modifier.toString(allConstructors[i].getModifiers());
                System.out.print(tabber + "  Modifiers: ");
                //Discover where there's modifiers or not
                if(allConstructors[i].getModifiers() == 0)
                    System.out.println("NONE");
                else
                    System.out.println(myModifier);
            }
        }
        else
            //Only if empty/none
            System.out.println(" NONE");
    }

    //Refactor 7 - Handles the fields
    public void fieldsFix(Class c, Object obj, boolean recursive, int depth){
        //Reset
        updateTabber(depth);
        System.out.println(tabber+"FIELDS( "+c.getName()+" )");
        System.out.print(tabber+"Fields->");
        int temp = c.getDeclaredFields().length;
        //Length check
        if (temp == 0)
            System.out.println(" NONE");
        else {
            System.out.println();
            //Based on the reflection example this time. Much cleaner to use this
            for (Field f : c.getDeclaredFields()) {
                //Get all necessary values
                System.out.println(tabber + " FIELD");
                String name = f.getName();
                Class type = f.getType();
                int modif = f.getModifiers();
                //NOTE - Fix te tostring. Use something else. Might be too late
                String mod = Modifier.toString(modif);
                System.out.println(tabber + "  Name: " + name);
                System.out.println(tabber + "  Type: " + type);
                System.out.println(tabber + "  Modifiers: " + mod);
                boolean arrayornot = type.isArray();
                //Try catch just in case
                try{
                    Field fx = c.getDeclaredField(name);
                    fx.setAccessible(true);
                    Object temporary = fx.get(obj);
                    boolean isthisPrimitive;
                    //Is this an array? If yes, keep going
                    if (arrayornot) {
                        Class compType = type.getComponentType();
                        int arraylength = Array.getLength(temporary);
                        //Print out the component type + length
                        System.out.println(tabber + "  Component Type: " + compType);
                        System.out.println(tabber + "  Length: " + arraylength);
                        System.out.println(tabber + "  Entries->");
                        //Prints out the values
                        for (int ii = 0; ii < arraylength; ii++) {
                            System.out.print(tabber + "   Value: ");
                            System.out.println(Array.get(temporary, ii));
                        }
                    }
                    //If not it might be something ot explore
                    else {

                        System.out.print(tabber + "  Value");
                        isthisPrimitive = type.isPrimitive();
                        if (!isthisPrimitive) {
                            System.out.println(" (ref): " + temporary);
                            System.out.println(tabber + "    -> Recursively inspect");
                            //Only works on not null and non-primitive values. Will go through
                            if(temporary != null){
                                //If recursive is set to true, we will explore the object reference
                                if(recursive==true)
                                {
                                    Class cc = temporary.getClass();
                                    inspectClass(cc, temporary, recursive, depth + 1);
                                }
                            }
                        }
                        else
                            //If no recursion needed, will go here instead
                            System.out.println(": " + temporary);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //Main center. Used to be a gigantic Method, now handles management of all the methods
    private void inspectClass(Class c, Object obj, boolean recursive, int depth)
            //throws ClassNotFoundException, NoSuchFieldException
    {
        updateTabber(depth);
        //Get the superclass
        Class sc = c.getSuperclass();
        //Class newClass = obj.getClass();

        //CLASS SECTION::
        System.out.println(tabber+"CLASS");
        System.out.println(tabber+"Class: "+c.getName());

        //SUPERCLASS SECTION::
        //Refactor 3 Made superclass into a method
        superClassFix(sc, obj, recursive, depth);

        //INTERFACE SECTION::
        //Refactor 4 Interface into a method
        interfaceFix(c, obj, recursive, depth);

        //CONSTRUCTOR SECTION::
        //Refactor 5
        constructorFix(c, depth);

        //METHOD SECTION::
        //Refactor 6
        methodFix(c, depth);

        //FIELD SECTION::
        //Refactor 7
        //https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/code/Reflection0/MainFields.java
        fieldsFix(c, obj, recursive, depth);
    }
}