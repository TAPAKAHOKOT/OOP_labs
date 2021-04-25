import java.net.*;
import java.io. *;

public class Crawler{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean inReadLineAvailable = true;

	public void start(URLDepthPair mainPair){
		try {
			System.out.println("(" + mainPair.getAfterMainURLPage() + ") (" + mainPair.getMainURLPage() + ")");

			socket = new Socket();
			socket.setSoTimeout(1000);
			socket.connect(new InetSocketAddress(mainPair.getMainURLPage(), 80), 500);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("GET " + mainPair.getAfterMainURLPage() + " HTTP/1.1");
            out.println("Host: " + mainPair.getMainURLPage());
            out.println("Accept: */*");
            out.println("User-Agent: Java");
            out.println("");
            out.flush();
			System.out.println("\n\n<<< Socket is running >>>");

		} catch (Exception e){
			System.out.println(e);
		}
	}

	public String readLine(){
		try{
			if (inReadLineAvailable){
				String line = in.readLine();
				if (line == null){
					inReadLineAvailable = false;
					return null;
				}
				return line;
			}
			else
				return null;
		}
		catch (Exception e){
			// System.out.println(e);
			return null;
		}
	}

	public String clearHref(String line){
		// System.out.println(line);
		String res = "";
		if (line.contains("href=\""))
			res = line.split("href=\"")[1].split("\"")[0];
		else if (line.contains("href='"))
			res = line.split("href=\'")[1].split("\'")[0];
		if (res.startsWith("http://"))
			return res.substring(7);
		else
			return "";
	}

	public String nextUrl(){
		String line;
		while ( (line = this.readLine()) != null ){
			line = line.toLowerCase();
			// System.out.println(line);
			if (line.contains("href="))
				return clearHref(line);
		}

		return null;
	}	

	public void close(){
		try{
			in.close();
			out.close();
			socket.close();
			System.out.println("<<< Socket is closed >>>\n\n");
		}
		catch (Exception e){
			// System.out.println(e);
		}
	}

	public Crawler(){
		System.out.println("Created");
	}
}