name = Main.class

but : $(name)

CFILES = ButtonLoadFileObserver.class \
	ButtonSaveFileObserver.class \
	CaseNumber.class \
	FileManager.class \
	GridModel.class \
	ObservateurMenu.class \
	ObservateurSudoku.class \
	PanelMenu.class \
	PanelSudoku.class \
	SaveFilter.class \
	Window.class \
	HelpVue.class \
	TimerModel.class \
	WinnerVue.class \
	ModeAuto.class \
	Main.class

JC = javac

JFLAGS = -implicit:none

ButtonLoadFileObserver.class : FileManager.class GridModel.class
	$(JC) $(JFLAGS) ButtonLoadFileObserver.java

ButtonSaveFileObserver.class : FileManager.class GridModel.class
	$(JC) $(JFLAGS) ButtonSaveFileObserver.java

CaseNumber.class : 
	$(JC) $(JFLAGS) CaseNumber.java

FileManager.class : SaveFilter.class
	$(JC) $(JFLAGS) FileManager.java

GridModel.class : CaseNumber.class
	$(JC) $(JFLAGS) GridModel.java

ObservateurMenu.class : HelpVue.class
	$(JC) $(JFLAGS) ObservateurMenu.java

ObservateurSudoku.class : 
	$(JC) $(JFLAGS) ObservateurSudoku.java

PanelMenu.class : ObservateurMenu.class
	$(JC) $(JFLAGS) PanelMenu.java

PanelSudoku.class : ObservateurSudoku.class WinnerVue.class TimerModel.class
	$(JC) $(JFLAGS) PanelSudoku.java

SaveFilter.class :
	$(JC) $(JFLAGS) SaveFilter.java

Window.class : PanelMenu.class PanelSudoku.class  GridModel.class FileManager.class TimerModel.class ModeAuto.class
	$(JC) $(JFLAGS) Window.java

HelpVue.class :
	$(JC) $(JFLAGS) HelpVue.java

WinnerVue.class :
	$(JC) $(JFLAGS) WinnerVue.java

TimerModel.class :
	$(JC) $(JFLAGS) TimerModel.java

ModeAuto.class :
	$(JC) $(JFLAGS) ModeAuto.java

Main.class : Window.class
	$(JC) $(JFLAGS) Main.java

clean :
	-rm -f $(CFILES)

run :
	java Main

.PHONY : but clean