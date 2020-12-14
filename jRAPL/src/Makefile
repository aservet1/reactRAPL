
TARGET = libCPUScaler.so $(OFILES)

CFILES =										\
		CPUScaler.c								\
		arch_spec.c								\
		msr.c									\
		dvfs.c									\
 		EnergyStats.c 							\

OFILES = $(CFILES:.c=.o)

JAVAFILES =									\
			ArchSpec.java 					\
			EnergyManager.java				\
											\
			EnergyMonitor.java				\
			SyncEnergyMonitor.java			\
			AsyncEnergyMonitor.java			\
											\
			EnergyStringParser.java			\
											\
			EnergyController.java			\
			DvfsEnergyController.java		\
			PowercapEnergyController.java	\
											\
			EnergySample.java				\
			EnergyStats.java				\
			EnergyDiff.java					\


CFLAGS = -fPIC -g -c -Wall
JAVA_HOME = /usr/lib/jvm/java-11-openjdk-amd64
JAVA_INCLUDE = $(JAVA_HOME)/include
JAVA_INCLUDE_LINUX = $(JAVA_INCLUDE)/linux

all: lib_shared_CPUScaler javafiles

common: $(CFILES)
	gcc $(CFLAGS) -I $(JAVA_INCLUDE) -I$(JAVA_INCLUDE_LINUX) $(CFILES)
lib_shared_CPUScaler: common
	gcc -g -Wall -I $(JAVA_INCLUDE) -I $(JAVA_INCLUDE_LINUX) -shared -Wl,-soname,libCPUScaler.so -o $(TARGET) -lc

javafiles: $(JAVAFILES)
	javac $(JAVAFILES) -d .

clean:
	rm -f lib_share_CPUScaler $(TARGET)
	rm -rf jrapl/
