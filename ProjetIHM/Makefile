Name = ProjetIHM

UNAME := $(shell uname)

ifeq ($(UNAME), Linux)
DBFLAGS = "mariadb-client.jar:."
else
DBFLAGS = "mariadb-client.jar;."
endif

JAVAUNIT = "junit-4.10.jar" org.junit.runner.JUnitCore
JUFLAGS = "junit-4.10.jar"
JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp"
SRC = src/fr/iutfbleau/projetIHM2020FI2/
BUILD = build/fr/iutfbleau/projetIHM2020FI2/
JC = javac 

allclass = \
	$(BUILD)VUE/DialogDescription.class \
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
	$(BUILD)VUE/Window.class \
	$(BUILD)VUE/TransitionThread.class \
	$(BUILD)CONTROLEUR/DescriptionEvent.class \
	$(BUILD)VUE/ImageDonjon.class \
	$(BUILD)VUE/PieceVue.class \
	$(BUILD)VUE/PieceMenuContextuel.class \
	$(BUILD)VUE/PieceContenueVue.class \
	$(BUILD)VUE/PanelNom.class \
	$(BUILD)VUE/MiniCarteVue.class \
	$(BUILD)VUE/InventaireMenuContextuel.class \
	$(BUILD)VUE/CaseInventaire.class \
	$(BUILD)VUE/InventaireContenueVue.class \
	$(BUILD)VUE/GameVue.class \
	$(BUILD)VUE/ChangementThread.class \
	$(BUILD)VUE/CelluleCarte.class \
	$(BUILD)VUE/CasePiece.class \
	$(BUILD)VUE/ImageClassLoader.class \
	$(BUILD)MODEL/ConnectionBD.class \
	$(BUILD)test/TestBaseDeDonne.class \
	$(BUILD)CONTROLEUR/CaseInventaireEvent.class \
	$(BUILD)CONTROLEUR/CasePieceEvent.class \
	$(BUILD)CONTROLEUR/EventPV.class \
	$(BUILD)CONTROLEUR/GestionClavier.class \
	$(BUILD)test/Main.class \
	$(BUILD)test/TestTexteMNP.class

allTest = \
	TestBaseDeDonne

default: $(allclass)

$(BUILD)VUE/ImageClassLoader.class: $(SRC)VUE/ImageClassLoader.java
	$(JC) $(JFLAGS) $(SRC)VUE/ImageClassLoader.java

$(BUILD)test/TestBaseDeDonne.class: $(SRC)test/TestBaseDeDonne.java $(BUILD)MODEL/ConnectionBD.class
	$(JC) $(JFLAGS) -cp $(JUFLAGS) $(SRC)test/TestBaseDeDonne.java

$(BUILD)test/Main.class: $(SRC)test/Main.java $(BUILD)VUE/Window.class
	$(JC) $(JFLAGS) $(SRC)test/Main.java

$(BUILD)VUE/GameVue.class: $(SRC)VUE/GameVue.java $(BUILD)VUE/PieceVue.class $(BUILD)VUE/PieceContenueVue.class  $(BUILD)VUE/MiniCarteVue.class $(BUILD)VUE/DialogDescription.class $(BUILD)VUE/InventaireContenueVue.class $(BUILD)API/TypeTruc.class $(BUILD)VUE/ImageClassLoader.class
	$(JC) $(JFLAGS) $(SRC)VUE/GameVue.java

$(BUILD)CONTROLEUR/GestionClavier.class: $(SRC)CONTROLEUR/GestionClavier.java
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/GestionClavier.java

$(BUILD)CONTROLEUR/EventPV.class: $(SRC)CONTROLEUR/EventPV.java $(BUILD)VUE/PieceVue.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/EventPV.java

$(BUILD)CONTROLEUR/DescriptionEvent.class: $(SRC)CONTROLEUR/DescriptionEvent.java $(BUILD)VUE/DialogDescription.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/DescriptionEvent.java

$(BUILD)CONTROLEUR/CasePieceEvent.class: $(SRC)CONTROLEUR/CasePieceEvent.java $(BUILD)VUE/CasePiece.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/CasePieceEvent.java

$(BUILD)CONTROLEUR/CaseInventaireEvent.class: $(SRC)CONTROLEUR/CaseInventaireEvent.java $(BUILD)VUE/CaseInventaire.class $(BUILD)VUE/InventaireMenuContextuel.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/CaseInventaireEvent.java

$(BUILD)MODEL/ConnectionBD.class: $(SRC)MODEL/ConnectionBD.java
	$(JC) $(JFLAGS) $(SRC)MODEL/ConnectionBD.java

$(BUILD)VUE/CaseInventaire.class: $(SRC)VUE/CaseInventaire.java $(BUILD)VUE/InventaireMenuContextuel.class $(BUILD)API/TypeTruc.class
	$(JC) $(JFLAGS) $(SRC)VUE/CaseInventaire.java

$(BUILD)VUE/CasePiece.class: $(SRC)VUE/CasePiece.java $(BUILD)VUE/PieceMenuContextuel.class
	$(JC) $(JFLAGS) $(SRC)VUE/CasePiece.java

$(BUILD)VUE/CelluleCarte.class: $(SRC)VUE/CelluleCarte.java
	$(JC) $(JFLAGS) $(SRC)VUE/CelluleCarte.java

$(BUILD)VUE/ChangementThread.class: $(SRC)VUE/ChangementThread.java
	$(JC) $(JFLAGS) $(SRC)VUE/ChangementThread.java

$(BUILD)VUE/DialogDescription.class: $(SRC)VUE/DialogDescription.java
	$(JC) $(JFLAGS) $(SRC)VUE/DialogDescription.java 

$(BUILD)VUE/ImageDonjon.class: $(SRC)VUE/ImageDonjon.java
	$(JC) $(JFLAGS) $(SRC)VUE/ImageDonjon.java

$(BUILD)VUE/InventaireContenueVue.class: $(SRC)VUE/InventaireContenueVue.java $(BUILD)VUE/PanelNom.class $(BUILD)VUE/CaseInventaire.class $(BUILD)CONTROLEUR/CaseInventaireEvent.class $(BUILD)VUE/ImageClassLoader.class
	$(JC) $(JFLAGS) $(SRC)VUE/InventaireContenueVue.java

$(BUILD)VUE/InventaireMenuContextuel.class: $(SRC)VUE/InventaireMenuContextuel.java $(BUILD)CONTROLEUR/DescriptionEvent.class $(BUILD)VUE/DialogDescription.class 
	$(JC) $(JFLAGS) $(SRC)VUE/InventaireMenuContextuel.java

$(BUILD)VUE/MiniCarteVue.class: $(SRC)VUE/MiniCarteVue.java $(BUILD)VUE/CelluleCarte.class
	$(JC) $(JFLAGS) $(SRC)VUE/MiniCarteVue.java $(SRC)CONTROLEUR/GestionClavier.java

$(BUILD)VUE/PanelNom.class: $(SRC)VUE/PanelNom.java $(BUILD)VUE/CaseInventaire.class
	$(JC) $(JFLAGS) $(SRC)VUE/PanelNom.java

$(BUILD)VUE/PieceContenueVue.class: $(SRC)VUE/PieceContenueVue.java $(BUILD)VUE/PanelNom.class $(BUILD)CONTROLEUR/CasePieceEvent.class $(BUILD)VUE/CaseInventaire.class $(BUILD)VUE/CasePiece.class
	$(JC) $(JFLAGS) $(SRC)VUE/PieceContenueVue.java

$(BUILD)VUE/PieceMenuContextuel.class: $(SRC)VUE/PieceMenuContextuel.java
	$(JC) $(JFLAGS) $(SRC)VUE/PieceMenuContextuel.java

$(BUILD)VUE/PieceVue.class: $(SRC)VUE/PieceVue.java $(BUILD)VUE/ImageDonjon.class
	$(JC) $(JFLAGS) $(SRC)VUE/PieceVue.java $(SRC)CONTROLEUR/EventPV.java $(SRC)VUE/TransitionThread.java $(SRC)VUE/ChangementThread.java 

$(BUILD)VUE/TransitionThread.class: $(SRC)VUE/TransitionThread.java
	$(JC) $(JFLAGS) $(SRC)VUE/TransitionThread.java

$(BUILD)VUE/Window.class: $(SRC)VUE/Window.java $(BUILD)VUE/GameVue.class
	$(JC) $(JFLAGS) $(SRC)VUE/Window.java

$(BUILD)test/testVUE.class: $(SRC)test/testVUE.java
	$(JC) $(JFLAGS) $(SRC)test/testVUE.java

$(BUILD)test/TestTexteMNP.class: $(SRC)test/TestTexteMNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Piece.class $(BUILD)API/Passage.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)test/TestTexteMNP.java

$(BUILD)API/Activable.class: $(SRC)API/Activable.java
	$(JC) $(JFLAGS) $(SRC)API/Activable.java $(SRC)API/Truc.java $(SRC)API/Descriptible.java $(SRC)API/TypeTruc.java

$(BUILD)API/ContientTrucs.class: $(SRC)API/ContientTrucs.java
	$(JC) $(JFLAGS) $(SRC)API/ContientTrucs.java

$(BUILD)API/Descriptible.class: $(SRC)API/Descriptible.java
	$(JC) $(JFLAGS) $(SRC)API/Descriptible.java

$(BUILD)API/Direction.class: $(SRC)API/Direction.java
	$(JC) $(JFLAGS) $(SRC)API/Direction.java

$(BUILD)API/EtatPassage.class: $(SRC)API/EtatPassage.java $(BUILD)API/Descriptible.class
	$(JC) $(JFLAGS) $(SRC)API/EtatPassage.java

$(BUILD)API/Joueur.class: $(SRC)API/Joueur.java $(BUILD)API/Piece.class
	$(JC) $(JFLAGS) $(SRC)API/Joueur.java

$(BUILD)API/Passage.class: $(SRC)API/Passage.java $(BUILD)API/Activable.class $(BUILD)API/Descriptible.class $(BUILD)API/EtatPassage.class
	$(JC) $(JFLAGS) $(SRC)API/Passage.java $(SRC)API/Piece.java

$(BUILD)API/PassagePieceFactory.class: $(SRC)API/PassagePieceFactory.java $(BUILD)API/Direction.class $(BUILD)API/Piece.class
	$(JC) $(JFLAGS) $(SRC)API/PassagePieceFactory.java

$(BUILD)API/Piece.class: $(SRC)API/Piece.java $(BUILD)API/Descriptible.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Direction.class $(BUILD)API/Passage.class
	$(JC) $(JFLAGS) $(SRC)API/Piece.java

$(BUILD)API/Truc.class: $(SRC)API/Truc.java $(BUILD)API/Descriptible.class $(BUILD)API/Activable.class $(BUILD)API/TypeTruc.class
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

$(BUILD)MNP/PassagePieceFactoryNP.class: $(SRC)MNP/PassagePieceFactoryNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Passage.class $(BUILD)API/Piece.class $(BUILD)MNP/PieceNP.class
	$(JC) $(JFLAGS) $(SRC)MNP/PassagePieceFactoryNP.java

$(BUILD)MNP/PieceNP.class: $(SRC)MNP/PieceNP.java $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Piece.class $(BUILD)API/Direction.class $(BUILD)API/Passage.class
	$(JC) $(JFLAGS) $(SRC)MNP/PieceNP.java

$(BUILD)MNP/TrucFactoryNP.class: $(SRC)MNP/TrucFactoryNP.java $(BUILD)API/TrucFactory.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Truc.class $(BUILD)MNP/TrucNP.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucFactoryNP.java

$(BUILD)MNP/TrucNP.class: $(SRC)MNP/TrucNP.java $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucNP.java

runtextuel : 
	java -classpath build fr.iutfbleau.projetIHM2020FI2.test.TestTexteMNP

run : 
	java -cp $(DBFLAGS) -classpath build fr.iutfbleau.projetIHM2020FI2.test.Main

jar:
	jar cvfe $(Name).jar fr.iutfbleau.projetIHM2020FI2.test.Main -C build fr -C res images

runjar:
	java -cp $(DBFLAGS) -jar $(Name).jar

runtest :
	java -classpath build -cp $(JAVAUNIT) fr.iutfbleau.projetIHM2020FI2.test.$(allTest)

clean:
	rm -rf $(BUILD)

.PHONY : but clean