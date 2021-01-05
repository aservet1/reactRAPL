package httpRaplServer;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Misc helper methods and things */
class Utils {

	/** Shows how many seconds and milliseconds, then counts up printing every second 
	 *	The overhead of looping and printing is gonna throw off the overall sleep time
	 *	by a few milliseconds.
	*/
	static void sleep_print(int ms) {
		int seconds = (int)(ms/1000);
		int milliseconds = ms % 1000;
		System.out.println("sec: " + seconds + ", " + "msec: " + milliseconds);
		try {
			for (int s = 1; s <= seconds; s++) {
				Thread.sleep(1000);
				System.out.println(s);
			}
			Thread.sleep(milliseconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/** Right now only used for 'sudo modprobe msr'.
	*   But can be used for any simple / non compound 
	*   (|&&>;)-like commands. Simple ones.
	*/
	static void execCmd(String command) {
		String s;
		try {
			Process p = Runtime.getRuntime().exec(command);
        	BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s); // printing stdout
			} while ((s = stdError.readLine()) != null) {
				System.out.println(s); // printing stderr
			}
		} catch (IOException e) {
        	System.out.println("<<<IOException in execCmd():");
        	e.printStackTrace();
        	System.exit(-1);
        }
	}
}
