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

    // Car c;
    // suv thar;
    // Racing BMW;
    // MiniCar nano;

    // Car *baseptr = &thar;
    // baseptr -> speed();

    // if(typeid(*baseptr) == typeid(suv)){
    //     cout<<"SUV"<<endl;
    //     suv *obj = dynamic_cast<suv*>(baseptr);
    //     obj -> safety();
    // }else{
    //     cout<<"not a SUV"<<endl;
    // }

    // Car *arr[3];
    // arr[0] = &c;
    // arr[1] = &thar;
    // arr[2] = &BMW;

    // for(int i = 0;i<3;i++){
    //     arr[i] -> speed();

    //     if(typeid(*arr[i]) == typeid(suv)){
    //         cout<<"SUV Thar"<<endl;
    //         suv *obj = dynamic_cast<suv*>(arr[i]);
    //         obj->safety();
    //     }else if(typeid(*arr[i]) == typeid(Racing)){
    //         cout<<"Racing"<<endl;
    //         Racing *obj1 = dynamic_cast<Racing*>(arr[i]);
    //         obj1->nos();
    //     }else{
    //         cout<<"mini car"<<endl;
    //     }
    // }

    int max = 10;
    Car* arr[max];
    int count = 0;
    int ch;

    do
    {
        cout<<"1.Add Mini car\n2.Add suv\n3.Add racing car\n4.Display all car\n0.Exit"<<endl;
        cin>>ch;
        switch (ch)
        {
        case 1:
            if(count<max){
                arr[count++] = new MiniCar();
            }
            else{
                cout<<"Array full"<<endl;
            }
            break;

        case 2:
            if(count<max){
                arr[count++] = new suv();
            }
            else{
                cout<<"Array full"<<endl;
            }
            break;

        case 3:
            if(count<max){
                arr[count++] = new Racing();
            }
            else{
                cout<<"Array full"<<endl;
            }
            break;

        case 4:
            for(int i=0;i<count;i++){
                arr[i] -> speed();

                if(typeid(*arr[i])==typeid(MiniCar)){
                    cout<<"Minicar"<<endl;
                    MiniCar *obj = dynamic_cast<MiniCar*>(arr[i]);
                    obj->speed();
                }
                else if(typeid(*arr[i])==typeid(suv)){
                    cout<<"SUV"<<endl;
                    suv *obj = dynamic_cast<suv*>(arr[i]);
                    obj->safety();
                }
                else if(typeid(*arr[i]) == typeid(Racing)){
                    cout<<"Racing Car"<<endl;
                    Racing *obj = dynamic_cast<Racing*>(arr[i]);
                    obj->nos();
                }
                else{
                    cout<<"Invalid"<<endl;
                }
            }
            break;
        case 0:
            cout<<"exit";
        default:
            break;
        }
    } while (ch!=0);
    

}
