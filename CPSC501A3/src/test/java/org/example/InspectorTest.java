package org.example;

//This didn't work until I did the following two:
//Adding Junit 4 to classpath
//Adding Junit 5.81 to classpath
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//This cannot work for some reason

class InspectorTest {

    Inspector inspect = new Inspector();

    public String tabber = "";

    @Test
    void pending1() {
        inspect.updateTabber(2);
        String tab = inspect.getTabber();
        assertEquals(tab, "\t\t");
    }
    void pending2() {
        //String a = new String ("Gura");
    }
    void pending3() {
    }
    void pending4() {
    }

    void pending5() {
    }

}