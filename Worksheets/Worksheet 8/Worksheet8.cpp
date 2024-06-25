#include <iostream>
using namespace std;

// Part I Code
// Task: Write two C++ functions to calculate factorial recursively and iteratively

int recursiveFactorial(int n) {
    if (n == 1 || n == 0) {
        return 1;
    }
    else {
        return n * recursiveFactorial(n - 1);
    }
}

int iterativeFactorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result = result * i;
    }
    return result;
}

// Part II Code
// Task: Write a C++ which takes an integer as input and computes the sum of the factorial of each digit

void factorialSum() {
    int number, digit, sum = 0;

    cout << "Enter an Integer: ";
    cin >> number;

    int originalNumber = number;
    while (number > 0) {
        digit = number % 10;
        number = number / 10;
        sum = sum + recursiveFactorial(digit);
    }
    cout << "Sum of Factorials of Digits in " << originalNumber << " is: " << sum << endl;
}

// Part III Code
// Task: Write a C++ function which takes two positive integers as N and M then prints a rectangle
// with size NxM which has stars (*) at borders, and lines (-) inside.

void printRectangle(int N, int M) {

    cout << "Enter an Integer as N: ";
    cin >> N;

    cout << "Enter an Integer as M: ";
    cin >> M;

    cout << "\n";
    cout << "Your Rectangle: " << endl;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            if (i == N || i == 1 || j == M || j == 1) {
                cout << "*";
            }
            else {
                cout << "-";
            }
        }
        cout << endl;
    }
}

// Main Method for Part I, II and III
int main() {

    // Part I Test

    int n, m, result;
    cout << "\n";
    cout << "****PART I****" << endl;
    cout << "Enter an Integer to Take Recursive Factorial: ";
    cin >> n;
    cout << "The Recursive Factorial of " << n << " is: " << recursiveFactorial(n) << endl;
    cout << "\n";
    cout << "Enter an Integer to Take Iterative Factorial: ";
    cin >> m;
    cout << "The Iterative Factorial of " << m << " is: " << iterativeFactorial(m) << endl;
    cout << "\n";

    // Part II Test

    cout << "****PART II****" << endl;
    factorialSum();
    cout << "\n";

    // Part III Test

    int N, M;
    cout << "****PART III****" << endl;
    printRectangle(N, M);
    cout << "\n";
}