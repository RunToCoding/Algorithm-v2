
import sys
input = sys.stdin.readline

def findMin(maxN, books, MaxMP) :
    lenB = len(books)
    total, idx = 0, 0

    while idx <= lenB-1:
        print("check : ", idx)
        if idx == 0:
            if MaxMP:
                total += books[0]
                chk = 1
                print("last1 : ", ( books[0], total))
            else:
                total += (books[0]*2)
                print("last2 : ", (books[0], total))
        else:
            total += (books[idx]*2)
            print("loop : ", (books[idx], total))
        idx += maxN
    return total

if __name__ == "__main__":
    N, M = map(int,input().split()) # 책의 개수, 한번에 들 수 있는 책의 수

    books = list(map(int, input().split()))

    minus, plus = [], []
    for b in books : 
        if b < 0 : minus.append(-b)
        else: plus.append(b)
    minus = sorted(minus, reverse=True)
    plus = sorted(plus, reverse=True)

    result = 0
    print("start : ", (minus, plus))
    if len(minus) == 0:
        result += findMin(M, plus, True)
    elif (len(plus) == 0):
        result += findMin(M, minus, True)
    else :
        if minus[0] >= plus[0] : 
            result += findMin(M, minus, True)
            result += findMin(M, plus, False)
        else :
            result += findMin(M, minus, False)
            result += findMin(M, plus, True)
    
    print(result)