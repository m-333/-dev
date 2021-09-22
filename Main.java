
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("Java MyTree sozluk.txt");
		System.out.println("Sözlük Yükleniyor. Lütfen Bekleyin...");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sözlük Yüklendi.");
		System.out.println("Bir Kelime Yazıp Enter Tuşuna Basınız...");

		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

		Trie trie = new Trie();

		File file = new File("sozluk.txt");

		var sc = new Scanner(file);

		while (sc.hasNextLine())
			trie.addWord(sc.nextLine());

		sc.close();
		List<String> matches = trie.search(obj.readLine());

		if (matches == null || matches.size() == 0) {
			System.out.println("Sözlükte böyle bir kelime yok");
		} else {
			System.out.println("Olası Kelimeler:");
			for (String str : matches) {
				System.out.println(str);
			}
		}

	}

}
