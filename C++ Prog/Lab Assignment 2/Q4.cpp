// 4. Search an Element (Linear Search)**

//    * Input an array and search if a number exists.


#include<iostream>
using namespace std;

int main(){
    int n, s;
    cout<<"Enter number of element : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"Enter element : ";
        cin>>arr[i];
    }
    cout<<"Enter element to search : ";
    cin>>s;
    bool found = false;
    for(int i=0;i<n;i++){
        if(s == arr[i]){
            cout<<"Element "<<s<<" found at position "<<(i+1);
            found = true;
            break;
        }
    }
    if(!found){
        cout<<"Element "<<s<<" not found";
    }
        
    return 0;
}
