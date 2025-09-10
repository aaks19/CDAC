// 6. Count Even and Odd Numbers**

//    * Count how many even and odd numbers are in the array.

#include<iostream>
using namespace std;

int main(){
    int n;
    int count_eve = 0 , count_odd = 0;
    cout<<"Enter number of element : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"Enter element : ";
        cin>>arr[i];
    }
    for(int i=0;i<n;i++){
        if(arr[i] % 2 == 0){
            count_eve++;
        }
        else{
            count_odd++;
        }
    }
    cout<<"number of even numbers = "<<count_eve<<" odd number = "<<count_odd;
}