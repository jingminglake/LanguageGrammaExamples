// "z"

//String = "aabbabcdeldc";
// "a4b3c2", 
import java.util.*;

class Solution {
	public String func(String s) {
		if (s == null || s.length() == 0)
			return s;
		Map<Character, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!map.containsKey(c)) {
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		//Set<Character> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		/*for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (set.contains(c)) {
				continue;
			}
			sb.append(c);
			sb.append(map.get(c));
			set.add(c);
			} */
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append(entry.getValue());
		}
		return sb.toString();
    }
	public static void main(String[] args) {
		Solution s = new Solution();
		String str = "zzaabbabcdeldc";
		System.out.println(s.func(str));
	}
}
