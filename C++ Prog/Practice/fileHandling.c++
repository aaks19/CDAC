#include<iostream>
#include<fstream>
#include<string.h>
using namespace std;


class Employee{
    private:
            int id;
            char name[30];
            double salary;

    public:
            Employee(){
                id = 0;
                name[0]= '\0';
                salary = 0.0;
            }
            Employee(int id, char *nm, double salary){
                this->id = id;
                this->salary = salary;
                strcpy(name, nm);
            }

            void accept(){
                cout<<"Enter details: "<<endl;
                cout<<"Enter id: "<<endl;
                cin>>id;
                cout<<"Enter name: "<<endl;
                cin>>name;
                cout<<"Enter salary: "<<endl;
                cin>>salary;
            }

            void display(){
                cout<<"Employee details"<<endl;
                cout<<"ID: "<<id<<endl;
                cout<<"name: "<<name<<endl;
                cout<<"Salary: "<<salary<<endl;
            }
            int getId(){
                return id;
            }
            char* getName(){
                return name;
            }
            double getSalary(){
                return salary;
            }

            void setName(char* nm){
                strcpy(name,nm);
            }
            void setSalary(double sal){
                salary = sal;
            }
};


//write in a binary file...

void addEmpDetail(){
    Employee e;
    e.accept();

    ofstream fout("employee.dat",ios::binary|ios::app);
    fout.write((char*)&e, sizeof(Employee));
    fout.close();
    cout<<"Employee details written successfully..."<<endl;
}


//read from a binary file

void readEmpDetail(){
    Employee e;
    ifstream fin("employee.dat",ios::binary);
    if(!fin.fail()){
        while(fin.read((char*)&e, sizeof(Employee))){
            e.display();
        }
    }

    fin.close();
}

//search employee
void searchDetail(int id){
    Employee e;
    ifstream fin("employee.dat",ios::binary);
    bool found = false;
    while(fin.read((char*)&e, sizeof(Employee))){
        if(e.getId()==id){
            e.display();
            found = true;
            break;
        }
    }
    fin.close();
    if(!found) cout<<"Not found..."<<endl;
}


//update details
void updateDetails(int id){
    fstream fs("employee.dat",ios::binary|ios::in|ios::out);
    Employee e;
    while(fs.read((char*)&e, sizeof(Employee))){
        if(e.getId()==id){
            char newname[30];
            double newsal;
            cout<<"Enter new name:"<<endl;
            cin>>newname;
            cout<<"Enter Salary: "<<endl;
            cin>>newsal;
            e.setName(newname);
            e.setSalary(newsal);

            long pos = -1 * (long)sizeof(Employee);
            fs.seekp(pos, ios::cur);
            fs.write((char*)&e, sizeof(Employee));
            cout<<"Details updated"<<endl;
        }
    }
    fs.close();

}


//display
void deleteDetail(int id){
    ifstream fin("employee.dat",ios::binary);
    ofstream fout("employee.dat", ios::binary);

    bool found = false;
    Employee e;
    while(fin.read((char*)&e, sizeof(Employee))){
        if(e.getId() != id ){
            fout.write((char*)&e, sizeof(Employee));
        }else{
            found= true;
        }
    }
    fin.close();
    fout.close();
}

 
int main(){
    int ch,id;
    do
    {
        cout<<"Enter choice \n1:wite \n2:read  \n3:search \n4:update emp \n5:delete  \n0:exit"<<endl;
        cin>>ch;
        switch (ch)
        {
        case 1:
            cout<<"write"<<endl;
            addEmpDetail();
            break;
        case 2:
            cout<<"Read"<<endl;
            readEmpDetail();
            break;
        case 3:
            
            cout<<"Enter id: "<<endl;
            cin>>id;
            searchDetail(id);
            break;
        case 4:
            
            cout<<"Enter id: "<<endl;
            cin>>id;
            updateDetails(id);
            break;
        case 5:
            
            cout<<"Enter id: "<<endl;
            cin>>id;
            deleteDetail(id);  
            break;
        case 0:
            cout<<"Exit..."<<endl;
            break;
        default:
            break;
        }
    } while (ch!=0);
    
} 