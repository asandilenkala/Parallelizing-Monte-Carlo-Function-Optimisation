JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
PACKAGE=MonteCarloMini

$(BINDIR)/$(PACKAGE)/%.class: $(SRCDIR)/$(PACKAGE)/%.java
	$(JAVAC) -d $(BINDIR) -sourcepath $(SRCDIR) $<

CLASSES=TerrainArea.class SearchParallel.class MonteCarloMinimizationParallel.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/$(PACKAGE)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/$(PACKAGE)/*.class 