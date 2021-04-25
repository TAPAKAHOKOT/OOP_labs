import java.util.LinkedList;
import java.util.Arrays;

class FindAllHrefs{
	private LinkedList<URLDepthPair> list = new LinkedList<URLDepthPair>();
	private LinkedList<Integer> listURLS = new LinkedList<Integer>();
	private int maxDepth;

	public FindAllHrefs(int maxDepth, URLDepthPair pair){
		this.maxDepth = maxDepth;
		list.add(pair);
	}
	public FindAllHrefs(){
		this.maxDepth = 0;
	}

	public void saveToListAllLinkes(URLDepthPair pair){
		
		if (pair.getDepth() < this.maxDepth){
			System.out.println("\nCurrent pair: " + pair.toString());

			Crawler crawler = new Crawler();
			crawler.start(pair);

			URLDepthPair cPair;
			String linkUrl;
			while ( (linkUrl = crawler.nextUrl()) != null ){
				if (!linkUrl.equals("")){
					// System.out.println(linkUrl);
					cPair = new URLDepthPair(linkUrl, pair.getDepth() + 1);
					if (!listURLS.contains(cPair.getUrl().hashCode())){
						listURLS.add(cPair.getUrl().hashCode());
						list.add(cPair);
						this.saveToListAllLinkes(cPair);
					}
				}
			}
			crawler.close();
		}
	}

	public void printList(){
		for (URLDepthPair pair : list){
			System.out.println(pair.toString());
		}
	}

	public static void main(String[] args){
		URLDepthPair mainPair = new URLDepthPair("government.ru/", 0);
		FindAllHrefs finder = new FindAllHrefs(1, mainPair);
		finder.saveToListAllLinkes(mainPair);
		finder.printList();
	}
}