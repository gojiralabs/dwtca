package com.gojiralabs.gojira.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.Nonnull;

public class HashCache<K, V> implements Cache<K, V> {

	private final Function<K, V> creator;
	private final Map<K, V> storage = new HashMap<>();

	private HashCache(@Nonnull Function<K, V> creator) {
		this.creator = creator;
	}

	public static <K, V> HashCache<K, V> from(@Nonnull Function<K, V> creator) {
		return new HashCache<>(creator);
	}

	@Override
	public V get(@Nonnull K key) {
		if (storage.containsKey(key)) {
			return storage.get(key);
		}
		V value = creator.apply(key);
		storage.put(key, value);
		return value;
	}

	@Override
	@Nonnull
	public Map<K, V> asMap() {
		return new HashMap<>(storage);
	}

	@Override
	public void clear() {
		storage.clear();
	}
}
