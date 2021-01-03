NATIVELIB = libNativeRAPL.so
TARGET = $(NATIVELIB) $(OFILES)

CFILES =					\
		NativeRAPL.c		\
		arch_spec.c			\
		msr.c				\
 		EnergyStats.c 		\

OFILES = $(CFILES:.c=.o)

CFLAGS = -fPIC -g -c -Wall
JAVA_HOME = /usr/lib/jvm/java-11-openjdk-amd64
JAVA_INCLUDE = $(JAVA_HOME)/include
JAVA_INCLUDE_LINUX = $(JAVA_INCLUDE)/linux

all: libNativeRAPL

libNativeRAPL:
	gcc $(CFLAGS) -I $(JAVA_INCLUDE) -I$(JAVA_INCLUDE_LINUX) $(CFILES)
	gcc -g -Wall -I $(JAVA_INCLUDE) -I $(JAVA_INCLUDE_LINUX) -shared -Wl,-soname,$(NATIVELIB) -o $(TARGET) -lc

clean:
	rm -f $(TARGET)
