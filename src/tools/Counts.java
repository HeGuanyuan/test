package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counts {
	public static void main(String[] args) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader("P:\\for_test_s1.txt"));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		Pattern expression = Pattern.compile("[a-zA-Z]+");// 定义正则表达式匹配单词
		String string = buffer.toString();
		Matcher matcher = expression.matcher(string);//
		Map<String, Integer> map = new TreeMap<String, Integer>();
		String word = "";
		while (matcher.find()) {// 是否匹配单词
			word = matcher.group();// 得到一个单词-树映射的键
			if (map.containsKey(word)) {// 如果包含该键，单词出现过
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);// 否则单词第一次出现，添加到映射中
			}
		}
		/*
		 * 核心：如何按照TreeMap 的value排序而不是key排序.将Map.Entry放在集合里， 重写比较器，在用
		 * Collections.sort(list, comparator);进行 排序
		 */

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		/*
		 * 重写比较器 取出单词个数（value）比较
		 */
		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
				return (left.getValue()).compareTo(right.getValue());
			}
		};

		Comparator<Map.Entry<String, Integer>> c = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				return -arg0.getValue().compareTo(arg1.getValue());
			}
		};
		Collections.sort(list, comparator);// 排序

		// 打印最多五个
		int last = list.size() - 1;
		for (int i = 0; i < last + 1; i++) {
			String key = list.get(i).getKey();
			Integer value = list.get(i).getValue();
			System.out.format("%6s : %d\n", key, value);
		}
	}
}
