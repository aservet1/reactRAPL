
package jRAPL;

public class EnergyManager
{
	private static boolean libraryLoaded = false;
	private static int energyManagersActive = 0 ;

	private native static void profileInit();
	private native static void profileDealloc();

	private static void loadLibrary() {

		String nativelib = "/home/alejandro/react-rapl/jRAPL/NativeRAPL/libNativeRAPL.so";
		try {
			System.load(nativelib);
		} catch (Exception e) {
			System.err.println("ERROR LOADING LIBRARY " + nativelib);
			e.printStackTrace();
			System.exit(1);
		}
		libraryLoaded = true;
	}

	public void init() //get a better name
	{
		if (!libraryLoaded) loadLibrary();
		if (energyManagersActive++ == 0) profileInit();
		ArchSpec.init(); // there's definitely a better way of doing this
	}

	public void dealloc()
	{
		if (--energyManagersActive == 0) profileDealloc();
	}

}

