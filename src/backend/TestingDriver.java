import jRAPL.*;

public class TestingDriver
{   
    public static void usageAbort() {
        System.err.println(
            "usage:"
            +"\n\tjava TestingDriver async for async testing"
            +"\n\tjava TestingDriver sync <primitive|object> for sync testing");
        System.exit(2);
    }
    public static void main(String... args)
    {
        if (args.length >= 1 && args[0].equals("async")){
            AsyncEnergyMonitor monitor = new AsyncEnergyMonitor();
            monitor.init();
            monitor.start();
            try {Thread.sleep(200);}catch(Exception e){e.printStackTrace();}
            monitor.stop();
            monitor.writeToFile(null);
            monitor.dealloc();
        } else if (args.length >= 1 && args[0].equals("sync")){
            SyncEnergyMonitor monitor = new SyncEnergyMonitor();
            monitor.init();
            if (args.length >= 2 && args[1].equals("primitive")){
                for (int i = 0; i < 25; i++) {
                    System.out.println(SyncEnergyMonitor.dumpPrimitiveArray(monitor.getPrimitiveSample(1)));
                    try {Thread.sleep(40);}catch(Exception e){e.printStackTrace();}
                }
            } else if (args.length >= 2 && args[1].equals("object")){
                for (int i = 0; i < 50; i++) {
                    System.out.println(monitor.getObjectSample(1).dump());
                    try {Thread.sleep(40);}catch(Exception e){e.printStackTrace();}
                }
            } else {
                usageAbort();
            }
            monitor.dealloc();
        }
        System.out.println(ArchSpec.ENERGY_STATS_STRING_FORMAT);       
    }}