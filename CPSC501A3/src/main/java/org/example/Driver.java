package org.example;


import java.io.*;

/**
 * CPSC 501
 * Driver to create output using inspector
 *
 * @author Jonathan Hudson
 */
public class Driver {

    public static void main(String[] args) throws Exception {
        boolean rec = true;
        if (args.length == 1) {
            rec = Boolean.parseBoolean(args[0]);
        }
        runTest("script1.txt", new ClassA(), rec);
        runTest("script2.txt", new ClassA(12), rec);
        runTest("script3.txt", new ClassB(), rec);//Has a weird error regarding java fields
        runTest("script4.txt", new ClassD(32), rec);
        runTest("script5.txt", new ClassD(), rec);
        runTest("script6.txt", new ClassB[12], rec);
        ClassB[] x = new ClassB[12];
        x[3] = new ClassB();
        runTest("script7.txt", x, rec);
        runTest("script8.txt", "Test String", rec);

        //runTest("scriptMyClass.txt", new MyClass(), rec); // Deleted practice file for Hello world
        //For participation
        String xx = new String("Hello, world!");
        runTest("scriptPart.txt", xx, rec);

        Boolean tt = false;
        //runTest("script9.txt", tt, rec);//With current version, runs into an issue
        int[] a = {1,2};
        runTest("scriptTest1.txt", a, rec);
    }

    public static void runTest(String filename, Object testObj, boolean recursive) {
        try {
            PrintStream old = System.out;
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
            ///This is a complete difference to the text on Helloworld. Feel free to comment them
            //For the sake of making it more similar
            System.out.println("======================================================");///
            System.out.println("Filename: " + filename);///
            System.out.println("Running Test: " + testObj);///
            System.out.println("Recursive: " + recursive);///
            new Inspector().inspect(testObj, recursive);
            System.out.println("======================================================");///
            ps.flush();
            fos.flush();
            ps.close();
            fos.close();
            System.setOut(old);
        } catch (IOException ioe) {
            System.err.println("Unable to open file: " + filename);
        } catch (Exception e) {
            System.err.println("Unable to compleatly run test: " + filename);
            e.printStackTrace();
        }
    }
}
