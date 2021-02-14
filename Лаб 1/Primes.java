
public class Primes{
	// Мой супер главный метод
	public static void main(String[] args){
		// Перебираю числа от 2 до 100 в поисках простых
		for (int k = 2; k < 101; k++){
			if (!isPrime(k))
				System.out.println("False for " + k);
		}
	}

	// Метод поиска простых чисел, false если простое
	public static boolean isPrime(int num){
		for (int k = 2; k < num; k++){
			if (num % k == 0)
				return true;
		}

		return false;
	}
}