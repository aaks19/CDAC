// Write a program to find factorial of a given number. ex:no5  fact=5*4*3*2*1=120

#include<iostream>
using namespace std;

int main(){
    int n, f=1, i;
    cout<<"Enter a number: ";
    cin>>n;
    for(i=1;i<=n;i++){
        f = f * i;
    }
    cout<<"Factorial of "<<n<<" is "<<f;
}