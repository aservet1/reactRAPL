package httpRaplServer;

import jRAPL.*;

public class App 
{
    public static void main( String[] args )
    {
		SyncEnergyMonitor e = new SyncEnergyMonitor();
		e.init();

		EnergyStats before = e.getObjectSample(1);
		try { Thread.sleep(100); } catch (Exception ex) { ex.printStackTrace(); }
		EnergyStats after = e.getObjectSample(1);
		EnergyDiff diff = EnergyDiff.between(before, after); 
        System.out.println( "Hello World!" );
		System.out.println( diff.toJSON() );

		e.dealloc();
	}
}
