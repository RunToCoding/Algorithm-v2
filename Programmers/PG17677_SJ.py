def solution(str1, str2):
    union = {}
    intersection = {}
    
    # 문자열 집합 생성
    str1, str2 = str1.lower(), str2.lower()
    strGroup1, strGroup2 = [], []
    strDict1, strDict2 = {}, {}
    
    # 문자열 집합 생성
    for i in range(len(str1)-1):
        temp_str = str1[i:i+2]
        if temp_str.isalpha():
            strGroup1.append(temp_str)
    
    for i in range(len(str2)-1):
        temp_str = str2[i:i+2]
        if temp_str.isalpha():
            strGroup2.append(temp_str)
            
    if len(strGroup1) == 0 and len(strGroup2) == 0:
        jaccard = 1
    else:
        # 딕셔너리로 변환
        strDict1 = {s : strGroup1.count(s) for s in list(set(strGroup1))}
        strDict2 = {s : strGroup2.count(s) for s in list(set(strGroup2))}

        # 교집합 구하기
        keyGroup2 = strDict2.keys()

        for k, v in strDict1.items():
            if k in keyGroup2:
                intersection[k] = min(strDict1[k], strDict2[k])

        # 합집합 구하기
        for k, v in strDict1.items():
            if k in keyGroup2:
                union[k] = max(strDict1[k], strDict2[k])
            else:
                union[k] = v

        unionKeyGroup = union.keys()

        for k, v in strDict2.items():
            if k not in unionKeyGroup:
                union[k] = strDict2[k]

        jaccard = sum(intersection.values()) / sum(union.values())

    result = int(jaccard * 65536)
    return result