//abstract class

#include<iostream>
using namespace std;

class Shape{
    public:
            virtual void area() = 0;

};

class Circle:public Shape{
    void area(){
        cout<<"area of circle";
    }
};

class Square:public Shape{
    void area(){
        cout<<"Area of square";
    }
};

int main(){
    Shape* sp = new Circle();
    sp->area();
    Shape* sp1 = new Square();
    sp1->area();
}
