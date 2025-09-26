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
#include<set>
#include<exception>
using namespace std;

class VehicleException : public exception{
    private:
            string message;

    public:
            VehicleException(const string& msg):message(msg){}
            const char* what(){
                return message.c_str();
            }
};

class Vehicle{
    private:
            int vehicle_id;
            string vehicle_type;

    public:
            Vehicle(){
                vehicle_id = 0;
                vehicle_type = "xyz";
            }

            Vehicle(int vehicle_id, string vehicle_type){
                this->vehicle_id = vehicle_id;
                this->vehicle_type = vehicle_type;
            }

            virtual void display(){
                cout<<"Vehicle details:"<<endl;
                cout<<"Vehicle id: "<<vehicle_id<<endl;
                cout<<"Vehicle Type: "<<vehicle_type<<endl;
            }

            virtual void accept(){
                cout<<"Enter details:"<<endl;
                cout<<"Enter vehicle id: "<<endl;
                cin>>vehicle_id;
                if(!vehicle_id){
                    throw VehicleException("Invalid id");
                }
                cout<<"Enter vehicle type: "<<endl;
                cin>>vehicle_type;
            }
};


class ElectricVehicle : public Vehicle{
    private:
            int batteryCapacity;
            double chargingTime;

    public:
            void chargeBattery(){
                cout<<"battery is charging"<<endl;
            }

            void accept(){
                Vehicle::accept();
                cout<<"Enter battery capacity: "<<endl;
                cin>>batteryCapacity;
                cout<<"Enter charging time: "<<endl;
                cin>>chargingTime;
                cout<<"Done..."<<endl;
            }

            void display(){
                Vehicle::display();
                cout<<"battery capacity: "<<batteryCapacity<<endl;
                cout<<"charging time: "<<chargingTime<<endl;
            }
};

class DieselVehicle : public Vehicle{
    private:
            double fuelCapacity;
    public:
            void refuelDiesel(){
                cout<<"Refueling..."<<endl;
            }

            void accept(){
                Vehicle::accept();
                cout<<"Enter fuel capacity: "<<endl;
                cin>>fuelCapacity;
                cout<<"Done..."<<endl;
            }

            void display(){
                Vehicle::display();
                cout<<"Fuel capacity: "<<fuelCapacity<<endl;
            }
};

int main(){
    vector<Vehicle*> vehicle;

    // set<Vehicle*> vehicle;

    // Vehicle *vehicle[10];
    int count=0;

    int ch;
    
    do{
        cout<<"enter choice:\n1.Electric vehicle\n2.diesel vehicle\n3.display\n0.Exit"<<endl;
        cin>>ch;
        try{
            switch (ch)
        {
        case 1:
            {
                Vehicle *baseptr = new ElectricVehicle();
                baseptr->accept();
                // vehicle.insert(baseptr); //set
                vehicle.push_back(baseptr); //vector
                // vehicle[count++] = baseptr; //array
            }
            break;
            
        case 2:
            {
                Vehicle *baseptr1 = new DieselVehicle();
                baseptr1->accept();
                // vehicle.insert(baseptr1); //set
                vehicle.push_back(baseptr1); //vector
                // vehicle[count++] = baseptr1; //array
            }
            break;
        case 3:
        {

            //using vector

            for(auto v : vehicle){
                v->display();
                if(typeid(*v)==typeid(ElectricVehicle)){
                    ElectricVehicle *eobj = dynamic_cast<ElectricVehicle*>(v);
                    eobj->chargeBattery();
                    eobj->display();
                }else if(typeid(*v) == typeid(DieselVehicle)){
                    DieselVehicle *dobj = dynamic_cast<DieselVehicle*>(v);
                    dobj->refuelDiesel();
                    dobj->display();
                }else{
                    cout<<"invalid"<<endl;
                }
            }


            //using array
            // for(int i=0;i<count;i++){
            //     if(typeid(*vehicle[i]) == typeid(ElectricVehicle)){
            //         ElectricVehicle *eobj = dynamic_cast<ElectricVehicle*>(vehicle[i]);
            //         eobj->chargeBattery();
            //         eobj->display();
            //     }
            //     else if(typeid(*vehicle[i])==typeid(DieselVehicle)){
            //         DieselVehicle *dobj = dynamic_cast<DieselVehicle*>(vehicle[i]);
            //         dobj->refuelDiesel();
            //         dobj->display();
            //     }
            //     else{
            //         cout<<"Invalid..."<<endl;
            //     }
            // }

            // using Set

            // for(auto v : vehicle){
            //     // v->display();
            //     if(typeid(*v)==typeid(ElectricVehicle)){
            //         ElectricVehicle *eobj = dynamic_cast<ElectricVehicle*>(v);
            //         eobj->chargeBattery();
            //         eobj->display();
            //     }else if(typeid(*v) == typeid(DieselVehicle)){
            //         DieselVehicle *dobj = dynamic_cast<DieselVehicle*>(v);
            //         dobj->refuelDiesel();
            //         dobj->display();
            //     }else{
            //         cout<<"invalid"<<endl;
            //     }
            // }
        }  
        break;  
        default:
            break;
        }
        }catch(VehicleException& e){
            cout<<"Exception..."<<e.what()<<endl;
        }
        
    }while(ch!=0);
}