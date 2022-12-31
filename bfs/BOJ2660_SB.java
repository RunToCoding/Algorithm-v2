package bfs;

import java.util.*;

/*
회장 뽑기
축구 응원을 위한 모임에서 회장을 선출하려고 한다.
어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다.
어느 회원이 다른 모든 회원과 친구이거나 친구의 친구이면, 이 회원의 점수는 2점이다.
어느 회원이 다른 모든 회원과 친구이거나 친구의 친구의 친구이면, 이 회원의 점수는 3점이다.
이런 식으로 4점, 5점 등이 정해진다.
회장은 회원들 중 점수가 가장 작은 사람이 된다.
회원 수는 50명을 넘지 않으며, 둘째 줄 이후 두 개의 회원 번호가 있는데, 이는 친구임을 의미한다.
회원 번호는 1부터 회원 수만큼 있으며, 마지막 줄에는 -1이 두 개 들어있다.
첫 번째 줄에 회장 후보 점수와 후보 수를 출력하고,
두 번째 줄에 회장 후보를 오름차순으로 모두 출력하라.
 */
public class BOJ2660_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int memberNum = input.nextInt() + 1; // 회원 번호가 1번부터 시작하므로 memberNum 을 1개 늘려준다!
        boolean[][] memberRelations = new boolean[memberNum][memberNum];

        while (true) {
            int i = input.nextInt();
            int j = input.nextInt();

            if (i == -1 && j == -1) break;

            memberRelations[i][j] = true;
            memberRelations[j][i] = true;
        }

        solution(memberNum, memberRelations);

    }

    private static void solution(int memberNum, boolean[][] memberRelations) {
        List<Integer> candidates = new ArrayList<>();
        int minScore = Integer.MAX_VALUE;


        // 각 멤버 점수 계산
        for (int i = 1; i < memberNum; i++) {
            int[] scores = new int[memberNum];
            Arrays.fill(scores, -1);
            Queue<Integer> nextMembers = new LinkedList<>();
            nextMembers.add(i);
            scores[i] = 0;

            while (!nextMembers.isEmpty()) {
                int member = nextMembers.poll();

                for (int j = 1; j < memberNum; j++) {
                    if (memberRelations[member][j] && scores[j] < 0) {
                        nextMembers.add(j);
                        scores[j] = scores[member] + 1;
                    }
                }
            }
            int score = Arrays.stream(scores).max().getAsInt();

            if (score > minScore) continue;

            if (score < minScore) {
                minScore = score;
                candidates = new ArrayList<>();

            }
            candidates.add(i);
        }

        Collections.sort(candidates);

        // 결과 출력
        System.out.println(minScore + " " + candidates.size());
        candidates.forEach(candidate -> System.out.print(candidate + " "));
    }
}
