Name = ProjetIHM

UNAME := $(shell uname)

ifeq ($(UNAME), Linux)
JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp" -cp ".:/mariadb-client.jar"
else
JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp" -cp ".;/mariadb-client.jar"
endif

SRC = src/fr/iutfbleau/projetIHM2020FI2/
BUILD = build/fr/iutfbleau/projetIHM2020FI2/
JC = javac 

allclass = \
	$(BUILD)API/Activable.class \
	$(BUILD)API/ContientTrucs.class \
	$(BUILD)API/Descriptible.class \
	$(BUILD)API/Direction.class \
	$(BUILD)API/EtatPassage.class \
	$(BUILD)API/Joueur.class \
	$(BUILD)API/Passage.class \
	$(BUILD)API/PassagePieceFactory.class \
	$(BUILD)API/Piece.class \
	$(BUILD)API/Truc.class \
	$(BUILD)API/TrucFactory.class \
	$(BUILD)API/TypeTruc.class \
	$(BUILD)MNP/ContientTrucsNP.class \
	$(BUILD)MNP/JoueurNP.class \
	$(BUILD)MNP/PassageNP.class \
	$(BUILD)MNP/PassagePieceFactoryNP.class \
	$(BUILD)MNP/PieceNP.class \
	$(BUILD)MNP/TrucFactoryNP.class \
	$(BUILD)MNP/TrucNP.class \
	$(BUILD)TEST/TestTexteMNP.class \
	$(BUILD)TEST/testVUE.class \


default: $(allclass)

$(BUILD)TEST/TestTexteMNP.class: $(SRC)TEST/TestTexteMNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Piece.class $(BUILD)API/Passage.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)TEST/TestTexteMNP.java

$(BUILD)API/Activable.class: $(SRC)API/Activable.java 
	$(JC) $(JFLAGS) $(SRC)API/Activable.java

$(BUILD)API/ContientTrucs.class: $(SRC)API/ContientTrucs.java
	$(JC) $(JFLAGS) $(SRC)API/ContientTrucs.java

$(BUILD)API/Descriptible.class: $(SRC)API/Descriptible.java
	$(JC) $(JFLAGS) $(SRC)API/Descriptible.java

$(BUILD)API/Direction.class: $(SRC)API/Direction.java
	$(JC) $(JFLAGS) $(SRC)API/Direction.java

$(BUILD)API/EtatPassage.class: $(SRC)API/EtatPassage.java $(BUILD)API/Descriptible.class
	$(JC) $(JFLAGS) $(SRC)API/EtatPassage.java

$(BUILD)API/Joueur.class: $(SRC)API/Joueur.java 
	$(JC) $(JFLAGS) $(SRC)API/Joueur.java

$(BUILD)API/Passage.class: $(SRC)API/Passage.java $(BUILD)API/Activable.class $(BUILD)API/Descriptible.class $(BUILD)API/EtatPassage.class
	$(JC) $(JFLAGS) $(SRC)API/Passage.java

$(BUILD)API/PassagePieceFactory.class: $(SRC)API/PassagePieceFactory.java $(BUILD)API/Direction.class $(BUILD)API/Piece.class
	$(JC) $(JFLAGS) $(SRC)API/PassagePieceFactory.java

$(BUILD)API/Piece.class: $(SRC)API/Piece.java $(BUILD)API/Descriptible.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Direction.class $(BUILD)API/Passage.class
	$(JC) $(JFLAGS) $(SRC)API/Piece.java

$(BUILD)API/Truc.class: $(SRC)API/Truc.java $(BUILD)API/Descriptible.class $(BUILD)API/Activable.class
	$(JC) $(JFLAGS) $(SRC)API/Truc.java

$(BUILD)API/TrucFactory.class: $(SRC)API/TrucFactory.java  $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/TypeTruc.class
	$(JC) $(JFLAGS) $(SRC)API/TrucFactory.java

$(BUILD)API/TypeTruc.class: $(SRC)API/TypeTruc.java $(BUILD)API/Descriptible.class
	$(JC) $(JFLAGS) $(SRC)API/TypeTruc.java

$(BUILD)MNP/ContientTrucsNP.class: $(SRC)MNP/ContientTrucsNP.java $(BUILD)API/ContientTrucs.class $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)MNP/ContientTrucsNP.java

$(BUILD)MNP/JoueurNP.class: $(SRC)MNP/JoueurNP.java $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Joueur.class $(BUILD)API/Piece.class
	$(JC) $(JFLAGS) $(SRC)MNP/JoueurNP.java

$(BUILD)MNP/PassageNP.class: $(SRC)MNP/PassageNP.java $(BUILD)API/Passage.class $(BUILD)API/Piece.class $(BUILD)API/EtatPassage.class
	$(JC) $(JFLAGS) $(SRC)MNP/PassageNP.java

$(BUILD)MNP/PassagePieceFactoryNP.class: $(SRC)MNP/PassagePieceFactoryNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Passage.class $(BUILD)API/Piece.class
	$(JC) $(JFLAGS) $(SRC)MNP/PassagePieceFactoryNP.java

$(BUILD)MNP/PieceNP.class: $(SRC)MNP/PieceNP.java $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Piece.class $(BUILD)API/Direction.class $(BUILD)API/Passage.class
	$(JC) $(JFLAGS) $(SRC)MNP/PieceNP.java

$(BUILD)MNP/TrucFactoryNP.class: $(SRC)MNP/TrucFactoryNP.java $(BUILD)API/TrucFactory.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucFactoryNP.java

$(BUILD)MNP/TrucNP.class: $(SRC)MNP/TrucNP.java $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucNP.java

run : 
	java -classpath build fr.iutfbleau.projetIHM2020FI2.TEST.TestTexteMNP

jar:
	jar cvfe $(Name).jar fr.iutfbleau.projetIHM2020FI2.TEST.TestTexteMNP -C build fr -C res images

runjar:
	java -jar $(Name).jar

clean:
	rm -rf $(BUILD)

.PHONY : but clean