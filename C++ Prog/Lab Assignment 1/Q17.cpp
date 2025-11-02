// Write a program to check entered number is Armstrong number or not.

#include<iostream>
using namespace std;

int main() {
    int num, orig, remainder, result = 0, n = 0;
    cout << "Enter an integer: ";
    cin >> num;

    orig = num;

    while (orig != 0) {
        remainder = orig % 10;
        result += remainder * remainder * remainder;
        orig /= 10;
    }

    if (result == num) {
        cout << num << " is an Armstrong number." << endl;
    } else {
        cout << num << " is not an Armstrong number." << endl;
    }

    return 0;
}