# 2559 - 수열

def findNum(N, M, nums):
    # python 3에서는 print() 으로 사용합니다.

    if N <= 1 : 
        print(-1)
    else :
        start, end = 0, 0
        minV = 100000000000000
        
        nums = sorted(nums)
        while start < N and end < N:
            total = nums[end]-nums[start]

            if total < M:
                end+=1
            elif total == M:
                return M
            else :
                if total > M : minV = min(minV, total)
                start += 1
  
    return minV

if __name__ == "__main__":
    N, M = map(int, input().split())
    nums = []
    for _ in range(N):
        nums.append(int(input().split()[0]))

    print(findNum(N, M, nums))