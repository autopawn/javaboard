foxhounds:
	rm src/*/*.class || true
	javac src/javaboard/*.java
	javac -classpath src src/foxhounds/*.java
	cd src; jar -cvfm ../foxhounds.jar foxhounds/Manifest.txt javaboard/*.class foxhounds/*.class

fivefieldkono:
	rm src/*/*.class || true
	javac src/javaboard/*.java
	javac -classpath src src/fivefieldkono/*.java
	cd src; jar -cvfm ../fivefieldkono.jar fivefieldkono/Manifest.txt javaboard/*.class fivefieldkono/*.class

connectfour:
	rm src/*/*.class || true
	javac src/javaboard/*.java
	javac -classpath src src/connectfour/*.java
	cd src; jar -cvfm ../connectfour.jar connectfour/Manifest.txt javaboard/*.class connectfour/*.class	
