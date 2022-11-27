# A3

Neil Mariano
3002 8838

I made Junit work but you would need two things in order to make it work for
your computer or not at all. The two things you'd need to do are:

*Adding Junit 4 to classpath
*Adding Junit 5.81 to classpath

##

How to 
Check out this cool gitlab link
https://gitlab.cpsc.ucalgary.ca/neil.mariano/A3

Notice - My Driver is modified
The different versions of Driver1a, 1b, and 1c are all prototypes
of my current Inspector. All my files are in gitlab
They are handled with Intelij
please use this 
Run -> Edit Configurations -> Paste this
JAVA_TOOL_OPTIONS=--add-opens=java.base/java.lang=ALL-UNNAMED
on Environment Variables

Otherwise
java --add-opens java.base/java.lang=ALL-UNNAMED Driver


## NOTABLE ISSUES

Final Notes.

There is an odd thing where the methods don't print properly.
They are printing in various orders.
I'm not sure I can fix them in time.

If for some reason this doesn't work on the school computers---
Delete all the package org.example and it should work
Afterwards

javac Driver.java
java --add-opens java.base/java.lang=ALL-UNNAMED Driver

## Refactor

1) Made tabber a global variable that everyone can use.
2) Made the updateTabber to keep tabber updates for every use based on depth
3) Made SuperclassFix method and removed it from Inspect
4) Made InterfaceFix method and removed it from Inspect
5) Made MethodFix method and removed it from Inspect
6) Made ConstructoFix method and removed it from Inspect
7) Made Fieldfix method and removed it from Inspect

Current goals
*Recurse through Field values
*Figure out why my methods are being disorganized sometimes
*Refactor and JUnit testing 

## Resources to keep track of

https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/code/Reflection0/
Primary examples

https://pages.cpsc.ucalgary.ca/~jwhudson/CPSC501F22/slides/CPSC501-4Reflection-2Java.pdf
Great explanations

https://www.javatpoint.com/java-array-getlength-method
Basic. Not necessary to link honestly.

https://www.programiz.com/java-programming/reflection
Helpful guide for specific examples

https://www.geeksforgeeks.org/method-class-getmodifiers-method-in-java/ 
Simple way to get some modifiers. Supplementary to previous 

https://stackoverflow.com/questions/11097658/getting-the-field-length-in-a-java-array-using-reflection
Method to get lengths for arrays. Useful and although I figured it out, I feel like this is
necessary to include just in case

https://www.geeksforgeeks.org/class-getfield-method-in-java-with-examples/
Basic ways to get some field methods

https://jenkov.com/tutorials/java-reflection/index.html
Some other exploration of other features