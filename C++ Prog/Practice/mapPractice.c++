// 4:Create an application using map for storing key and value
//    key:int
//    value:Account type
//    Create Account class with actid ,name,balance
//    Create Menu driven app
//    1:Add Account
//    2:Display all
//    3:Search account by actid;
//    4:Remove all

#include<iostream>
#include<map>
#include<exception>
using namespace std;

class AccountException : public exception{
    private:
            string message;

    public:
            AccountException(const string& msg): message(msg){}
                const char* what(){
                    return message.c_str();
                }
};




class Account{
    private: 
            int id;
            string name;
            double balance;
    
    public:
            Account(){
                id = 0;
                name = "xyz";
                balance = 0.0;
            }

            Account(int id, string name, double balance){
                this->id = id;
                this->name = name;
                this->balance = balance;
            }

            void accept(){
                cout<<"id: "<<endl;
                cin>>id;
                if(!id){
                    throw AccountException("Invalid id");
                }
                cout<<"Name: "<<endl;
                cin>>name;
                cout<<"Balance: "<<endl;
                cin>>balance;
                if(balance<=0){
                    throw AccountException("balance should be positive");
                }
            }

            void display(){
                cout<<"id = "<<id<<endl;
                cout<<"Name = "<<name<<endl;
                cout<<"Balance = "<<balance<<endl;
            }

            int getId(){
                return id;
            }

};


int main(){
    map<int,Account> accmap;
    int ch;
    

    do{
        cout<<"Enter choice:\n1.Add\n2.display\n3.search\n4.remove\n5.exit"<<endl;
        cin>>ch;
        try{
            switch(ch){
            case 1:{
                Account ac;
                ac.accept();
                accmap.insert({ac.getId(),ac});
                cout<<"Added"<<endl;
            }
            break;

            case 2:{
                if(accmap.empty()){
                    cout<<"No element"<<endl;
                }else{
                    for(auto &i:accmap){
                        i.second.display();
                    }
                }
            }
            break;

            case 3:{
                int id;
                cout<<"Enter id to search: "<<endl;
                cin>>id;
                auto it = accmap.find(id);
                if(it != accmap.end()){
                    it->second.display();
                }else{
                    cout<<"not found"<<endl;
                }
            }
            break;

            case 4:{
                accmap.clear();
                cout<<"Cleared"<<endl;
            }
            break;

            case 0:{
                cout<<"Exit..."<<endl;
            }
            break;

           default:
            cout<<"invalid..."<<endl;
        }
        }catch(AccountException& e){
            cout<<"Exception.."<<e.what()<<endl;
        }
        
    }while(ch!=0);

}