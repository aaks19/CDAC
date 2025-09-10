// 10. Merge Two Arrays**

// * Take two arrays and merge them into a third array.


#include<iostream>
using namespace std;


int main(){
    int n,m;
    cout<<"enter number of element in array 1 : ";
    cin>>n;
    cout<<"enter number of element in array 2 : ";
    cin>>m;

    int arr1[n];
    int arr2[m];

    for(int i=0;i<n;i++){
        cout<<"enter elements of array 1: ";
        cin>>arr1[i];
    }
    for(int i=0;i<m;i++){
        cout<<"enter elements of array 2: ";
        cin>>arr2[i];
    }

    int temp[m+n];
    for(int i=0;i<n;i++){
        temp[i] = arr1[i];
    }
    for(int i=0;i<m;i++){
        temp[n+i] = arr2[i];
    }
    cout<<"merged array : ";
    for(int i=0;i<(m+n);i++){
        cout<<temp[i]<<"  ";
    }   
}
