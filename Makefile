# To run this use: make FILE="filename"

all: complie_and_run

complie:
	javac LabDueDates.java

run:
	java LabDueDates < $(FILE)

complie_and_run: complie run
