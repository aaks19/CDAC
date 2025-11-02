#include<iostream>
using namespace std;

template<class T>

class MyQueue{
    private:
            T front;
            T end;
            T arr[10];

    public:
            MyQueue(){
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

            void deque(){
                if(end<0){
                    cout<<"Queue Underflow..."<<endl;
                }else{
                    cout<<"Dequeued Element = "<<arr[front]<<endl;
                    front++;

                    if(front>end){
                        front = end = 1;
                    }
                }
            }

            void isFront(){
                cout<<"Front element ="<<arr[front]<<endl;
            }
            void isEmpty(){
                if(front == -1){
                    cout<<"True"<<endl;
                }else{
                    cout<<"False"<<endl;
                }
            }
};


int main(){
    MyQueue <int> q;
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);

    q.isFront();
    q.isEmpty();
    q.deque();
    q.deque();
    q.deque();
    q.isFront();
    q.isEmpty();

    return 0;
}