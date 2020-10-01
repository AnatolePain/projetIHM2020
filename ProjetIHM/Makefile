Name = Main

JFLAGS = -implicit:none -d build -classpath build -sourcepath "src"
SRC = src/fr/iutfbleau/projetIHM2020FI2/
BUILD = build/fr/iutfbleau/projetIHM2020FI2/
JC = javac


clean:
	$(RM) *.class

run : 
	java -classpath build fr.iutfbleau.projetIHM2020FI2.test.TestTexteMNP

jar:
	jar cvfe $(Name).jar fr.iutfbleau.projetIHM2020FI2.test.TestTexteMNP -C build fr -C res images

runjar:
	java -jar $(Name).jar
clean:
	rm -Rf $(BUILD)*

.PHONY : but clean