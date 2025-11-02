#include<iostream>
using namespace std;

class Car{
    public:
        virtual void speed(){
            cout<<"Basic speed"<<endl;
        }
};

class MiniCar:public Car{
    public: 
            void speed(){
                cout<<"mini car speed"<<endl;
            }
};

class suv:public Car{
    public:
            void speed(){
                cout<<"Suv speed"<<endl;
            }
            void safety(){
                cout<<"safest"<<endl;
            }
};

class Racing:public Car{
    public:
            void speed(){
                cout<<"Racing car"<<endl;
            };

            void nos(){
                cout<<"Nitros"<<endl;
            }
};



int main(){
    Car c;
    suv thar;
    MiniCar nano;
    Racing bmw;

    Car *baseptr = &thar;
    baseptr->speed();

    if(typeid(*baseptr) == typeid(suv)){
        cout<<"SUV"<<endl;
        suv *obj = dynamic_cast<suv*>(baseptr);
        obj->safety();
    }else{
        cout<<"not suv"<<endl;
    }
}