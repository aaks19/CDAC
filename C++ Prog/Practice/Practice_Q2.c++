/*

2.Vehicle Management System

Design a class Vehicle representing general information about vehicles with members like vehicle_id and vehicle_type.

Derive two specialized classes:

ElectricVehicle with members battery_capacity and charging_time, and a method chargeBattery().

DieselVehicle with member fuel_capacity, and a method refuelDiesel().

The system should:

Allow dynamic addition of vehicles using polymorphism.

Accept & Display information using a virtual function.

Use RTTI (e.g., dynamic_cast or instanceof) to invoke vehicle-specific behavior.

 Note:-Create Array of Objects or Vector Or Map


*/




#include<iostream>
#include<vector>
using namespace std;

class Vehicle{
    private:
            int vehicle_id;
            string vehicle_type;

    public:
            Vehicle(){
                vehicle_id = 0;
                vehicle_type = "nan";
            }

            Vehicle(int vehicle_id, string vehicle_type){
                this->vehicle_id = vehicle_id;
                this->vehicle_type = vehicle_type;
            }

            virtual void accept(){
                cout<<"Enter vehicle detail:"<<endl;
                cout<<"Vehicle id = "<<endl;
                cin>>vehicle_id;
                cout<<"vehicle type = "<<endl;
                cin>>vehicle_type;
            }

            virtual void display(){
                cout<<"vehicle details:"<<endl;
                cout<<"Vehicle id = "<<vehicle_id<<endl;
                cout<<"Vehicle type = "<<vehicle_type<<endl;
            }
};


class ElectricVehicle : public Vehicle{
    private:
            string battery_type;
            double charging_time;

    public:
            void chargeBattery(){
                cout<<"Changeing battery"<<endl;
            }

            void accept(){
                Vehicle::accept();
                cout<<"Enter charging"<<endl;
                cout<<"Enter battery type - "<<endl;
                cin>>battery_type;
                cout<<"Enter charging time - "<<endl;
                cin>>charging_time;
            }

            void display(){
                Vehicle::display();
                cout<<"Electric vehicle charging details:"<<endl;
                cout<<"Battery type - "<<battery_type<<endl;
                cout<<"Charging time - "<<charging_time<<endl;
            }
};


class DieselVehicle : public Vehicle{
    private:
            double fuel_capacity;

    public:
            void refuelDiesel(){
                cout<<"Resuel Diesel"<<endl;
            }

            void accept(){
                Vehicle::accept();
                cout<<"Enter fuel capacity"<<endl;
                cin>>fuel_capacity;
            }
            
            void display(){
                Vehicle::display();
                cout<<"Diesel vehicle fuel detail"<<endl;
                cout<<"Fuel capacity"<<fuel_capacity;
            }
};


int main(){
    vector<Vehicle*> vehicle;
    int ch;
    do
    {
        cout<<"\n\nEnter choise-\n1.Electric Vehicle \n2.Diesel Vehicle \n3.Display \n4.Exit\n\n"<<endl;
        cin>>ch;
        switch (ch)
        {
        case 1:
            {
                Vehicle *baseptr = new ElectricVehicle();
                baseptr->accept();
                vehicle.push_back(baseptr);
            }
            break;
        
        case 2:
            {
                Vehicle *baseptr = new DieselVehicle();
                baseptr->accept();
                vehicle.push_back(baseptr);
            }
            break;

        case 3:
            //display
            {
                for(auto v : vehicle){
                    v->display();
                    if(typeid(*v)==typeid(ElectricVehicle)){
                        ElectricVehicle *ev = dynamic_cast<ElectricVehicle*>(v);
                        ev->chargeBattery();
                    }
                    else if(typeid(*v) == typeid(DieselVehicle)){
                        DieselVehicle *dv = dynamic_cast<DieselVehicle*>(v);
                        dv->refuelDiesel();
                    }
                }
            }
            break;

        case 4:
            cout<<"Exit"<<endl;
            break;
        
        default:
            break;
        }
    } while (ch!=4);
    
    for(auto v : vehicle){
        delete v;
    }
    vehicle.clear();

    return 0;

}