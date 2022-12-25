package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
보물섬
보물섬 지도는 직사각형 모양이며, 여러 칸으로 나뉘어져 있다.
각 칸은 육지(L)나 바다(W)로 표시되어 있으며, 육지에서 상하좌우로 이동이 가능하다.
한 칸 이동에 걸리는 시간은 한 시간이며,
보물은 서로 간 최단 거리로 이동하는 데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
이 때, 최단 거리 이동 시 같은 곳을 두 번 이상 지나가거나 멀리 돌아가서는 안된다.
보물이 묻혀있는 두 곳 사이의 최단 거리로 이동하는 시간을 출력하라.
 */
public class BOJ2589_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] rc = input.nextLine().split(" ");
        int row = Integer.parseInt(rc[0]);
        int col = Integer.parseInt(rc[1]);

        String[][] map = new String[row][col];

        for (int r = 0; r < row; r++) {
            String[] mapInfo = input.nextLine().split("");
            System.arraycopy(mapInfo, 0, map[r], 0, col);
        }

        System.out.println(solution(row, col, map));
    }

    private static int solution(int row, int col, String[][] map) {
        int maxDistanceMinTime = 0;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                // 바다이면 그냥 지나감
                if (map[r][c].equals("W")) continue;

                // 육지인 경우
                maxDistanceMinTime = Math.max(maxDistanceMinTime, findTreasure(r, c, map, row, col));
            }
        }

        return maxDistanceMinTime;
    }

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int findTreasure(int curRow, int curCol, String[][] map, int row, int col) {
        Queue<Position> queue = new LinkedList<>();
        int[][] isVisited = new int[row][col]; // 방문 위치를 방문 순서와 함께 표시 (방문 순서 - 시간)
        int[][] distance = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동 정보
        int maxDistanceMinTime = 0;

        // 출발 위치 추가
        queue.add(new Position(curRow, curCol));
        isVisited[curRow][curCol] = 1;

        while (!queue.isEmpty()) {
            Position curPosition = queue.poll();
            curRow = curPosition.row;
            curCol = curPosition.col;
            maxDistanceMinTime = Math.max(maxDistanceMinTime, isVisited[curRow][curCol]);
            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + distance[i][0];
                int nextCol = curCol + distance[i][1];

                if (checkLocationWithinMap(nextRow, row, nextCol, col)
                        && checkForUnvisitedLand(map, nextRow, nextCol, isVisited)) {
                    queue.add(new Position(nextRow, nextCol));
                    isVisited[nextRow][nextCol] = isVisited[curRow][curCol] + 1;
                }
            }
        }

        return maxDistanceMinTime - 1; // 시작 위치를 1로 잡았기 때문에 실제 이동 시간은 1을 빼주어야 함
    }

    private static boolean checkLocationWithinMap(int nextRow, int row, int nextCol, int col) {
        // 지도 내의 위치인지 확인
        return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col;
    }

    private static boolean checkForUnvisitedLand(String[][] map, int nextRow, int nextCol, int[][] isVisited) {
        // 방문하지 않은 땅인지 확인
        return map[nextRow][nextCol].equals("L") && isVisited[nextRow][nextCol] == 0;
    }
}
