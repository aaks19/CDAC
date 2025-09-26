#include<iostream>
#include<vector>
#include<exception>
using namespace std;


class LaundryException : public exception{
    private:
            string message;

    public:
            LaundryException(const string& msg):message(msg){}
            const char* what(){
                return message.c_str();
            }
};

class laundry{
    private:
            int order_id;
            string name;

    public : int quantity;
    
    public:

            laundry(){
                order_id = 0;
                name = "xyz";
                quantity = 0;
            }

            laundry(int order_id, string name, int quantity){
                this->order_id = order_id;
                this->name = name;
                this->quantity = quantity;
            }

            virtual void display(){
                cout<<"Details"<<endl;
                cout<<"Order id = "<<order_id<<endl;
                cout<<"Name = "<<name<<endl;
                cout<<"Quantity = "<<quantity<<endl;
            }

            virtual void accept(){
                cout<<"Enter order id: "<<endl;
                cin>>order_id;
                if(!cin){
                    throw LaundryException("Invalid input");
                }
                cout<<"Enter name: "<<endl;
                cin>>name;
                cout<<"Enter quantity: "<<endl;
                cin>>quantity;
                if(!cin || quantity <=0){
                    throw LaundryException("Quantity can't be 0");
                }
            }
};


class Regular_Laundry : public laundry {
private:
    double iron_Price;
    double price; 

public:
    void calculate_RegularPrice() {
        cout << "Total price = " << price << endl;
    }

    void display() {
        laundry::display();
        cout << "Price per cloth: " << iron_Price << endl;
        cout << "Total price: " << price << endl;
    }

    void accept() {
        laundry::accept();
        cout << "Price per cloth: ";
        cin >> iron_Price;
        if(!cin||iron_Price<=0){
            throw LaundryException("Price must be positive");
        }
        price = quantity * iron_Price; 
    }
};



class Dry_Cleaning : public laundry {
private:
    double dry_Clean_Price;
    double price; 

public:
    void calculate_DryCleanPrice() {
        cout << "Total price = " << price << endl;
    }

    void display()  {
        laundry::display();
        cout << "Price per cloth: " << dry_Clean_Price << endl;
        cout << "Total price: " << price << endl;
    }

    void accept()  {
        laundry::accept();
        cout << "Price for dry cleaning: ";
        cin >> dry_Clean_Price;
        if(!cin || dry_Clean_Price<=0){
            throw LaundryException("price must be positive");
        }
        if(quantity > 10) {
            price = (quantity * dry_Clean_Price) - (quantity * 10);
        } else {
            price = quantity * dry_Clean_Price;
        }
    }
};


int main(){
    vector<laundry*> orders;

    int ch;

    do
    {
        cout<<"Enter Choice\n1.Regular Laundry\n2.Dry Clean Laundry\n3.Display Details\n4.Exit\n\n";
        cin>>ch;
        try{
            switch (ch)
            {
            case 1:{
                laundry* lr = new Regular_Laundry;
                lr->accept();
                // lr->display();
                // if(typeid(*lr) == typeid(Regular_Laundry)){
                //     Regular_Laundry *reg_L = dynamic_cast<Regular_Laundry*>(lr);
                //     reg_L->calculate_RegularPrice();
                // }else{
                //     cout<<"Invalid Type..."<<endl;
                // }
                orders.push_back(lr);
                cout<<"Added..."<<endl;
                break;
            }

            case 2:{
                laundry *dl = new Dry_Cleaning;
                dl->accept();
                // dl->display();
                // if(typeid(*dl) == typeid(Dry_Cleaning)){
                //     Dry_Cleaning *dry_L = dynamic_cast<Dry_Cleaning*>(dl);
                //     dry_L->calculate_DryCleanPrice();
                // }else{
                //     cout<<"Invalid Type..."<<endl;
                // }
                orders.push_back(dl);
                cout<<"Added..."<<endl;
                break;
            }
            
            case 3:{
                //display
                cout<<"Display details"<<endl;
                for(auto detail : orders){
                    // detail->display();
                    if(typeid(*detail) == typeid(Regular_Laundry)){
                        Regular_Laundry *lr = dynamic_cast<Regular_Laundry*>(detail);
                        lr->calculate_RegularPrice();
                        lr->display();
                    }else if(typeid(*detail) == typeid(Dry_Cleaning)){
                        Dry_Cleaning *dc = dynamic_cast<Dry_Cleaning*>(detail);
                        dc->calculate_DryCleanPrice();
                        dc->display();
                    }

                    cout<<"----------------------------------------"<<endl;
                }
                break;
            }
            
            case 4:{
                for(auto detail : orders){
                    delete detail;
                }
                orders.clear();
                cout<<"Exit"<<endl;
                break;  
            }
            default:
                break;
            }
        }catch(LaundryException& e){
            cout<<"Exception..."<<e.what()<<endl;
            cin.clear();
        }
        
    } while (ch!=4);
    
}