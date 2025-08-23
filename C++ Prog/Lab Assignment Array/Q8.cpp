// 8.Second Largest Element**

//    * Find the second largest number without sorting.
#include<iostream>
using namespace std;

int main(){
    int n;
    cout<<"enter number of elements : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"enter number : ";
        cin>>arr[i];
    }
    int sec_largest = arr[0];
    int max = arr[0];

    for(int i=0;i<n;i++){
        if(arr[i]>max){
            max=arr[i];
        }
        if(arr[i]>sec_largest && arr[i]<max){
            sec_largest = arr[i];
        }
    }
    cout<<"second largest element is : "<<sec_largest;
    return 0;
}