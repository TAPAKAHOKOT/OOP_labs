import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class FindAllHrefs implements Runnable{
	private LinkedList<URLDepthPair> list = new LinkedList<URLDepthPair>();
	private int maxDepth;
	private URLDepthPair mainPair;

	public FindAllHrefs(int maxDepth, URLDepthPair pair){
		this.maxDepth = maxDepth;
		mainPair = pair;
		list.add(pair);
	}
	public FindAllHrefs(){
		this.maxDepth = 0;
	}

	@Override
	public void run(){
		this.saveToListAllLinkes(mainPair);
	}

	public void saveToListAllLinkes(URLDepthPair pair){
		
		if (pair.getDepth() < this.maxDepth){
			// System.out.println("\nCurrent pair: " + pair.toString());

			Crawler crawler = new Crawler();
			crawler.start(pair);

			URLDepthPair cPair;
			String linkUrl;
			while ( (linkUrl = crawler.nextUrl()) != null ){
				if (!linkUrl.equals("")){
					// System.out.println(linkUrl);
					cPair = new URLDepthPair(linkUrl, pair.getDepth() + 1);
					int pairHash = cPair.getUrl().hashCode(); 
					if (!ThreadList.listURLS.contains(pairHash)){
						ThreadList.listURLS.add(pairHash);
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

	public int getListSize(){
		return list.size();
	}

	public LinkedList<URLDepthPair> getList(){
		return list;
	}

	public static void main(String[] args){
		// Scanner scanner = new Scanner(System.in);

		// System.out.println("Enter URL-adress: ");
		// String url = scanner.nextLine();

		// System.out.println("Enter scanner depth: ");
		// int depth = scanner.nextInt();
		// depth = depth < 1 ? 1 : depth;

		int depth = 5;
		String url = ThreadList.urlsList[1];

		URLDepthPair mainPair = new URLDepthPair(url, 0);
		FindAllHrefs finder = new FindAllHrefs(1, mainPair);

		finder.saveToListAllLinkes(mainPair);
		// finder.printList();
		// finder и thread массивы
		var finderArr = new FindAllHrefs[finder.getListSize() - 1];
		var threads = new Thread[finder.getListSize() - 1];
		// заполняем finder и thread массивы
		int counter = 0;
		for (var pair : finder.getList()){
			if (pair.getDepth() != 0){
				finderArr[counter] = new FindAllHrefs(depth, pair);
				threads[counter] = new Thread(finderArr[counter]);
				counter++;
			}

		}
		// работаем с потоками
		System.out.println("\n\n<<" + threads.length + ">>\n\n");
		System.out.println(mainPair.toString());
		try{
			// запускаем потоки
			for (var th : threads)
				th.start();
			// ловим остановки потоков
			for (var th : threads)
				th.join();
			// выводим результаты
			for (var fin : finderArr)
				fin.printList();
		} catch (Exception e){}
	}
}