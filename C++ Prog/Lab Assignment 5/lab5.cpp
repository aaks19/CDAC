/*
1:Create Date class with members day,month ,year.Write no argument and parameterised constructor .
Create two object s and initialize them using no argument and parameterised constructor
respectively.Print date using display function.               
*/

#include<iostream>
using namespace std;

class Date{
    protected:
        int date,month,year;

    public:
        Date(){
            date=1;
            month=1;
            year=2000;
        }
        Date(int date,int month, int year){
            this->date = date;
            this->month = month;
            this->year = year;
        }
        int display(){
            cout<<"Date: "<<date<<"/"<<month<<"/"<<year<<endl;
            return 0;
        }


        //getter
        int getDate(){
            return date;
        }
        int getMonth(){
            return month;
        }
        int getYear(){
            return year;
        }

        //setter
        void setDate(int newDate){
            this->date = newDate;
        }
        void setMonth(int newMonth){
            this->month = newMonth;
        }
        void setYear(int newYear){
            this->year = newYear;
        }

};

// int main(){
//     Date obj_d;
//     cout<<"Default date"<<endl;
//     obj_d.display();
//     cout<<endl;

//     cout<<"parameterized date"<<endl;
//     Date obj_d1(19,6,2003);
//     obj_d1.display();

//     cout<<endl;

//     cout<<"Enter new date: ";
//     int newDate;
//     cin>>newDate;
//     cout<<"Enter new month: ";
//     int newMonth;
//     cin>>newMonth;
//     cout<<"Enter new year: ";
//     int newYear;
//     cin>>newYear;

//     obj_d1.setDate(newDate);
//     obj_d1.setMonth(newMonth);
//     obj_d1.setYear(newYear);

//     cout<<"update using setter"<<endl;

//     obj_d1.display();

//     return 0;
// }



/*
2:Create Employee class with members id(int),name(string),dob(Date).Use above created Date class.
Write default and parameterised constructor in Employee Class.
Write accept() function to accept information and display() to display emp information.
*/



// class Employee : public Date {
// private:
//     int id;
//     string name;

// public:
//     Employee() : Date() {
//         id = 1;
//         this->name = "ABC";   
//     }

//     Employee(int id, string name, int date, int month, int year)
//         : Date(date, month, year) {
//         this->id = id;
//         this->name = name;
//     }

//     void accept() {   
//         cout << "Enter id: ";
//         cin >> this->id;
//         cout << "Enter name: ";
//         cin >> this->name;
//         cout << "Enter date: ";
//         cin >> this->date;
//         cout << "Enter month: ";
//         cin >> this->month;
//         cout << "Enter year: ";
//         cin >> this->year;
//     }

//     void display() {
//         cout << "ID = " << this->id << endl;
//         cout << "Name = " << this->name << endl;
//         cout << "DOB = " << date << "/" << month << "/" << year << endl;
//     }
// };

// int main() {
//     cout << "----Default Employee----" << endl;
//     Employee e1;
//     e1.display();

//     cout << "\n----Parameterized Employee----" << endl;
//     Employee e2(101, "pata ni", 19, 6, 2003);
//     e2.display();

//     cout << "\n----Accept Employee----" << endl;
//     Employee e3;
//     e3.accept();
//     e3.display();

//     return 0;
// }



/*
3:Consider that payroll software needs to be developed for computerization of
operations of an ABC organization. The organization has employees.
3.1. Construct a class Employee with following members using private access
specifies:
 Employee Id integer
 Employee Name string
 Basic Salary double
 HRA double
 Medical double=1000
 PF double
PT double
 Net Salary double
 Gross Salary double
Please use following expressions for calculations://Note:Don't accept HRA,PF PT from user
 * HRA = 50% of Basic Salary
* PF = 12% of Basic Salary
* PT = Rs. 200

3.2. Write methods to display the details of an employee and calculate the gross
and net salary.
* Goss Salary = Basic Salary + HRA + Medical
* Net Salary = Gross Salary – (PT + PF)

Create Object of employee class and assign values and display Details.
*/



#include<iostream>
using namespace std;

class Employee{
private:
		int id;
		string name;
		double basic_salary;
		double HRA;
		double Medical = 1000;
		double PF;
		double PT;
		double net_salary;
		double gross_salary;
			
public:
		Employee()
		{
			id = 121;
			name = "xyz";
			basic_salary=0000;
			net_salary=0000;
			gross_salary=0000;
		}
		Employee(int id,string name,double basic_salary)
		{
			this->id = id;
			this->name = name;
			this->basic_salary = basic_salary;
		}
		void accept(){
			cout<<"enter your ID: "<<endl;
			cin>>id;
			cout<<"enter your Name: "<<endl;
			cin>>name;
			cout<<"enter your Basic salary: "<<endl;
			cin>>basic_salary;
		}
		
		void display(){
			cout<<"ID: "<<id<<endl;
			cout<<"name: "<<name<<endl;
			cout<<"Basic Salary: "<<basic_salary<<endl;
			
			HRA = 0.5 * basic_salary;
			PF = 0.12 * basic_salary;
			PT = 200;
			Medical = 1000;
			
			gross_salary = basic_salary + HRA + Medical;
			net_salary = gross_salary - (PT + PF);
			
			cout<<"Gross salary: "<<gross_salary<<endl;
			cout<<"Net salary: "<<net_salary<<endl;
		}
		
};



int main(){
	Employee e;
	e.accept();
    cout<<"\n\n\t\tCalculate net salary and gross salary"<<endl;
	e.display();
	
}
