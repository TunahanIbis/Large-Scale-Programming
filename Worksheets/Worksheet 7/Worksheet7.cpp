#include <iostream>
#include <algorithm>
using namespace std;

// Part I Code
// Task: Write a C++ function that finds the reverse of a given array.

void reverseArray(int arr[], int size) {
    int start = 0;
    int end = size - 1;

    while (start < end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        start++;
        end--;
    }
}

// Part II Code
// Task: Write a C++ function that determines if a given unsorted array of numbers consists of consecutive numbers or not.

void findConsecutive(int arr[], int size) {

    for (int i = 0; i < size - 1; i++) {
        if (arr[i] + 1 == arr[i + 1]) {
            cout << "Answer: Yes. The array consists of consecutive numbers.\n";
            return;
        }
    }

    cout << "Answer: No. The array doesn't consist of consecutive numbers.\n";
}

// Part III Code
// Task: Write C++ functions that find intersection and union of two arrays

void findIntersection(int arr1[], int size1, int arr2[], int size2) {

    cout << "Intersection of The Arrays: ";
    int i = 0, j = 0;

    while (i < size1 && j < size2) {
        if (arr1[i] == arr2[j]) {
            cout << arr1[i] << " ";
            i++;
            j++;
        }
        else if (arr1[i] < arr2[j]) {
            i++;
        }
        else {
            j++;
        }
    }
    cout << "\n";
}

void findUnion(int arr1[], int size1, int arr2[], int size2) {

    cout << "Union of The Arrays: ";
    int i = 0, j = 0;

    while (i < size1 && j < size2) {
        if (arr1[i] < arr2[j]) {
            cout << arr1[i] << " ";
            i++;
        }
        else if (arr1[i] > arr2[j]) {
            cout << arr2[j] << " ";
            j++;
        }
        else {
            cout << arr1[i] << " ";
            i++;
            j++;
        }
    }

// Print remaining elements of the larger array
    while (i < size1) {
        cout << arr1[i] << " ";
        i++;
    }

    while (j < size2) {
        cout << arr2[j] << " ";
        j++;
    }

    cout << "\n";
}

// Main Method for Part I, II and III
int main() {

    // Part I Test

    int arrPartI[] = {5, 10, 15, 20, 25};
    int sizePartI = sizeof(arrPartI) / sizeof(arrPartI[0]);

    cout << "\n";
    cout << "***Part I***"
         << "\n";

    cout << "Unsorted Array: ";
    for (int i = 0; i < sizePartI; i++) {
        cout << arrPartI[i] << " ";
    }
    cout << "\n";

    reverseArray(arrPartI, sizePartI);

    cout << "Reversed Array: ";
    for (int i = 0; i < sizePartI; i++) {
        cout << arrPartI[i] << " ";
    }
    cout << "\n";

    // Part II Test

    int arrPartII[5] = {1, 5, 10, 2, 8};
    int sizePartII = sizeof(arrPartII) / sizeof(arrPartII[0]);

    int arr2PartII[5] = {2, 5, 9, 0, 7};
    int size2PartII = sizeof(arr2PartII) / sizeof(arr2PartII[0]);

    sort(arrPartII, arrPartII+5);
    sort(arr2PartII, arr2PartII+5);

    cout << "\n";
    cout << "***Part II***"
         << "\n";
    cout << "-Does this array consist of consecutive numbers? ---> ";
    for (int i = 0; i < sizePartII; i++) {
        cout << arrPartII[i] << " ";
    }
    cout << "\n";
    findConsecutive(arrPartII, sizePartII);

    cout << "-Does this array consist of consecutive numbers? ---> ";
    for (int i = 0; i < size2PartII; i++) {
        cout << arr2PartII[i] << " ";
    }
    cout << "\n";
    findConsecutive(arr2PartII, size2PartII);

    // Part III Test

    int arr1PartIII[5] = {1, 2, 3, 4, 5};
    int size1PartIII = sizeof(arr1PartIII) / sizeof(arr1PartIII[0]);

    int arr2PartIII[5] = {3, 4, 5, 6, 7};
    int size2PartIII = sizeof(arr2PartIII) / sizeof(arr2PartIII[0]);

    cout << "\n";
    cout << "***Part III***" << "\n";

    cout << "Array 1: ";
    for (int i = 0; i < size1PartIII; i++) {
        cout << arr1PartIII[i] << " ";
    }
    cout << "\n";
    cout << "Array 2: ";
    for (int i = 0; i < size2PartIII; i++) {
        cout << arr2PartIII[i] << " ";
    }
    cout << "\n";

    findIntersection(arr1PartIII, size1PartIII, arr2PartIII, size2PartIII);
    findUnion(arr1PartIII, size1PartIII, arr2PartIII, size2PartIII);
    cout << "\n";

    return 0;
}