import math

def eratosthenes(min_, max_):
    result = [1] * (max_ - min_ + 1)
    
    # max보다 작은 1보다 큰 모든 제곱수 구하기
    squareNumbers = []
    for i in range(2, int(math.sqrt(max_)) + 1):
        squareNumbers.append(i*i)
        
    # 각 제곱수의 배수가 min, max 사이에 있으면 해당 배열의 숫자 제거
    for squrNum in squareNumbers:
        j = min_ // squrNum  # 반복문 시작(min에 가장 가까운 수)
        
        # 현재 수(제곱수 * j)가 min, max 사이에 있다면 result배열값 0으로 변경
        while min_ <= squrNum * j and squrNum * j <= max_:
            if result[squrNum * j - min_] > 0
                result[squrNum * j - min_] = 0
            j += 1
    
    print(sum(result))
    
# 입력 받기
min_, max_ = map(int, input().split())
eratosthenes(min_, max_)