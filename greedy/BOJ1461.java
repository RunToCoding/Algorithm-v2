package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
도서관
각 책들의 원래 위치가 주어질 때, 책을 모두 제자리에 놔둘 최소 걸을 수 계산하기
 */
public class BOJ1461 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int books = input.nextInt(); // 책 수
        int move = input.nextInt(); // 한 번에 들 수 있는 책 수
        List<Integer> negativeLocation = new ArrayList<>(); // 음수 위치
        List<Integer> positiveLocation = new ArrayList<>(); // 양수 위치
        for (int idx = 0; idx < books; idx++) {
            int location = input.nextInt();
            if (location < 0) {
                negativeLocation.add(location);
            } else {
                positiveLocation.add(location);
            }
        }

        System.out.println(solution(books, move, negativeLocation, positiveLocation));
    }

    private static int solution(int books, int move, List<Integer> negativeLocation, List<Integer> positiveLocation) {
        int answer = 0;
        int maxNum = 0;

        negativeLocation.sort(Comparator.naturalOrder()); // 음수 오름차순 정렬
        positiveLocation.sort(Comparator.reverseOrder()); // 양수 내림차순 정렬

        // 음수
        int[] stetsAndMaxNum = getStepsAndMaxNum(negativeLocation, move);
        answer += stetsAndMaxNum[0];
        maxNum = Math.max(maxNum, stetsAndMaxNum[1]);

        // 양수
        stetsAndMaxNum = getStepsAndMaxNum(positiveLocation, move);
        answer += stetsAndMaxNum[0];
        maxNum = Math.max(maxNum, stetsAndMaxNum[1]);

        return answer * 2 - maxNum;
    }

    private static int[] getStepsAndMaxNum(List<Integer> location, int move) {
        int[] stetsAndMaxNum = new int[2];
        int idx = 0;
        while (idx < location.size()) {
            int number = Math.abs(location.get(idx));
            stetsAndMaxNum[0] += number;
            stetsAndMaxNum[1] = Math.max(number, stetsAndMaxNum[1]);
            idx += move;
        }

        return stetsAndMaxNum;
    }
}
