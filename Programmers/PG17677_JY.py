def splitTwoWord(str, wordDict, num) :
    lenS = len(str)-1

    wordLen = 0
    for i in range(lenS):
        s1, s2 = str[i].lower(), str[i+1].lower()

        # ord(a) = 97 / ord(z) = 122 => 영어가 아니면
        if ord(s1) < 97 or ord(s1) > 122:
            if ord(s2) < 97 or ord(s2) > 122: # 다음 글자도 영어가 아니면
                i += 1
        else : # 영어가 맞다면
            if ord(s2) < 97 or ord(s2) > 122 : # 다음 글자가 영어가 아니면
                i += 1
            else :
                if s1+s2 in wordDict.keys():
                    wordDict[s1+s2][num] += 1
                else :
                    wordDict[s1+s2] = [0 ,0]
                    wordDict[s1+s2][num] = 1
                wordLen += 1
    return wordDict, wordLen

def solution(str1, str2):
    answer = 0
    wordDict = dict()
    wordDict, lenS1 = splitTwoWord(str1, wordDict, 0)
    wordDict, lenS2 = splitTwoWord(str2, wordDict, 1)

    if lenS1 == 0 and lenS2 == 0: 
        answer = 65536
    elif lenS1 != 0 and lenS2 != 0:
        intersact = 0
        union = 0
        for value in wordDict.values():
            intersact += min(value[0], value[1])
            union += max(value[0], value[1])
        print(intersact, union)
        answer = int(intersact / union * 65536)

    return answer

print(solution("E=M*C^2", "e=m*c^2"))