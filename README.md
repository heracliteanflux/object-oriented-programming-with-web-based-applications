# Object-oriented design and GUI programming in Java

Exercises in the Java programming language with an emphasis on object-oriented design and applications in GUI programming.

---

![](6_CourseSchedulerGUI/img/05.png)

---

### Table of Contents
1. [Tools](#tools)
2. [Terms](#terms)
3. [Based on](#based-on)

---

### Tools

[[H](https://docs.oracle.com/en/java/index.html)][[W](https://en.wikipedia.org/wiki/Java_(programming_language))] Oracle Java
* [Java Standard Edition](https://docs.oracle.com/en/java/javase/index.html)
  * [The Java Tutorials](https://docs.oracle.com/javase/tutorial/)
    * [Getting Started](https://docs.oracle.com/javase/tutorial/getStarted/index.html)
    * [Learning the Java Language](https://docs.oracle.com/javase/tutorial/java/index.html)
    * [Essential Java Classes](https://docs.oracle.com/javase/tutorial/essential/index.html)
      * [PATH and CLASSPATH](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)
    * [Collections](https://docs.oracle.com/javase/tutorial/collections/index.html)
    * [Date Time](https://docs.oracle.com/javase/tutorial/datetime/index.html)
    * [Deployment](https://docs.oracle.com/javase/tutorial/deployment/index.html)
    * [Creating a GUI with Swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html)
      * [Modifying the Look and Feel](https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html)
    * [Creating a JavaFX GUI](https://docs.oracle.com/javase/8/javase-clienttechnologies.htm)
  * [JDK Tools and Utilities](https://docs.oracle.com/javase/8/docs/technotes/tools/)
    * [Java Platform, Standard Edition Tools Reference](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/)
      * [2 Setting the Class Path](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html)
  * [Java Downloads](https://www.oracle.com/java/technologies/downloads/)
    * [Java SE 18 Archive Downloads](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
* [[W](https://en.wikipedia.org/wiki/JShell)] jshell

[[H](https://ant.apache.org/)][[W](https://en.wikipedia.org/wiki/Apache_Ant)] Apache Ant

[[H](https://db.apache.org/derby/)][[W](https://en.wikipedia.org/wiki/Apache_Derby)] Apache Derby
* [[D](https://db.apache.org/derby/quick_start.html)]
  * [[D](https://db.apache.org/derby/papers/DerbyTut/install_software.html)] 1 Install Software
  * [[D](https://db.apache.org/derby/papers/DerbyTut/ij_intro.html)] 2 ij Basics
  * [[D](https://db.apache.org/derby/papers/DerbyTut/embedded_intro.html)] 3 Embedded Derby
* [[Download](https://db.apache.org/derby/derby_downloads.html)]
* [[Download](https://www.oracle.com/java/technologies/javadb-downloads.html)] Java DB

[[H](https://ant.apache.org/ivy/)][[W](https://en.wikipedia.org/wiki/Apache_Ivy)] Apache Ivy

[[H](https://maven.apache.org)][[W](https://en.wikipedia.org/wiki/Apache_Maven)] Apache Maven

[[H](https://netbeans.apache.org/)][[W](https://en.wikipedia.org/wiki/NetBeans)] Apache NetBeans
* [Java SE Learning Trail](https://netbeans.apache.org/kb/docs/java/)
  * [Designing a Swing GUI in NetBeans IDE](https://netbeans.apache.org/kb/docs/java/quickstart-gui.html)

[[H](https://junit.org)][[W](https://en.wikipedia.org/wiki/JUnit)] JUnit

[[H](https://www.eclipse.org/ide/)][[W](https://en.wikipedia.org/wiki/Eclipse_(software))] Eclipse

---

### Terms

* [[W](https://en.wikipedia.org/wiki/Apache_Derby)] Apache Derby
* [[W](https://en.wikipedia.org/wiki/Array_(data_structure))] Array
* [[W](https://en.wikipedia.org/wiki/Vector_processor)] Array Processor
* [[W](https://en.wikipedia.org/wiki/Array_programming)] Array Programming
* [[W](https://en.wikipedia.org/wiki/Java_bytecode)] Bytecode
* [[W](https://en.wikipedia.org/wiki/Class_(computer_programming))] Class
* [[W](https://en.wikipedia.org/wiki/Class-based_programming)] Class-Based Programming
* [[W](https://en.wikipedia.org/wiki/Java_class_file)] Class File
* [[W](https://en.wikipedia.org/wiki/Class_invariant)] Class Invariant
* [[W](https://en.wikipedia.org/wiki/Classpath)] Class Path
* [[W](https://en.wikipedia.org/wiki/Composition_over_inheritance)] Composition Over Inheritance
* [[W](https://en.wikipedia.org/wiki/Constructor_(object-oriented_programming))] Constructor
* [[W](https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science))] Contravariance
* [[W](https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science))] Covariance
* [[W](https://en.wikipedia.org/wiki/Dependency_inversion_principle)] Dependency Inversion Principle
* [[W](https://en.wikipedia.org/wiki/Encapsulation_(computer_programming))] Encapsulation
* [[W](https://en.wikipedia.org/wiki/Factory_(object-oriented_programming))] Factory
* [[W](https://en.wikipedia.org/wiki/Final_(Java))] `final`
* [[W](https://en.wikipedia.org/wiki/Generics_in_Java)] Generics
* [[W](https://en.wikipedia.org/wiki/Graphical_user_interface)] Graphical User Interface (GUI)
* [[W](https://en.wikipedia.org/wiki/Immutable_object)] Immutable Object
* [[W](https://en.wikipedia.org/wiki/Inheritance_(object-oriented_programming))] Inheritance
* [[W](https://en.wikipedia.org/wiki/Instance_(computer_science))] Instance
* [[W](https://en.wikipedia.org/wiki/Interface_(object-oriented_programming))] Interface
* [[W](https://en.wikipedia.org/wiki/Java_(programming_language))] Java
* [[W](https://en.wikipedia.org/wiki/Java_annotation)] Java Annotation
* [[W](https://en.wikipedia.org/wiki/List_of_Java_APIs)] Java API
* [[W](https://en.wikipedia.org/wiki/Java_applet)] Java Applet
* [[W](https://en.wikipedia.org/wiki/JAR_(file_format))] Java Archive (JAR)
* [[W](https://en.wikipedia.org/wiki/Java_Class_Library)] Java Class Library (JCL)
* [[W](https://en.wikipedia.org/wiki/Java_collections_framework)] Java Collection Framework (JCF)
* [[W](https://en.wikipedia.org/wiki/Java_Community_Process)] Java Community process (JCP)
* [[W](https://en.wikipedia.org/wiki/Java_compiler)] Java Compiler
* [[W](https://en.wikipedia.org/wiki/Java_concurrency)] Java Concurrency
* [[W](https://en.wikipedia.org/wiki/Criticism_of_Java)] Java criticism
* [[W](https://en.wikipedia.org/wiki/Java_Development_Kit)] Java Development Kit (JDK)
* [[W](https://en.wikipedia.org/wiki/Interface_(Java))] Java Interface
* [[W](https://en.wikipedia.org/wiki/Java_Modeling_Language)] Java Modeling Language (JML)
* [[W](https://en.wikipedia.org/wiki/Java_package)] Java Package
* [[W](https://en.wikipedia.org/wiki/Java_(software_platform))] Java Platform
* [[W](https://en.wikipedia.org/wiki/Java_Platform,_Standard_Edition)] Java Platform, Standard Edition (Java SE)
* [[W](https://en.wikipedia.org/wiki/Java_syntax)] Java Syntax
* [[W](https://en.wikipedia.org/wiki/Java_version_history)] Java version history
* [[W](https://en.wikipedia.org/wiki/Java_virtual_machine)] Java Virtual Machine (JVM)
* [[W](https://en.wikipedia.org/wiki/Javac)] javac
* [[W](https://en.wikipedia.org/wiki/Javadoc)] Javadoc
* [[W](https://en.wikipedia.org/wiki/Member_variable)] Member Variable
* [[W](https://en.wikipedia.org/wiki/Method_(computer_programming))] Method
* [[W](https://en.wikipedia.org/wiki/Method_overriding)] Method Overriding
* [[W](https://en.wikipedia.org/wiki/Mixin)] Mixin
* [[W](https://en.wikipedia.org/wiki/Multiple_inheritance)] Multiple Inheritance
* [[W](https://en.wikipedia.org/wiki/Multitier_architecture)] Multi Tier Architecture
* [[W](https://en.wikipedia.org/wiki/Object_(computer_science))] Object
* [[W](https://en.wikipedia.org/wiki/Object-oriented_programming)] Object-Oriented Programming
* [[W](https://en.wikipedia.org/wiki/Object_composition)] Object Composition
* [[W](https://en.wikipedia.org/wiki/Object_lifetime)] Object Lifetime
* [[W](https://en.wikipedia.org/wiki/Object_pool_pattern)] Object Pool Pattern
* [[W](https://en.wikipedia.org/wiki/OpenJDK)] OpenJDK
* [[W](https://en.wikipedia.org/wiki/Oracle_Corporation)] Oracle Corporation
* [[W](https://en.wikipedia.org/wiki/Polymorphism_(computer_science))] Polymorphism
* [[W](https://en.wikipedia.org/wiki/Prepared_statement)] Prepared Statement
* [W] Project Object Model (POM)
* [[W](https://en.wikipedia.org/wiki/Property_(programming))] Property
* [[W](https://en.wikipedia.org/wiki/Prototype-based_programming)] Prototype-Based Programming
* [[W](https://en.wikipedia.org/wiki/Reflective_programming)] Reflective Programming
* [[W](https://en.wikipedia.org/wiki/Scala_(programming_language))] Scala
* [[W](https://en.wikipedia.org/wiki/Static_variable)] Static Variable
* [[W](https://en.wikipedia.org/wiki/Sun_Microsystems)] Sun Microsystems, Inc.
* [[W](https://en.wikipedia.org/wiki/Trait_(computer_programming))] Trait
* [[W](https://en.wikipedia.org/wiki/Graphical_widget)] Widget
* [[W](https://en.wikipedia.org/wiki/Windowing_system)] Windowing System
* [[W](https://en.wikipedia.org/wiki/WIMP_(computing))] Windows, Icons, Menus, Pointers (WIMP)
* [[W](https://en.wikipedia.org/wiki/Primitive_wrapper_class_in_Java)] Wrapper Class
* [[W](https://en.wikipedia.org/wiki/Write_once,_run_anywhere)] Write Once, Run Anywhere (WORA)

---

### Based on

Deitel, Paul & Harvey Deitel. (2017). _Java How to Program, Early Objects_. 11th Ed. [Home](https://deitel.com/java-how-to-program-11-e-early-objects-version/). [GitHub](https://github.com/pdeitel/JavaHowToProgram11e_EarlyObjects).

Verbanec, Al. (2023). CMPSC 221 Object-Oriented Programming with Web-Based Applications. The Pennsylvania State University.