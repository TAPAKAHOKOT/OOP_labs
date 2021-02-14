
public class Palindrome{
	// SOS запускать только с доп арументами
	// Тестирование каждого входного аргумента на палиндромность
	public static void main(String[] args){
		// Перебираю аргументы от юзера
		for (int i = 0; i < args.length; i++){
			String s = args[i];

			// Запускаю палиндромоТест
			System.out.println(isPalindrome(s) + " for " + s);
		}
	}

	public static String reverseString(String line){
		String res = "";

		// Прохожусь по каждому элементу строки
		for (int k = 0; k < line.length(); k++){
			// Теперь последняя буква строки - первая итд
			res += line.charAt(line.length() - k - 1);
		}

		return res;
	}

	public static boolean isPalindrome(String line){
		// Создаем перевернутую строку line и убираем в r_line
		String r_line = reverseString(line);

		// Проверяем совпадают ли r_line и line, true - совпадают
		return line.equals(r_line);
	}
}