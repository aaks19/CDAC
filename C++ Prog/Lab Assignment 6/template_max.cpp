//Function Template – Maximum
// Write a function template findMax() that returns the maximum of two values.

#include<iostream>
using namespace std;

template<class Q>
void findMax(Q &num1,Q &num2)
{
	if(num1>num2){
		cout<<"number 1 is greater "<<num1<<endl;
	}else{
		cout<<"number 2 is greater "<<num2<<endl;
	}
	
	
}
int main(){
	//int a,b;
	char a,b;
	cout<<"enter two numbers for finding max: "<<endl;
	cin>>a>>b;
	
	findMax(a,b);
}

