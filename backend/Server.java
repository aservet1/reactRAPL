import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class MyHandler implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {
		InputStream is = t.getRequestBody();
		read(is);
		String response = "This is the response";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}


public class Server {

	public static void main(String[] args) {

		

	}

}
