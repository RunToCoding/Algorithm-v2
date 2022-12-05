package sieveOfEratosthenes;

import java.util.Scanner;

/*
제곱 ㄴㄴ 수
어떤 정수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱ㄴㄴ수라고 함
제곱수는 정수의 제곱
min 과 max 가 주어지면, min 보다 크거나 같고 max 보다 작거나 같은 제곱ㄴㄴ수가 몇 개 있는지 출력하라.
min : 1 , max : 10 -> 1, 2, 3, 5, 6, 7, 10 => 7
 */
public class BOJ1106_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long min = input.nextLong();
        long max = input.nextLong();

        System.out.println(solution(min, max));
    }

    private static long solution(long min, long max) {
        long count = 0L;
        int scope = (int) (max - min + 1);
        boolean[] valueCheck = new boolean[scope]; // index 는 int 범위 내여야 함

        for (long i = 2; i <= Math.sqrt(max); i++) {
            long square = i * i;
            long start = min % square == 0 ? min / square : min / square + 1; // 최대한 근접한 수에서 확인을 위한 시작점
            for (long j = start; j * square <= max; j++) { // 제곱근의 배수 검사
                valueCheck[(int) (j * square - min)] = true;
            }
        }

        for (boolean check : valueCheck) {
            if (!check) count++;
        }

        return count;
    }
}
