#include<iostream>>
using namespace std;

template<class T>

class MyQueue{
    private:
            T front;
            T end;
            T arr[10];

    public:
            queue(){
                this->front = -1;
                this->end = -1;
            }

            void enqueue(T val){
                if(front>9){
                    cout<<"Queue Overflow"<<endl;
                }else{
                    if(front == -1){
                        front++;
                    }
                    end++;
                    arr[end] = val;
                }
                
            }

            void de

};