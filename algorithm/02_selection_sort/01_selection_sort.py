def findMinIndex(arr):

    min = arr[0]
    min_index = 0

    for i in range(1, len(arr)):
        if arr[i] < min:
            min = arr[i]
            min_index = i

    return min_index

def selectSort(arr):

    newArr = []

    for i in range(len(arr)):
        min = findMinIndex(arr)
        newArr.append(arr.pop(min))

    return newArr

print selectSort([5, 3, 6, 2, 10])