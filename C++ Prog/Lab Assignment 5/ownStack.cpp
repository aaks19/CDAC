/*

create your own stack class and create array inside class. write push and pop function i.e. push(int i) , int pop().
accept size from user, also check stack is full or empty.

*/


#include<iostream>
using namespace std;

class MyStack{
    private:
            int i,n;
    

    public:
            MyStack(){
                cout<<"Stack Empty"<<endl;
            }

            int pop(){
                for(int i=0;i<n;i++){
                    cout<<"popped element = "<<array[n-i-1];
                }
            }


};

int main(){
    MyStack array[5];
}