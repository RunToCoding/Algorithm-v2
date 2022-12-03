# 제곱 ㄴㄴ 수
# 2559 - 수열
import sys
input = sys.stdin.readline

def squareNo(N, M):
    if N == 1 and M == 1: return 0
    
    chkList = [True]*(M-N+1)
    # print(chkList)

    for n in range(2, int(M**(1/2)+1)) : # 제곱근 범위
        n = n**2
        if n > M : break
        for i in range(((N-1)//n+1)*n, M+1, n):
        #for i in range(n, M+1, n):
            # if i < N : continue > 시간초과남 
            if chkList[i-N] : chkList[i-N] = False
    return sum(chkList)

if __name__ == "__main__":
    N, M = map(int,input().split())
    
    print(squareNo(N, M))