// Check if number is a prime number or not.: 

#include<iostream>
using namespace std;

int main(){
    int num,i;
    cout<<"enter a number : ";
    cin>>num;
    if(num<=1){
        cout<<"not prime";
    }
    for(int i=2;i<=num/2;i++){
        if(num % i == 0){
            cout<<"not prime";
            break;
        }else{
            cout<<"prime";
            break;
        }
    }
}