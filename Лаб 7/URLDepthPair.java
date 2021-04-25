public class URLDepthPair{
	private final String URL;
	private final int DEPTH;

	public URLDepthPair(){
		this.URL = "";
		this.DEPTH = 0;
	}

	public URLDepthPair(String url, int depth){
		this.URL = url;
		this.DEPTH = depth;
	} 

	public String getUrl(){
		return this.URL;
	}

	public int getDepth(){
		return this.DEPTH;
	}

	public String getMainURLPage(){
		return this.getUrl().split("/")[0];
	}

	public String getAfterMainURLPage(){
		String[] urlsParts = this.getUrl().split("/");
		if (urlsParts.length < 2)
			return "/";
		else{
			String res = this.getUrl().substring(urlsParts[0].length());
			if (res.endsWith("/"))
				res = res.substring(0, res.length());
			return res;
		}
	}

	public String toString(){
		return " ".repeat(this.DEPTH) + this.DEPTH + " : " + this.URL;
	}
}