
package httpRaplServer;

import java.util.Date;

/** Parse url path, call a JraplJob (get energy stats, get energy lapse over milliseconds, etc) 
 * if necessary, and then package up a response and header into a a String array {header, response}
 * which is then send to the appropriate channels in the calling HttpRAPL server class.
*/
class Router {
	/** Returns header and body of GET request on 'path' */
    static String[] route(String path) {
        String header, body;
        boolean success = true;
        
		if (path.equals("/"))
		{
			body = Pages.WELCOME;
		}
		else if (path.equals("/energy/stats"))
		{
			body = JraplJobs.energySnapshot().toJSON();
		}
		else if (path.startsWith("/energy/diff/")) // will overall be of the form '/energy/diff/{milliseconds}'
		{
			try{
				int milliseconds = Integer.parseInt(path.substring("/energy/diff/".length())); //remove "energy/diff/" prefix and parse the number remanining
				body = JraplJobs.energyLapse(milliseconds).toJSON();
			} catch (NumberFormatException ex) {
				//ex.printStackTrace();
				body = Pages.NOT_FOUND;
				success = false;
			}
		}
	    else
		{
            body = Pages.NOT_FOUND;
            success = false;
        }
        
        header = "HTTP/1.1 " + ((success) ? "200 OK" : "404 NOT FOUND") + "\n"
    		    +"Server: HttpRAPL Server for Energy Requests : 1.0" + "\n"
    	    	+"Date: " + new Date() + "\n"
        		+"Content-type: " + "text/html" + "\n" // @TODO not sure if you can just blanket claim that it's "text/html"
                +"Content-length: " + body.getBytes().length;

        return new String[]{header, body};
    }    
}