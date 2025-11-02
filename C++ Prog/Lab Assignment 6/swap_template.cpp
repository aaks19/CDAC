//Function Template – Swap
// Write a function template swapValues() that swaps two variables of any type.
#include<iostream>
using namespace std;

template <class A>
void swapValue(A& a, A& b){
	A temp;
	temp = a;
	a = b;
	b = temp;	
}

int main(){
//	int a, b;
	char a,b;
	cout<<"Enter two numnber a , b :"<<endl;
	cin>>a>>b;
	swapValue(a,b);
	cout<<"a = "<<a<<"  b = "<<b<<endl;
}
