# Makefile for compiling and running the Number Range Summarizer program

# Flags
JAVAC = javac
JAVA = java
JUNIT = numberrangesummarizer/junit-4.13/junit.jar
HAMCREST = numberrangesummarizer/junit-4.13/hamcrest-core.jar
CLASSPATH = .:$(JUNIT):$(HAMCREST)

# Directories
SRC = numberrangesummarizer
BIN = numberrangesummarizer/bin


# Main class
MAIN_CLASS = numberrangesummarizer.NumberRangeSummarizerImpl

# Target to compile all .java files
compile:
	mkdir -p $(BIN)
	$(JAVAC) -cp $(CLASSPATH) -d $(BIN) $(SRC)/*.java

# Target to run JUnit tests
test: compile
	$(JAVA) -cp $(BIN):$(CLASSPATH) org.junit.runner.JUnitCore numberrangesummarizer.NumberRangeSummarizerImplTest

# Clean up compiled files
clean:
	rm -rf $(BIN)

# Main class for manual testing
run: compile
	$(JAVA) -cp $(BIN):$(JUNIT):$(HAMCREST) $(MAIN_CLASS)
