// 7. Sort Array (Ascending & Descending)**

//    * Implement a simple bubble sort.

#include<iostream>
using namespace std;

void swap(int &a, int &b){
    int temp = a;
    a = b;
    b = temp;
}

int main(){
    int n;
    cout<<"enter number of elements : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"Enter elements : ";
        cin>>arr[i];
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<n-i-1;j++){
            if(arr[j]>arr[j+1]){
                swap(arr[j],arr[j+1]);
                // int temp = arr[j];
                // arr[j] = arr[j+1];
                // arr[j+1] = temp;
            }
        }
    }

    for(int i=0; i<n; i++){
        cout<<arr[i]<<"  ";
    }

    return 0;
}