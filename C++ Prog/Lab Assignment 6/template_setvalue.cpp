//3: Class Template – Box
// Implement a class template Box<T> that stores one value of any type and provides getValue() and setValue() methods.



#include<iostream>
#include<typeinfo>
using namespace std;

template<class T>
class Box{
	private:T val;
	
	public:
		Box(T val){
			this->val = val;
		}
		void display(){
			cout<<endl<<val<<endl;
		}
			T getValue(){
			return val;		
			}
			
			void setValue(T newval){
				this->val = newval;
			}
};
int main(){
	//for int
	Box <int>b1(30);
	b1.getValue();
	b1.display();
	int a;
	cout<<"enter a number to change: "<<endl;
	cin>>a;
	Box <int>b2(a);	
	b2.display();
	
	
	
	//for char
	Box < char>b1('a');
	b1.getValue();
	b1.display();
	char a;
	cout<<"enter a number to change: "<<endl;
	cin>>a;
	Box < char>b2(a);	
	b2.display();
}
