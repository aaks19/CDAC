// Write a program to accept two integers x and n and compute x raised to n.

#include <iostream>
using namespace std;
int main() {
    int x, n;
    long long result = 1; // Use long long to handle large results
    cout << "Enter base: ";
    cin >> x;
    cout << "Enter exponent: ";
    cin >> n;

    if (n < 0) {
        cout << "negative exponent invalid!" << endl;
        return 1;
    }

    for (int i = 0; i < n; i++) {
        result *= x;
    }

    cout << x << "^" << n << " is: " << result << endl;
    return 0;
}