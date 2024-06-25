# Part I Code
# Task: Write a Python function that finds the reverse of a given array.

# Reverse the array by moving it's start and end
def reverse_array(arr):
    start_index = 0
    end_index = len(arr) - 1

    while start_index < end_index:
        # Swap elements at start_index and end_index by using temp
        temp = arr[start_index]
        arr[start_index] = arr[end_index]
        arr[end_index] = temp

        # Move towards the center of the array
        start_index += 1
        end_index -= 1

    # Return the reversed array
    return arr

# Part I Test
arr_part_i = [5, 10, 15, 20, 25]
print("\n***Part I***")
print("Unsorted Array:", arr_part_i)
# Create a copy before reversing
reversed_arr = reverse_array(arr_part_i.copy())
print("Reversed Array:", reversed_arr)

# Part II Code
# Task: Write a Python function that determines if a given unsorted array of numbers consists of consecutive numbers or not.

def consecutive_numbers(arr):
    # Check if the array is empty
    if not arr:
        # Return false if the array is empty
        return False

    # Sort the array
    arr.sort()

    # Check if there are at least two consecutive numbers
    for i in range(1, len(arr)):
        # Check the sorted array to see if there is a consecutive number
        if arr[i] == arr[i - 1] + 1:
            # Return true if there is at least two consecutive numbers
            return True

    # Return false if no consecutive numbers are found
    return False

# Part II Test
arr_part_ii = [1, 5, 7, 10, 2]
arr2_part_ii = [3, 5, 9, 0, 7]
print("\n***Part II***")
print("-Does this array consist of consecutive numbers? --->", arr_part_ii)
print("Answer:", consecutive_numbers(arr_part_ii))
print("-Does this array consist of consecutive numbers? --->", arr2_part_ii)
print("Answer:", consecutive_numbers(arr2_part_ii))

# Part III Code
# Task: Write Python functions that find the intersection and union of two arrays

# Find the intersection of two arrays
def find_intersection(arr1, arr2):
    # Create an empty array
    intersection = []
    # Check if a certain element is in the first array
    for element1 in arr1:
        common = False
        # Check if the element is in the second array
        for element2 in arr2:
            if element1 == element2:
                common = True
                break
            # Append the element if it is common for two arrays
        if common:
            intersection.append(element1)
            # Return the intersection
    return intersection

# Find the union of two arrays
def find_union(arr1, arr2):
    # Copy the array
    union = arr1.copy()
    # Check if a certain element is in the second array
    for element2 in arr2:
        common = False
        # Check if the element is in the first array
        for element1 in arr1:
            if element1 == element2:
                common = True
                break
            # Append the element if the element is not common
        if not common:
            union.append(element2)
    return union

# Part III Test
arr1_part_iii = [1, 2, 3, 4, 5]
arr2_part_iii = [3, 4, 5, 6, 7]
print("\n***Part III***")
print("Array 1:", arr1_part_iii)
print("Array 2:", arr2_part_iii)
print("Intersection of The Arrays:", find_intersection(arr1_part_iii, arr2_part_iii))
print("Union of The Arrays:", find_union(arr1_part_iii, arr2_part_iii))
print("\n")
