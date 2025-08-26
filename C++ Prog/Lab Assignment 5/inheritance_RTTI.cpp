#include<iostream>
using namespace std;

class Car{
    public:
            virtual void speed(){
                cout<<"Basic car speed"<<endl;
            }
};

class MiniCar:public Car{
    public:
            void speed(){
                cout<<"Mini car speed = 60 km/hr"<<endl;
            }
};

class suv:public Car{
    public:
            void speed(){
                cout<<"SUV Speed = 250 km/hr"<<endl;
            }
            void safety(){
                cout<<"Safest SUV"<<endl;
            }
};

class Racing:public Car{
    public:
            void speed(){
                cout<<"Racing car speed = 400 km/hr"<<endl;
            }

            void nos(){
                cout<<"Fastest Racing Car"<<endl;
            }
};

int main(){

    Car c;
    suv thar;
    Racing BMW;
    MiniCar nano;

    Car *baseptr = &thar;
    baseptr -> speed();

    if(typeid(*baseptr) == typeid(suv)){
        cout<<"SUV"<<endl;
        suv *obj = dynamic_cast<suv*>(baseptr);
        obj -> safety();
    }else{
        cout<<"not a SUV"<<endl;
    }

    Car *arr[3];
    arr[0] = &c;
    arr[1] = &thar;
    arr[2] = &BMW;

    for(int i = 0;i<3;i++){
        arr[i] -> speed();

        if(typeid(*arr[i]) == typeid(suv)){
            cout<<"SUV Thar"<<endl;
            suv *obj = dynamic_cast<suv*>(arr[i]);
            obj->safety();
        }else if(typeid(*arr[i]) == typeid(Racing)){
            cout<<"Racing"<<endl;
            Racing *obj1 = dynamic_cast<Racing*>(arr[i]);
            obj1->nos();
        }else{
            cout<<"mini car"<<endl;
        }
    }

}
