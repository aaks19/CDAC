// 2. Sum & Average of Array**

//    * Input marks of `n` students, find total & average.

#include<iostream>
using namespace std;

int main(){
    int n, sum=0,count=0;
    cout<<"Enter number of students : ";
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++){
        cout<<"enter marks = ";
        cin>>arr[i];
        count++;
    }
    for(int i=0;i<n;i++){
        sum = sum + arr[i];
    }
    cout<<"sum of marks = "<<sum;
    float average = sum/count;
    cout<<"average = "<< average;

    return 0;
}