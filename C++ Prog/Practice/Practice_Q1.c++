/*

1.Company Department Structure
Create a class Department with attributes dept_id and dept_name.

Derive:

HRDepartment, with members num_recruiters, open_positions, and a method conductInterviews().

FinanceDepartment, with member budget, and a method generateFinancialReport().

Requirements:

Add departments polymorphically.

Accept & Display details via virtual functions.

Use RTTI to call class-specific methods dynamically.

*/

#include<iostream>
using namespace std;


class Department{
    private: int dept_id;
                string dept_name;

    public:
            Department(){
                dept_id = 0;
                dept_name = "NAN";
            }

            Department(int dept_id, string dept_name){
                this->dept_id = dept_id;
                this->dept_name = dept_name;
            }

            virtual void display(){
                cout<<"Details:"<<endl;
                cout<<"Department id = "<<dept_id<<endl;
                cout<<"Department name = "<<dept_name<<endl;
            }

            virtual void acceptDetail(){
                cout<<"Enter department Details: "<<endl;
                cout<<"Enter dept id ="<<endl;
                cin>> dept_id;
                cout<<"Enetr dept name = "<<endl;
                cin>> dept_name;
            }
};


class HrDepartment : public Department{
    private:
            int num_recruiters,open_positions;
    
    public:
            void conductInterviews(){
                cout<<"Conduct interviews"<<endl;
            }
            void display(){
                Department::display();
                cout<<"interview details"<<endl;
                cout<<"number of recruiters = "<<num_recruiters<<endl;
                cout<<"open_position = "<<open_positions<<endl;
            }
            void acceptDetail(){
                Department::acceptDetail();
                cout<<"Enter interview Details in hr department"<<endl;
                cout<<"Number of recruiters = "<<endl;
                cin>>num_recruiters;
                cout<<"open positions = "<<endl;
                cin>>open_positions;
            }
};

class FinanceDepartment : public Department{
    private:
            double member_budget;
    
    public:
            void generateFinancialReport(){
                cout<<"financial report"<<endl;
            }

            void acceptDetail(){
                Department::acceptDetail();
                cout<<"finance Department Budget"<<endl;
                cout<<"Enter member budget = "<<endl;
                cin>>member_budget;
            }

            void display(){
                Department::display();
                cout<<"finance department details"<<endl;
                cout<<"Member budget = "<<member_budget<<endl;
            }
};



int main(){
    Department *baseptr;
     int ch;
    
    do
    {    cout<<"\n\nEnter Choice:\n1.HR Department \n2.Finance Department \n3.Exit\n\n";
        cin>>ch;
    switch (ch)
    {
    case 1:
        baseptr = new HrDepartment();
        baseptr->acceptDetail();
        baseptr->display();
        if(typeid(*baseptr) == typeid(HrDepartment)){
            HrDepartment *obj = dynamic_cast<HrDepartment*>(baseptr);
            obj->conductInterviews();
        }else{
            cout<<"invalid type!"<<endl;
        }
        delete baseptr;
        break;
    case 2:
        baseptr = new FinanceDepartment();
        baseptr->acceptDetail();
        baseptr->display();
        if(typeid(*baseptr) == typeid(FinanceDepartment)){
            FinanceDepartment *obj = dynamic_cast<FinanceDepartment*>(baseptr);
            obj->generateFinancialReport();
        }
        delete baseptr;
        break;
    
    case 3:
        cout<<"exit"<<endl;
        break;
    
    default:
        break;
    }
    } while (ch!=3);
    

}