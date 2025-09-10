// 9. Frequency of Each Element**

//    * Count how many times each element occurs.

#include<iostream>
using namespace std;

int main(){
    int n;
    cout<<"enter number of element : ";
    cin>>n;
    int arr[n];
    int flag[n];
    for(int i=0;i<n;i++){
        cout<<"enter element " << i+1 << " : ";
        cin>>arr[i];
        flag[i] = 0;
    }

    for(int i=0;i<n;i++){
        if(flag[i]==0){
            int count = 1;
            for(int j=i+1;j<n;j++){
                if(arr[i]==arr[j]){
                    count++;
                    flag[j] = 1;
                }
            }
            cout<<arr[i]<<" occurs "<<count<<" times"<<endl;
        }
    }
}