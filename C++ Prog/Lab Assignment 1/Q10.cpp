// Write a program to find m to the power n. m=3 and n=4 so 3*3*3*3

#include<iostream>
using namespace std;

int main(){
    int n, m, p=1, i;
    cout<<"enter base: ";
    cin>>m;
    cout<<"enter power: ";
    cin>>n;
    for(i=0;i<n;i++){
        p *= m;
    }
    cout<<m<<" to the power "<<n<<" is "<<p;
}