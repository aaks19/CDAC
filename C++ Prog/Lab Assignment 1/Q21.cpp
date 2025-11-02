// Write a program, which accepts two integers and an operator as a character (+ - * / ), performs the 
// corresponding operation and displays the result.

#include<iostream>
using namespace std;
int main(){
    int a, b;
    char ch;
    cout << "Enter two integers: ";
    cin >> a >> b;
    cout << "Enter an operator (+, -, *, /): ";
    cin >> ch;
    switch(ch){
        case '+':
            cout << "Result: " << a + b << endl;
            break;
        case '-':
            cout << "Result: " << a - b << endl;
            break;
        case '*':
            cout << "Result: " << a * b << endl;
            break;
        case '/':
            if(b != 0){
                cout << "Result: " << a / b << endl;
            } else {
                cout << "Error: Division by zero" << endl;
            }
            break;
        default:
            cout << "Invalid operator" << endl;
            break;
    }
}