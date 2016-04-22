all:	compile run

compile: randCFL.java
	javac randCFL.java

run:	randCFL.class
	java randCFL -t grammar

clean:
	rm -f randCFL.class