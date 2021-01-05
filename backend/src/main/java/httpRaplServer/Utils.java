package httpRaplServer;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Misc helper methods and things */
class Utils {
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
        		while ((s = stdInput.readLine()) != null) System.out.println(s); // printing stdout
        		while ((s = stdError.readLine()) != null) System.out.println(s); // printing stderr
        	} catch (IOException e) {
        		System.out.println("<<<IOException in execCmd():");
        		e.printStackTrace();
        		System.exit(-1);
        	}
	}
}
