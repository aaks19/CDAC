// Write a program that accepts numbers continuously as long as the number is positive and prints the 
// sum of the given numbers.

#include <iostream>
using namespace std;   

int main() {
    int num, sum = 0;
    cout << "Enter a number: ";
    cin >> num;
    while (num > 0) {
        sum += num;
        cout << "Enter a number: ";
        cin >> num;
    }
    cout << "Sum is: " << sum << endl;
    return 0;
}