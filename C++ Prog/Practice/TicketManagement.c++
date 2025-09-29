#include<iostream>
#include<exception>
#include<vector>

using namespace std;

class TickedManagementException : public exception{
    private:
            string message;
    public: 
            TickedManagementException(const string& msg){
                     this->message=msg;
            }
            const char* what(){
                return message.c_str();
            }
};


class Ticket{
    private:
            int ticket_no;
            string passenger_name;
    protected: double fare;
    public:
            Ticket(){
                ticket_no = 0;
                passenger_name = "xyz";
                fare = 0.0;
            }
            virtual void accept(){
                cout<<"Enter ticket no: "<<endl;
                cin>>ticket_no;
                if(!ticket_no||ticket_no<=0){
                    throw TickedManagementException("Invalid ticket no.");
                }
                cout<<"Enter name: "<<endl;
                cin>>passenger_name;
            }

            virtual void display(){
                cout<<"Ticket no: "<<ticket_no<<endl;
                cout<<"Name : "<<passenger_name<<endl;
                // cout<<"fare: "<<fare<<endl;
            }
};

class BusTicket : public Ticket{
    private:
            int seat_no;
            int age;
            double distance_km;
            bool is_ac;

    public:
            void calculate_fare(){
                if(is_ac == true){
                    fare = (distance_km * 2 )+(distance_km*2)*0.20;
                }else{
                    fare = distance_km * 2;
                }

            }
            void accept(){
                Ticket::accept();
                cout<<"Enter seat number: "<<endl;
                cin>>seat_no;
                if(!seat_no||seat_no<=1 && seat_no>=40){
                    throw TickedManagementException("Invalid seat number");
                }
                cout<<"Enter age: "<<endl;
                cin>>age;
                cout<<"Enter distance in km: "<<endl;
                cin>>distance_km;
                cout<<"Enter ac or non ac:"<<endl;
                string ch;
                cin>>ch;
                if(ch=="ac"){
                    is_ac=true;
                }else{
                    is_ac = false;
                }
            }

            void display(){
                Ticket::display();
                cout<<"Seat no: "<<seat_no<<endl;
                cout<<"age: "<<age<<endl;
                cout<<"AC or Non AC: ";
                if(is_ac){
                    cout<<"AC"<<endl;
                }else{
                    cout<<"Non AC"<<endl;
                }
                cout<<"Fare : "<<fare<<endl;
            }
};


class TrainTicket : public Ticket{
    private:
            string coach_type;
            double distance_km;

    public:
            void calculate_fare(){
                if(coach_type == "AC"){
                    fare = (distance_km*1.5) + (distance_km*1.5)*0.30;
                }
                else{
                    fare = distance_km * 1.5;
                }
            }

            void accept(){
                Ticket::accept();
                cout<<"Enter coach Type (AC, General, Sleeper): "<<endl;
                cin>>coach_type;
                cout<<"Enter distance in km : "<<endl;
                cin>>distance_km;
            }

            void display(){
                Ticket::display();
                cout<<"Coach type: "<<coach_type<<endl;
                cout<<"Distance: "<<distance_km<<endl;
                cout<<"Fare : "<<fare<<endl;
            }
};



int main(){
    vector<Ticket*> ticket;
    int ch;

    do{
        try{
            cout<<"Enter choice:\n1.Add Bus Ticket\n2.Add Train Ticket\n3.Diaplay All Ticket\n4.Exit"<<endl;
            cin>>ch;

            switch (ch)
            {
            case 1:{
                Ticket* t = new BusTicket();
                t->accept();
                ticket.push_back(t);
                cout<<"Added"<<endl;
                break;
            }
            case 2:{
                Ticket* t = new TrainTicket();
                t->accept();
                ticket.push_back(t);
                cout<<"Added"<<endl;
                break;
            }
            case 3:{
                for(auto tkt : ticket){
                    if(typeid(*tkt) == typeid(BusTicket)){
                        BusTicket *bt = dynamic_cast<BusTicket*>(tkt);
                        bt->calculate_fare();
                        bt->display();
                    }
                    if(typeid(*tkt) == typeid(TrainTicket)){
                        TrainTicket *tt = dynamic_cast<TrainTicket*>(tkt);
                        tt->calculate_fare();
                        tt->display();
                    }
                }
            }
            break;

            case 4:
                cout<<"Exit..."<<endl;
                break;
            default:
                break;
            }
        }catch(TickedManagementException& e){
            cout<<"Exception occured..."<<e.what()<<endl;
        }
    }while(ch!=4);



}