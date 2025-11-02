// Write a  program to print all Prime numbers between 1 to n. 

#include<iostream>
using namespace std;

int main(){
    int n, i, j, isPrime;
    cout << "Enter number: ";
    cin >> n;

    cout << "Prime numbers between 1 and " << n << " are: "<< endl;
    for(i = 2; i <= n; i++){
        isPrime = 1; 
        for(j = 2; j <= i / 2; j++){
            if(i % j == 0){
                isPrime = 0; 
                break;
            }
        }
        if(isPrime){
            cout << i << " ";
        }
    }
    
    cout << endl;
    return 0;
}