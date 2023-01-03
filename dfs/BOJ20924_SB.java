package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*
트리의 기둥과 가지
나무의 기둥 길이와 가장 긴 가지의 길이를 구하기
'루트 노드'부터 2개 이상 자식으로 나뉘는 '기가 노드'까지가 나무 기둥
'기가 노드'부터 '리프 노드'까지가 나뭇가지

방향이 항상 올바르게 주어지는 것은 아님
3 3
1 3 2
2 3 3
> 정답 : 0 3
4 4
4 3 1
2 3 3
3 1 2
> 정답 : 1 3
 */
public class BOJ20924_SB {
    static class Node {
        int nextNode;
        int length;

        Node(int nextNode, int length) {
            this.nextNode = nextNode;
            this.length = length;
        }

        @Override
        public String toString() {
            return "nextNode = " + this.nextNode + " length = " + this.length;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodeNum = input.nextInt();
        int rootNode = input.nextInt();

        // 인접 리스트 생성
        List<Node>[] nodes = new List[nodeNum + 1];
        for (int i = 0; i <= nodeNum; i++) {
            nodes[i] = new ArrayList<>();
        }

        // 양방향 노드로 구성
        for (int i = 0; i < nodeNum - 1; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int len = input.nextInt();
            nodes[a].add(new Node(b, len));
            nodes[b].add(new Node(a, len));
        }

        // 결과 출력
        System.out.println(solution(nodeNum, rootNode, nodes));
    }

    private static String solution(int nodeNum, int rootNode, List<Node>[] nodes) {
        int columnLength = 0;
        int branchLength = 0;

        boolean[] visited = new boolean[nodeNum + 1];
        visited[rootNode] = true;

        boolean isBranch = false;
        Stack<Node> nextMoveNodes = new Stack<>();
        nextMoveNodes.add(new Node(rootNode, 0));

        while (!nextMoveNodes.isEmpty()) {
            Node node = nextMoveNodes.pop();
            int nodeLength = node.length;
            int nodeNumber = node.nextNode;

            List<Node> nextNodes = nodes[nodeNumber];

            int count = 0;
            for (Node next : nextNodes) {
                if (!visited[next.nextNode]) {
                    nextMoveNodes.add(new Node(next.nextNode, nodeLength + next.length));
                    visited[next.nextNode] = true;
                    count++;
                }
            }

            // 기둥에서 가지로 변하는 순간
            if (!isBranch && count > 1) {
                isBranch = true;
                columnLength = nodeLength;
            }

            // 다음 노드가 없는 경우
            if (count == 0) {
                if (isBranch) branchLength = Math.max(branchLength, nodeLength - columnLength);
                else columnLength = Math.max(columnLength, nodeLength);
            }
        }


        return columnLength + " " + branchLength;
    }
}
