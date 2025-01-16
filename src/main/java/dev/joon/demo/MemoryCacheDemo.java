package dev.joon.demo;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class MemoryCacheDemo {
    private static final Map<String, byte[]> CACHE = new HashMap<>();

    static class LeakyCache {
        public static void addToCache(String key) {
            CACHE.put(key, new byte[1024 * 1024]);
        }
    }

    static class NonLeakyCache {
        private static final Map<String, SoftReference<byte[]>> CACHE = new HashMap<>();

        // 개선된 버전 - SoftReference 사용 - SoftReference는 "JVM의 메모리가 부족할 때" GC의 대상이 됨. << 실시간으로 분석하면 heap이 거의 다 찬 상태에서 heap 메모리 사용량이 급격하게 감소함.
        public static void addToCache(String key) {
            CACHE.put(key, new SoftReference<>(new byte[1024 * 1024]));
        }
    }


    public static void runMemoryLeakAndImprovedCacheDemo() {
        int loopCount = 1_000_0;
        for (int i = 0; i < loopCount; i++) {
            //LeakyCache.addToCache("key-" + i);
            NonLeakyCache.addToCache("key-" + i);
            if (i % 10 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Added " + i + " items to cache");
            }
        }
    }
}
