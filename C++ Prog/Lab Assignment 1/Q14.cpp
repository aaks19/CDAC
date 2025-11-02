// Write a  program to find sum of all even and odd numbers between 1 to n. 

#include<iostream>
using namespace std;

int main(){
    int n, i, evenSum = 0, oddSum = 0;
    cout << "Enter number: ";
    cin >> n;
    
    for(i = 1; i <= n; i++){
        if(i % 2 == 0){
            evenSum += i; 
        } else {
            oddSum += i;
        }
    }
    
    cout << "Sum of even numbers = " << evenSum << endl;
    cout << "Sum of odd numbers = " << oddSum << endl;
    
    return 0;
}