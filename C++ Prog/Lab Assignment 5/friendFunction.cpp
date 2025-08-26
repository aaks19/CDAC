#include<iostream>
using namespace std;

class Box{
    private:
            int length;
    public:
            Box(int l){
                length = l;
            }

    friend int changeLength(Box);
};

int changeLength(Box b){
    b.length = b.length + 100;
    return b.length;
}

int main(){
    Box box(100);
    int l = changeLength(box);
    cout<<"incremented length = "<<l<<endl;
}