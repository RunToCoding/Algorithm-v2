from sys import stdin, setrecursionlimit
from collections import deque

def findLongBranch(graph, Node, visited):
    if Node not in graph: return 0 # 리프노드

    maxBranch = 0
    for nextNode, edge in graph[Node].items():
        if visited[nextNode] == 0: # 다음 방문할 노드가 들린적 없으면
            visited[nextNode] = 1 #방문흔적 남김
            maxBranch = max(maxBranch, edge + findLongBranch(graph, nextNode, visited))  # 그 하위 자식노드 들린 branch

    return maxBranch


if __name__ == "__main__":
    N, rootN = map(int, stdin.readline().split()) # 노드개수, 루트노드번호
    from collections import defaultdict

    graph = defaultdict(dict)
    
    # 값 저장
    for _ in range(N-1):
        a, b, d = map(int, stdin.readline().split())
        graph[a][b] = d
        graph[b][a] = d
    
    print(" 노드 인접노드들 : ", graph)

    pillar, branch = 0, 0 # 기둥, 가지
    rootNB = rootN # 기가노드
    chkRootNb = True
    visited = [0]*(N+1)
    visited[rootN] = -1
    while True:
        if len(graph[rootNB]) > 1 : break
        if len(graph[rootNB]) == 0 : 
            chkRootNb = False
            break
        print("check  :", (rootNB, graph))
        adjNode, adjEdge = list(graph[rootNB].items())[0]
        del graph[adjNode][rootNB]
        rootNB = adjNode
        pillar += adjEdge
        visited[rootNB] = -1  
    print("### 기둥노드 정보 : ", rootNB, pillar, visited)
    print("### 그래프 정보 : ", graph)
    
    if chkRootNb : branch = findLongBranch(graph, rootNB, visited)

    print("result ; ", pillar, branch)
    