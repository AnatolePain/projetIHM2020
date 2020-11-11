Name = ProjetIHM

UNAME := $(shell uname)

ifeq ($(UNAME), Linux)
DBFLAGS = -classpath "build:mariadb-client.jar"
JAVAUNIT = -classpath "build:mariadb-client.jar:junit-4.10.jar:hamcrest-core.jar" org.junit.runner.JUnitCore
JUFLAGS = -implicit:none -d build -classpath "build:junit-4.10.jar" -sourcepath "src:tmp"
else
DBFLAGS = -classpath "build;mariadb-client.jar;"
JAVAUNIT = -classpath "build;mariadb-client.jar;junit-4.10.jar;hamcrest-core.jar;" org.junit.runner.JUnitCore
JUFLAGS = -implicit:none -d build -classpath "build;junit-4.10.jar;" -sourcepath "src:tmp"
endif

JFLAGS = -implicit:none -d build -classpath build -sourcepath "src:tmp"
SRC = src/fr/iutfbleau/projetIHM2020FI2/
BUILD = build/fr/iutfbleau/projetIHM2020FI2/
JC = javac 
JTEST = java $(JAVAUNIT) fr.iutfbleau.projetIHM2020FI2.test.

allclass = \
	$(BUILD)test/Main.class \
	$(BUILD)test/TestBaseDeDonne.class \
	$(BUILD)test/TestTexteBD.class \
	$(BUILD)test/TestTexteMNP.class \
#  	$(BUILD)VUE/DialogDescription.class \
#  	$(BUILD)API/Activable.class \
#  	$(BUILD)API/ContientTrucs.class \
#  	$(BUILD)API/Descriptible.class \
#  	$(BUILD)API/Direction.class \
#  	$(BUILD)API/EtatPassage.class \
#  	$(BUILD)API/Joueur.class \
#  	$(BUILD)API/Passage.class \
#  	$(BUILD)API/PassagePieceFactory.class \
#  	$(BUILD)API/Piece.class \
#  	$(BUILD)API/Truc.class \
#  	$(BUILD)API/TrucFactory.class \
#  	$(BUILD)API/TypeTruc.class \
#  	$(BUILD)MNP/ContientTrucsNP.class \
#  	$(BUILD)MNP/JoueurNP.class \
#  	$(BUILD)MNP/PassageNP.class \
#  	$(BUILD)MNP/PassagePieceFactoryNP.class \
#  	$(BUILD)MNP/PieceNP.class \
#  	$(BUILD)MNP/TrucFactoryNP.class \
#  	$(BUILD)MNP/TrucNP.class \
#  	$(BUILD)VUE/Window.class \
#  	$(BUILD)VUE/TransitionThread.class \
#  	$(BUILD)CONTROLEUR/DescriptionEvent.class \
#  	$(BUILD)VUE/ImageDonjon.class \
#  	$(BUILD)VUE/PieceVue.class \
#  	$(BUILD)VUE/PieceMenuContextuel.class \
#  	$(BUILD)VUE/PieceContenuVue.class \
#  	$(BUILD)VUE/PanelNom.class \
#  	$(BUILD)VUE/MiniCarteVue.class \
#  	$(BUILD)VUE/InventaireMenuContextuel.class \
#  	$(BUILD)VUE/CaseInventaire.class \
#  	$(BUILD)VUE/InventaireContenuVue.class \
#  	$(BUILD)VUE/GameVue.class \
#  	$(BUILD)VUE/ChangementThread.class \
#  	$(BUILD)VUE/CelluleCarte.class \
#  	$(BUILD)VUE/CasePiece.class \
#  	$(BUILD)VUE/ImageClassLoader.class \
#  	$(BUILD)MODEL/ConnectionBD.class \
#  	$(BUILD)MODEL/GameStart.class \
#  	$(BUILD)MODEL/IDObject.class \
#  	$(BUILD)MODEL/GestionIDBD.class \
#  	$(BUILD)test/TestBaseDeDonne.class \
#  	$(BUILD)CONTROLEUR/CaseInventaireEvent.class \
#  	$(BUILD)CONTROLEUR/CasePieceEvent.class \
#  	$(BUILD)CONTROLEUR/EventPV.class \
#  	$(BUILD)test/TestTexteMNP.class \
#  	$(BUILD)test/TestTexteBD.class \
#  	$(BUILD)test/TestVUEMiniCarte.class \
#  	$(BUILD)MODEL/JoueurBD.class \
#  	$(BUILD)MODEL/ContientTrucsBD.class \
#  	$(BUILD)MODEL/PassageBD.class \
#  	$(BUILD)MODEL/PassagePieceFactoryBD.class \
#  	$(BUILD)MODEL/PieceBD.class \
#  	$(BUILD)MODEL/TrucBD.class \
#  	$(BUILD)MODEL/TrucFactoryBD.class \
#  	$(BUILD)CONTROLEUR/SetupModel.class \
#  	$(BUILD)CONTROLEUR/MiniCarteEvent.class \
#  	$(BUILD)VUE/ChargeCarte.class \
#  	$(BUILD)CONTROLEUR/PieceContenuEvent.class \
#  	$(BUILD)CONTROLEUR/InventaireController.class \
#  	$(BUILD)CONTROLEUR/PieceController.class \

allTest = \
	$(JTEST)TestBaseDeDonne; \
	$(JTEST)TestVUEMiniCarte;

default: $(allclass)

$(BUILD)MODEL/GameStart.class: $(SRC)MODEL/GameStart.java $(BUILD)MODEL/JoueurBD.class
	$(JC) $(JFLAGS)  $(SRC)MODEL/GameStart.java

$(BUILD)MODEL/IDObject.class: $(SRC)MODEL/IDObject.java
	$(JC) $(JFLAGS)  $(SRC)MODEL/IDObject.java

$(BUILD)MODEL/GestionIDBD.class: $(SRC)MODEL/GestionIDBD.java $(BUILD)MODEL/IDObject.class
	$(JC) $(JFLAGS)  $(SRC)MODEL/GestionIDBD.java

$(BUILD)MODEL/PassageBD.class: $(SRC)MODEL/PassageBD.java
	$(JC) $(JFLAGS)  $(SRC)MODEL/PassageBD.java

$(BUILD)MODEL/PassagePieceFactoryBD.class: $(SRC)MODEL/PassagePieceFactoryBD.java $(BUILD)MODEL/PieceBD.class $(BUILD)MODEL/PassageBD.class
	$(JC) $(JFLAGS) $(SRC)MODEL/PassagePieceFactoryBD.java

$(BUILD)MODEL/PieceBD.class: $(SRC)MODEL/PieceBD.java $(BUILD)MODEL/GameStart.class
	$(JC) $(JFLAGS) $(SRC)MODEL/PieceBD.java

$(BUILD)MODEL/TrucBD.class: $(SRC)MODEL/TrucBD.java
	$(JC) $(JFLAGS) $(SRC)MODEL/TrucBD.java

$(BUILD)MODEL/TrucFactoryBD.class: $(SRC)MODEL/TrucFactoryBD.java $(BUILD)MODEL/TrucBD.class
	$(JC) $(JFLAGS) $(SRC)MODEL/TrucFactoryBD.java

$(BUILD)MODEL/ContientTrucsBD.class: $(SRC)MODEL/ContientTrucsBD.java $(BUILD)API/ContientTrucs.class $(BUILD)API/Truc.class $(BUILD)MODEL/GestionIDBD.class
	$(JC) $(JFLAGS) $(SRC)MODEL/ContientTrucsBD.java $(SRC)MODEL/PieceBD.java $(SRC)MODEL/JoueurBD.java $(SRC)MODEL/GameStart.java

$(BUILD)MODEL/JoueurBD.class: $(SRC)MODEL/JoueurBD.java $(BUILD)MODEL/ConnectionBD.class $(BUILD)API/Piece.class $(BUILD)MODEL/ContientTrucsBD.class
	$(JC) $(JFLAGS) $(SRC)MODEL/JoueurBD.java $(SRC)MODEL/GameStart.java

$(BUILD)VUE/ImageClassLoader.class: $(SRC)VUE/ImageClassLoader.java
	$(JC) $(JFLAGS) $(SRC)VUE/ImageClassLoader.java

$(BUILD)test/TestBaseDeDonne.class: $(SRC)test/TestBaseDeDonne.java $(BUILD)MODEL/ConnectionBD.class
	$(JC) $(JUFLAGS) $(SRC)test/TestBaseDeDonne.java

$(BUILD)test/Main.class: $(SRC)test/Main.java $(BUILD)VUE/Window.class $(BUILD)CONTROLEUR/SetupController.class $(BUILD)CONTROLEUR/SetupModel.class
	$(JC) $(JFLAGS) $(SRC)test/Main.java

$(BUILD)VUE/GameVue.class: $(SRC)VUE/GameVue.java $(BUILD)VUE/PieceVue.class $(BUILD)VUE/PieceContenuVue.class  $(BUILD)VUE/MiniCarteVue.class $(BUILD)VUE/DialogDescription.class $(BUILD)VUE/InventaireContenuVue.class $(BUILD)API/TypeTruc.class $(BUILD)VUE/ImageClassLoader.class 
	$(JC) $(JFLAGS) $(SRC)VUE/GameVue.java

#$(BUILD)CONTROLEUR/GestionClavier.class: $(SRC)CONTROLEUR/GestionClavier.java
#	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/GestionClavier.java

$(BUILD)CONTROLEUR/MiniCarteEvent.class: $(SRC)CONTROLEUR/MiniCarteEvent.java $(BUILD)VUE/PieceVue.class $(BUILD)VUE/MiniCarteVue.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/MiniCarteEvent.java

$(BUILD)CONTROLEUR/EventPV.class: $(SRC)CONTROLEUR/EventPV.java $(BUILD)VUE/PieceVue.class $(BUILD)API/Joueur.class $(BUILD)API/Passage.class $(BUILD)VUE/MiniCarteVue.class $(BUILD)API/TypeTruc.class $(BUILD)API/Truc.class $(BUILD)API/ContientTrucs.class $(BUILD)CONTROLEUR/SetupModel.class  $(BUILD)CONTROLEUR/PieceController.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/EventPV.java

$(BUILD)CONTROLEUR/DescriptionEvent.class: $(SRC)CONTROLEUR/DescriptionEvent.java $(BUILD)VUE/DialogDescription.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/DescriptionEvent.java

$(BUILD)CONTROLEUR/CasePieceEvent.class: $(SRC)CONTROLEUR/CasePieceEvent.java $(BUILD)VUE/CasePiece.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/CasePieceEvent.java

$(BUILD)CONTROLEUR/CaseInventaireEvent.class: $(SRC)CONTROLEUR/CaseInventaireEvent.java 
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/CaseInventaireEvent.java

$(BUILD)MODEL/ConnectionBD.class: $(SRC)MODEL/ConnectionBD.java
	$(JC) $(JFLAGS) $(SRC)MODEL/ConnectionBD.java

$(BUILD)VUE/CaseInventaire.class: $(SRC)VUE/CaseInventaire.java $(BUILD)VUE/InventaireMenuContextuel.class
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

$(BUILD)VUE/InventaireContenuVue.class: $(SRC)VUE/InventaireContenuVue.java $(BUILD)VUE/PanelNom.class $(BUILD)VUE/CaseInventaire.class $(BUILD)VUE/ImageClassLoader.class
	$(JC) $(JFLAGS) $(SRC)VUE/InventaireContenuVue.java

$(BUILD)VUE/InventaireMenuContextuel.class: $(SRC)VUE/InventaireMenuContextuel.java $(BUILD)VUE/DialogDescription.class 
	$(JC) $(JFLAGS) $(SRC)VUE/InventaireMenuContextuel.java

$(BUILD)VUE/MiniCarteVue.class: $(SRC)VUE/MiniCarteVue.java $(BUILD)VUE/CelluleCarte.class $(BUILD)API/Direction.class
	$(JC) $(JFLAGS) $(SRC)VUE/MiniCarteVue.java $(SRC)CONTROLEUR/MiniCarteEvent.java

$(BUILD)VUE/PanelNom.class: $(SRC)VUE/PanelNom.java 
	$(JC) $(JFLAGS) $(SRC)VUE/PanelNom.java

$(BUILD)VUE/PieceContenuVue.class: $(SRC)VUE/PieceContenuVue.java $(BUILD)VUE/PanelNom.class $(BUILD)VUE/CasePiece.class $(BUILD)VUE/ImageClassLoader.class
	$(JC) $(JFLAGS) $(SRC)VUE/PieceContenuVue.java

$(BUILD)VUE/PieceMenuContextuel.class: $(SRC)VUE/PieceMenuContextuel.java $(BUILD)VUE/DialogDescription.class
	$(JC) $(JFLAGS) $(SRC)VUE/PieceMenuContextuel.java

$(BUILD)VUE/PieceVue.class: $(SRC)VUE/PieceVue.java $(BUILD)VUE/ImageDonjon.class $(BUILD)VUE/ImageClassLoader.class $(BUILD)VUE/MiniCarteVue.class $(BUILD)API/Joueur.class $(BUILD)VUE/PieceContenuVue.class $(BUILD)CONTROLEUR/SetupModel.class 
	$(JC) $(JFLAGS) $(SRC)VUE/PieceVue.java $(SRC)VUE/TransitionThread.java $(SRC)VUE/ChangementThread.java 

$(BUILD)VUE/TransitionThread.class: $(SRC)VUE/TransitionThread.java
	$(JC) $(JFLAGS) $(SRC)VUE/TransitionThread.java

$(BUILD)VUE/Window.class: $(SRC)VUE/Window.java $(BUILD)VUE/GameVue.class
	$(JC) $(JFLAGS) $(SRC)VUE/Window.java

$(BUILD)test/TestVUEMiniCarte.class: $(SRC)test/TestVUEMiniCarte.java
	$(JC) $(JUFLAGS) $(SRC)test/TestVUEMiniCarte.java

$(BUILD)test/TestTexteMNP.class: $(SRC)test/TestTexteMNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Piece.class $(BUILD)API/Passage.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)test/TestTexteMNP.java

$(BUILD)test/TestTexteBD.class: $(SRC)test/TestTexteBD.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Piece.class $(BUILD)API/Passage.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class $(BUILD)MODEL/PassagePieceFactoryBD.class $(BUILD)MODEL/TrucFactoryBD.class $(BUILD)MODEL/JoueurBD.class
	$(JC) $(JFLAGS) $(SRC)test/TestTexteBD.java

$(BUILD)API/Activable.class: $(SRC)API/Activable.java
	$(JC) $(JFLAGS) $(SRC)API/Activable.java $(SRC)API/Truc.java $(SRC)API/Descriptible.java $(SRC)API/TypeTruc.java

$(BUILD)API/ContientTrucs.class: $(SRC)API/ContientTrucs.java $(BUILD)API/Truc.class
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

$(BUILD)MNP/PassagePieceFactoryNP.class: $(SRC)MNP/PassagePieceFactoryNP.java $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Passage.class $(BUILD)API/Piece.class $(BUILD)MNP/PieceNP.class $(BUILD)MNP/PassageNP.class
	$(JC) $(JFLAGS) $(SRC)MNP/PassagePieceFactoryNP.java

$(BUILD)MNP/PieceNP.class: $(SRC)MNP/PieceNP.java $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Piece.class $(BUILD)API/Direction.class $(BUILD)API/Passage.class
	$(JC) $(JFLAGS) $(SRC)MNP/PieceNP.java

$(BUILD)MNP/TrucFactoryNP.class: $(SRC)MNP/TrucFactoryNP.java $(BUILD)API/TrucFactory.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/Truc.class $(BUILD)MNP/TrucNP.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucFactoryNP.java

$(BUILD)MNP/TrucNP.class: $(SRC)MNP/TrucNP.java $(BUILD)API/Truc.class
	$(JC) $(JFLAGS) $(SRC)MNP/TrucNP.java

$(BUILD)VUE/ChargeCarte.class: $(SRC)VUE/ChargeCarte.java $(BUILD)API/Piece.class $(BUILD)VUE/MiniCarteVue.class
	$(JC) $(JFLAGS) $(SRC)VUE/ChargeCarte.java

$(BUILD)CONTROLEUR/SetupModel.class: $(SRC)CONTROLEUR/SetupModel.java $(BUILD)MNP/PassagePieceFactoryNP.class $(BUILD)API/Piece.class $(BUILD)MNP/PassageNP.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class $(BUILD)MODEL/ConnectionBD.class $(BUILD)MODEL/PassagePieceFactoryBD.class $(BUILD)MODEL/PassageBD.class $(BUILD)MODEL/TrucFactoryBD.class $(BUILD)MNP/JoueurNP.class $(BUILD)MNP/TrucFactoryNP.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/SetupModel.java

$(BUILD)CONTROLEUR/PieceContenuEvent.class: $(SRC)CONTROLEUR/PieceContenuEvent.java  
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/PieceContenuEvent.java 

$(BUILD)CONTROLEUR/JeterEvent.class: $(SRC)CONTROLEUR/JeterEvent.java
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/JeterEvent.java $(SRC)CONTROLEUR/InventaireController.java 

$(BUILD)CONTROLEUR/PieceController.class: $(SRC)CONTROLEUR/PieceController.java $(BUILD)CONTROLEUR/CasePieceEvent.class $(BUILD)CONTROLEUR/DescriptionEvent.class 
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/PieceController.java $(SRC)CONTROLEUR/InventaireController.java $(SRC)CONTROLEUR/CaseInventaireEvent.java  $(SRC)CONTROLEUR/JeterEvent.java $(SRC)CONTROLEUR/UtiliserEvent.java $(SRC)CONTROLEUR/PieceContenuEvent.java $(SRC)CONTROLEUR/PieceVueController.java
	 
$(BUILD)CONTROLEUR/InventaireController.class: $(SRC)CONTROLEUR/InventaireController.java $(BUILD)CONTROLEUR/CaseInventaireEvent.class $(BUILD)CONTROLEUR/JeterEvent.class $(BUILD)CONTROLEUR/UtiliserEvent.class $(BUILD)CONTROLEUR/PieceContenuEvent.class $(BUILD)CONTROLEUR/PieceVueController.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/InventaireController.java

$(BUILD)CONTROLEUR/SetupController.class: $(SRC)CONTROLEUR/SetupController.java  $(BUILD)CONTROLEUR/PieceController.class $(BUILD)CONTROLEUR/InventaireController.class $(BUILD)CONTROLEUR/PieceVueController.class $(BUILD)CONTROLEUR/EventPV.class
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/SetupController.java 

# $(BUILD)CONTROLEUR/SetupController.class: $(SRC)CONTROLEUR/SetupController.java $(BUILD)CONTROLEUR/CaseInventaireEvent.class $(BUILD)CONTROLEUR/CasePieceEvent.class $(BUILD)CONTROLEUR/DescriptionEvent.class $(BUILD)CONTROLEUR/MiniCarteEvent.class $(BUILD)CONTROLEUR/SetupModel.class $(BUILD)VUE/MiniCarteVue.class $(BUILD)CONTROLEUR/InventaireController.class $(BUILD)CONTROLEUR/PieceController.class $(BUILD)CONTROLEUR/EventPV.class

$(BUILD)CONTROLEUR/PieceVueController.class: $(SRC)CONTROLEUR/PieceVueController.java  
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/PieceVueController.java 

$(BUILD)CONTROLEUR/UtiliserEvent.class: $(SRC)CONTROLEUR/UtiliserEvent.java  
	$(JC) $(JFLAGS) $(SRC)CONTROLEUR/UtiliserEvent.java $(SRC)CONTROLEUR/InventaireController.java

runtextuel : 
	java -classpath build fr.iutfbleau.projetIHM2020FI2.test.TestTexteMNP

runbd : 
	java $(DBFLAGS) fr.iutfbleau.projetIHM2020FI2.test.TestTexteBD

run : 
	java $(DBFLAGS) fr.iutfbleau.projetIHM2020FI2.test.Main

jar:
	jar cvfe $(Name).jar fr.iutfbleau.projetIHM2020FI2.test.Main -C build fr -C res images

runjar:
	java $(DBFLAGS) -jar $(Name).jar

runtest :
	$(allTest)

clean:
	rm -rf $(BUILD)

.PHONY : but clean