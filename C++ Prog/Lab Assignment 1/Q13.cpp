// Check whether the number is palindrome or not?

#include<iostream>
using namespace std;

int main(){
    int num, i, rev, digit;
    cout<<"Enter a number";
    cin>>num;
    int temp = num;
    while(temp != 0){
        digit = temp % 10;
        rev = rev * 10 + digit;
        temp = temp / 10;
    }
    if(rev == num){
        cout<<"palindrome";
    }else{
        cout<<"not palindrome";
    }
}