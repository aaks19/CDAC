//Function Template � Array Sum
// Write a function template sumArray() that accepts an array of any type and returns the sum of its elements.


#include<iostream>
using namespace std;

template<class T>
void sumArray(T arr[], int size){
	T sum = 0;
	for(int i=0;i<size;i++){
		sum = sum + arr[i];
	}
	cout<<"Sum = "<<sum<<endl;
}

int main(){
	int size = 5;
	int arr1[] = {1,2,3,4,5};
	
	double arr2[] = {1.1,1.1,1.1,1.1,1.1};
	sumArray(arr1,size);
	sumArray(arr2, size);
}
