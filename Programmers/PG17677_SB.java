package programmers;

import java.util.HashMap;
import java.util.Map;

/*
뉴스 클러스터링
자카드 유사도는 집합 간의 유사도를 검사하는 여러 방법 중 하나로 알려져 있다.
두 집합 A, B 사이의 자카드 유사도 J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의한다.
집합 A와 집합 B 모두 공집합일 경우 나눗셈이 정의되지 않으니 따로 J(A, B) = 1로 정의한다.
이를 이용해 문자열 사이의 유사도를 계산하는 데 이용하려고 한다.
문자열을 두 글자씩 끊어서 다중 집합을 만들고, 두 문자열 사이의 자카드 유사도를 구한다.
예를 들어, 문자열 "FRANCE"가 주어진다면, {FR, RA, AN, NC, CE}와 같이 다중 집합을 만든다.
그 다음 유사도를 출력 시 65536을 곱하고 소수점 아래를 버린 정수부를 출력한다.
 */
public class PG17677_SB {
    public static void main(String[] args) {
        String[] str1 = {"E = M*C^2", "FRANCE", "aa1+aa2"};
        String[] str2 = {"e = m*c^2", "french", "AAAA12"};
        int[] answers = {65536, 16384, 43690};

        for (int idx = 0; idx < str1.length; idx++) {
            System.out.println(solution(str1[idx], str2[idx]) == answers[idx]);
        }

    }

    private static int solution(String str1, String str2) {
        int baseNumber = 65536;

        // 대문자로 변경
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        // 다중 집합 구성
        Map<String, Integer> multiSet1 = createMultiSet(str1);
        Map<String, Integer> multiSet2 = createMultiSet(str2);

        // 두 집합 모두 공집합인 경우
        if (multiSet1.isEmpty() && multiSet2.isEmpty()) return baseNumber;

        // 두 집합 중 하나가 공집합인 경우
        if (multiSet1.isEmpty() || multiSet2.isEmpty()) return 0;

        float intersection = 0; // 교집합
        float union = 0; // 합집합

        // 교집합, 합집합 구성하기 - multiSet1 기준으로 multiSet2와 비교
        for (String key : multiSet1.keySet()) {
            if (multiSet2.containsKey(key)) {
                intersection += Math.min(multiSet1.get(key), multiSet2.get(key));
                union += Math.max(multiSet1.get(key), multiSet2.get(key));
            } else {
                union += multiSet1.get(key);
            }
        }
        // 합집합 구성하기 - multiSet2 에서 multiSet1에 포함되지 않는 값들 추가
        for (String key : multiSet2.keySet()) {
            if (!multiSet1.containsKey(key)) union += multiSet2.get(key);
        }

        return (int) (intersection / union * baseNumber);
    }

    private static Map<String, Integer> createMultiSet(String word) {
        Map<String, Integer> multiSet = new HashMap<>();
        for (int idx = 0; idx < word.length() - 1; idx++) {
            if (65 <= word.charAt(idx) && word.charAt(idx) <= 90
                    && 65 <= word.charAt(idx + 1) && word.charAt(idx + 1) <= 90) {
                String key = "" + word.charAt(idx) + word.charAt(idx + 1);
                multiSet.put(key, multiSet.getOrDefault(key, 0) + 1);
            }
        }

        return multiSet;
    }
}
