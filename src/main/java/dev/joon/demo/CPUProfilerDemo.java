package dev.joon.demo;

public class CPUProfilerDemo {
    public static void runStringConcatenationTests() {
        System.out.println("Starting inefficient string concatenation...");
        long start = System.currentTimeMillis();
        int loop = 1_000_000;
        inefficientStringConcatenation(loop);
        // efficientStringConcatenation(loop);
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
    }

    // 의도적으로 비효율적인 문자열 처리
    public static String inefficientStringConcatenation(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Hello" + i; // String 객체를 반복적으로 생성 -> GC 횟수 + Heap 할당 횟수 증가
        }
        return result;
    }

    // 개선된 버전
    public static String efficientStringConcatenation(int iterations) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            result.append("Hello").append(i);
        }
        return result.toString();
    }
}
