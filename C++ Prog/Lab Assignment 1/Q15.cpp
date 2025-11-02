// Write a  program to enter a number and print its reverse.

#include<iostream>
using namespace std;

int main(){
    int num, i, rev = 0, digit; 
    cout << "Enter a number: ";
    cin >> num;
    int temp = num;
    
    while(temp != 0){
        digit = temp % 10; 
        rev = rev * 10 + digit; 
        temp = temp / 10; 
    }
    
    cout << "Reversed number: " << rev << endl;
    
    return 0;
}