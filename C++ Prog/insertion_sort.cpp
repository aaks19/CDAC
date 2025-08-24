#include<iostream>
using namespace std;

void swap(int &a, int &b){
    int temp = a;
    a = b;
    b = temp;
}

int main(){
    int n;
    cout<<"enter size of array: ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"Enter element : ";
        cin>>arr[i];
    }

    for(int i=0;i<n-1;i++){
        int key = arr[0];
        for(int j=i+1;j<=n;j++){
            if(arr[j]<arr[j-1]){
                swap(arr[j],arr[j-1]);
            }else{
                break;
            }
        }
    }

    cout<<"sorted array : ";
    
    for(int i=0;i<n;i++){
        cout<<arr[i]<<"  ";
    }

}
