
package jRAPL;

import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class AsyncEnergyMonitor extends EnergyMonitor implements Runnable {

	private Instant monitorStartTime = null;
	private Instant monitorStopTime = null;
	private final ArrayList<Instant> timestamps; //TODO -- decide if you want to have a boolean that enables whether or not you do want to collect timestamps
	private ArrayList<String> samples; 
	private int samplingRate; // milliseconds
	private volatile boolean exit = false;
	private Thread t = null;
	
	public AsyncEnergyMonitor()
	{
		samplingRate = 10;
		timestamps = new ArrayList<Instant>();
		samples = new ArrayList<String>();
	}

	public AsyncEnergyMonitor(int s)
	{
		samplingRate = s;
		timestamps = new ArrayList<Instant>();
		samples = new ArrayList<String>();
	}

	public void run()
	{
		while (!exit) {
			String energyString = EnergyMonitor.energyStatCheck(0);
			samples.add(energyString);
			timestamps.add(Instant.now());
			try { Thread.sleep(samplingRate); } catch (Exception e) {}
		}
	}

	public void start()
	{
		monitorStartTime = Instant.now();
		t = new Thread(this);
		t.start();
	}
	
	public void stop()
	{
		monitorStopTime = Instant.now();
		exit = true;
		try {
			 t.join();
		} catch (Exception e) {
			System.err.println("Exception " + e + " caught.");
			e.printStackTrace();
			System.err.println("Your program has shut down with process return code = 1");
			System.exit(1);
		}
		t = null;
	}

	public void reset()
	{
		monitorStartTime = null;
		monitorStopTime = null;
		exit = false;
		samples.clear();
		timestamps.clear();
	}

	public int getSamplingRate()
	{
		return samplingRate;
	}

	public void setSamplingRate(int s)
	{
		samplingRate = s;
	}

	public int getNumReadings()
	{
		return samples.size();
	}

	public Duration getLifetime()
	{
		if (monitorStartTime != null && monitorStopTime != null)
			return Duration.between(monitorStartTime, monitorStopTime);
		else return null;
	}


	public String toString()
	{
		String s = "";
		s += "samplingRate: " + samplingRate + " milliseconds\n";
		s += "lifetime: " + Long.toString(getLifetime().toMillis()) + " milliseconds\n";
		s += "number of samples: " + Integer.toString(getNumReadings()) + "\n";

		return s;
	}



	public void writeToFile(String fileName)
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter ( // write to stdout if filename is null
									(fileName == null)
										? new OutputStreamWriter(System.out)
										: new FileWriter(new File(fileName))
									);

			writer.write("samplingRate: " + samplingRate + " milliseconds\n");
			writer.write("socket,"+ArchSpec.ENERGY_STATS_STRING_FORMAT.split("@")[0]+",timestamp(usec since epoch)\n");
			for (int i = 0; i < samples.size(); i++) {
				String energyString = samples.get(i);
				String[] perSocketStrings = energyString.split("@");
				long usecs = Duration.between(Instant.EPOCH, timestamps.get(i)).toNanos()/1000;
				for (int _i = 0; _i < perSocketStrings.length; _i++) {
					int socket = _i+1;
					writer.write(
						Integer.toString(socket) + "," 
						+ perSocketStrings[_i] + "," 
						+ Long.toString(usecs) + "\n"
					);
				}
			}
			writer.flush();
			if (fileName != null)
				writer.close(); // only close if you were writing to an actual file, otherwise you would be closing System.out
		} catch (IOException e) {
			System.out.println("error writing " + fileName);
			e.printStackTrace();
		}
	}


	public Instant[] getLastKTimestamps(int k) 
	{
		int start = timestamps.size() - k;
		int arrayIndex = 0;
		if (start < 0) {
			start = 0;
			k = timestamps.size();
		}

		Instant[] timestampsArray = new Instant[k];

		for (int i = start; i < timestamps.size(); i++)
			timestampsArray[arrayIndex++] = timestamps.get(i);

		return timestampsArray;

	}

	public String[] getLastKSamples(int k) 
	{
		int start = samples.size() - k;
		int arrayIndex = 0;

		if (start < 0) {
			start = 0;
			k = samples.size();
		}
		
		String[] samplesArray = new String[k];
		for (int i = start; i < samples.size(); i++)
			samplesArray[arrayIndex++] = samples.get(i);
		
		return samplesArray;
	}


	/* Returns an array of arrays of EnergyStats objects. Each individual array
		is a list of the readings for all sockets requested. Even if only one
		socket was read from, it's still an array of arrays. The single socket
		reading is just index 0 of a 1-element array, regardless of whether it's
		just one socket because you asked for a specific socket, or because you
		were reading all sockets but only had one. */
	public EnergyStats[][] getLastKSamples_Objects(int k) 
	{
		String[] strings = getLastKSamples(k);
		Instant[] timestamps = getLastKTimestamps(k);

		EnergyStats[][] samplesArray = new EnergyStats[k][ArchSpec.NUM_SOCKETS*ArchSpec.NUM_STATS_PER_SOCKET];
		for (int i = 0; i < strings.length; i++) {
			String energyString = strings[i];
			samplesArray[i] = EnergyStringParser.toObjectArray(energyString);
			for (EnergyStats e : samplesArray[i]) e.setTimestamp(timestamps[i]);
		}

		return samplesArray;
	}
	public double[][] getLastKSamples_Arrays(int k)
	{
		String[] strings = getLastKSamples(k);
	
		double[][] samplesArray = new double[k][ArchSpec.NUM_SOCKETS*ArchSpec.NUM_STATS_PER_SOCKET];
		for (int i = 0; i < strings.length; i++) {
			String energyString = strings[i];
			samplesArray[i] = EnergyStringParser.toPrimitiveArray(energyString);
		}

		return samplesArray;
	}


	public static void main(String[] args) throws InterruptedException
	{
		int rate = (args.length > 0) ? Integer.parseInt(args[0]) : 10;
		AsyncEnergyMonitor monitor = new AsyncEnergyMonitor(rate);
		monitor.init();	

		monitor.start();	
		Thread.sleep(3000);
		monitor.stop();

		System.out.println(monitor);
		int k = 5;
		System.out.println(Arrays.deepToString(monitor.getLastKSamples_Arrays(k)));
		System.out.println();
		System.out.println(Arrays.toString(monitor.getLastKTimestamps(k)));

		monitor.dealloc();
	}

}
