// 1. Array Input & Output**

//    * Take `n` elements from the user and display them.

#include<iostream>
using namespace std;

int main(){
    int n;
    cout<<"Enter number of elements = ";
    cin>>n;
    int arr[n];

    for(int i=0;i<n;i++){
        cout<<"enter element: ";
        cin>>arr[i];
    }
    cout<<"Elements are  ";
    for(int i=0;i<n;i++){
        cout<<arr[i]<<" ";
    }

    return 0;
}