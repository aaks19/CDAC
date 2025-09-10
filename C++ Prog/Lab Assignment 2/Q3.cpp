// 3. Find Maximum and Minimum**

//    * Input array and print the largest & smallest element.

#include<iostream>
using namespace std;

int main(){
    int n;
    cout<<"Enter number of elements : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"enter elements : ";
        cin>>arr[i];
    }
    int min = arr[0];
    int max = arr[0];
    for(int i=0;i<n;i++){
        if(arr[i]<min){
            min=arr[i];
        }
        if(arr[i]> max){
            max=arr[i];
        }
    }
    cout<<"minimum : "<<min<<"  maximum : "<<max;
    return 0;
}