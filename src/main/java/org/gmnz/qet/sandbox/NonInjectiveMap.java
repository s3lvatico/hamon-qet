package org.gmnz.qet.sandbox;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class NonInjectiveMap {

	private Map<String, Integer> direct;
	private Map<Integer, List<String>> inverse;




	NonInjectiveMap() {
		direct = new HashMap<>();
		inverse = new HashMap<>();
	}




	void put(String key, Integer value) {
		direct.put(key, value);
		if (!inverse.containsKey(value)) {
			List<String> l = new ArrayList<>();
			inverse.put(value, l);
		}
		inverse.get(value).add(key);
	}

}
