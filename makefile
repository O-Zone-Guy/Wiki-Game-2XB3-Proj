########################################
# Author:		Omar Alkersh 					     #
# Revised:	Thursday, 12th March, 2020 #
# Description: "MAKEFILE"              #
########################################

# All required libraries are should be present in lib/ directory

JFLAGS = -g
# JCLASS = -cp ./src:.:./lib/sqlite-jdbc-3.30.1.jar
JCLASS = -cp ./src:./src/tests:.:/usr/share/java/junit4-4.12.jar:./lib/sqlite-jdbc-3.30.1.jar
JC = javac
JVM = java

# not sure entirely what this does but seems important
.SUFFIXES: .java .class

# rule to convert .java to .class. Compile java files
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

# need to list all our java source files
CLASSES= src/cas/xb3/proj/alg/SQLHandler.java \
         src/cas/xb3/proj/NodeT.java \
         src/cas/xb3/proj/PathT.java \
         src/cas/xb3/proj/alg/Algorithms.java \
         src/cas/xb3/proj/UI/UInterface.java \
         src/cas/xb3/proj/UI/UIResults.java \

# MIKE: test files
TESTS= src/tests/TestSQLHandler.java \
			 src/tests/AllTests.java \
			src/tests/TestNodeT.java \

# sets default entry for makefile
default: classes

classes: $(CLASSES:.java=.class)

run: src/cas/xb3/proj/UI/UInterface.class
	$(JVM) $(JCLASS) cas.xb3.proj.UI.UInterface

# MIKE: run tests
tests: $(TESTS:.java=.class)

test: src/tests/AllTests.class
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore tests.AllTests

# consider using doxygen
docs:
	doxygen Doxyfile

# MIKE: temp rule for database testing
# dbtest: src/SQLHandler.class
# 	$(JVM) $(JCLASS) SQLHandler

# # MIKE: app testing
# app: src/UInterface.class
# 	$(JVM) $(JCLASS) UInterface

# cleans project
clean:
	find . -iname "*.class" -exec rm -rf {} \;
	find . -iname "*\~" -exec rm {} \;
	rm -rf html
	rm -rf latex
	rm -f plan.tex
