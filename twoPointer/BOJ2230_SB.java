package twoPointer;

import java.util.Arrays;
import java.util.Scanner;

/*
수 고르기
N개의 정수로 이루어진 수열 A[1], A[2], ..., A[N] 이 있다.
이 수열에서 두 수를 골랐을 때, 그 차이가 M 이상이면서 제일 작은 경우를 구하는 프로그램을 작성하시오
ex. 수열 : 1, 2, 3, 4, 5, M : 3 => 3 (1 4 또는 2 5 인 경우)
    수열 : 1, 5, 3 , ㅡ : 3 => 4 (1 5 인 경우)
항상 차이가 M 이상인 두 수를 고를 수 있다!
 */
public class BOJ2230_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sequenceSize = input.nextInt(); // 수열 크기
        long baseNumber = input.nextLong(); // 기준 숫자 = M
        long[] sequence = new long[sequenceSize];
        for (int i = 0; i < sequenceSize; i++) {
            sequence[i] = input.nextLong();
        }

        System.out.println(solution(sequenceSize, baseNumber, sequence));
    }

    private static long solution(int sequenceSize, long baseNumber, long[] sequence) {
        long number = Long.MAX_VALUE;

        Arrays.sort(sequence);

        int start = 0;
        int end = 0;

        while (start < sequenceSize && end < sequenceSize) {
            long gap = sequence[end] - sequence[start];
            if (gap < baseNumber) {
                end++;
            } else {
                number = Math.min(number, gap);
                start++;
            }
        }

        return number;
    }
}
