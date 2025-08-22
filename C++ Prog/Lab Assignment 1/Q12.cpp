// Sum of series :
// 	1+2+3+….+n

#include<iostream>
using namespace std;

int main(){
    int n,i;
    int sum=0;
    cout<<"Enter number: ";
    cin>>n;
    for(i=1;i<=n;i++){
        sum = sum + i;
    }
    cout<<"sum = "<<sum;
}