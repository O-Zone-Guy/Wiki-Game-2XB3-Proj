########################################
# Author:		Omar Alkersh 					     #
# Revised:	Thursday, 12th March, 2020 #
# Description: "MAKEFILE"              #
########################################

# All required libraries are should be present in lib/ directory

JFLAGS = -g
JCLASS = -cp ./src:.:./lib/sqlite-jdbc-3.30.1.jar
JC = javac
JVM = java

# not sure entirely what this does but seems important
.SUFFIXES: .java .class

# rule to convert .java to .class. Compile java files
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

# need to list all our java source files
CLASSES= src/SQLHandler.java \
         src/NodeT.java \
         src/PathT.java \
         src/Algorithms.java \
#         src/UInterface.java \


# sets default entry for makefile
default: classes

classes: $(CLASSES:.java=.class)

# consider using doxygen
#docs:
#	doxygen Doxyfile

# add a rule for testing
#test: src/SQLHandler.class
#	$(JVM) $(JCLASS) SQLHandler

# cleans project
clean:
	find . -iname "*.class" -exec rm -rf {} \;
	find . -iname "*\~" -exec rm {} \;
	rm -rf html
	rm -rf latex
	rm plan.tex
