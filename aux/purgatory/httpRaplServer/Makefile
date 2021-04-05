jRAPL-jar = src/main/resources/jRAPL.jar
httpRAPL-jar = target/httpRaplServer-1.0.jar

all:
	mvn clean install
	./transferjars.sh $(jRAPL-jar) $(httpRAPL-jar)

clean:
	mvn clean
