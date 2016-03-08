import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hashing {
	public static void main(String[] args) throws Exception {
		// 读取一篇文章中的单词作为测试用例
		BufferedReader reader = new BufferedReader(new FileReader("P:\\e.txt"));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		reader.close();

		Pattern expression = Pattern.compile("[a-zA-Z]+");// 定义正则表达式匹配单词
		String string = buffer.toString();

		Matcher matcher = expression.matcher(string);
		int c = 0;
		while (matcher.find()) {
			c++;
		}

		// 测试用例data
		String[] data = new String[c];
		matcher = expression.matcher(string);
		c = 0;
		while (matcher.find()) {
			data[c++] = matcher.group();
		}
	}

	public static void find(String[] data) {
		int s = 456976;

	}

	class Pair {
		int v;
		String k;

		public Pair(String key) {
			this.k = key;
		}

		public int hashCode() {
			char[] array = this.k.toCharArray();
			int code = 0;
			for (int i = 0; i < array.length && i < 4; i++) {
//				code += char[i]*
			}
			return code;
		}
	}

	class HashPairs {
		Pair[] pairs = new Pair[100];

		public HashPairs() {
			
		}

		public void Insert(String s) {

		}
	}
	

}
