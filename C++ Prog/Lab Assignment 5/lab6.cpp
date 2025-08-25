/*

1 Solve this.
Fresh business scenario to apply inheritance , polymorphism   to emp based organization scenario.

Create Emp based organization structure --- Emp , Mgr , Worker


1.1 Emp state--- id(int), name, deptId , basicSalary(double)
Accept all of above in constructor arguments.

Methods ---
1.2. compute net salary ---ret 0
(eg : public double computeNetSalary(){return 0;})

1.2 Mgr state  ---id,name,basic,deptId , perfBonus
Add suitable constructor
Methods ----
1. compute net salary (formula: basic+perfBonus) -- override computeNetSalary


1.3 Worker state  --id,name,basic,deptId,hoursWorked,hourlyRate
Methods : 
1.  compute net salary (formula:  = basic+(hoursWorked*hourlyRate) --override computeNetSalary
2. get hrlyRate of the worker  -- add a new method to return hourly rate of a worker.(getter)

Create suitable array to store organization details.
Provide following options
1. Hire Manager
I/P : all manager details

2. Hire Worker  
I/P : all worker details

3. Display information of all employees net salary (by invoking computeNetSal), 

4. Exit
----------------------------------------------------

*/

/*
#include <iostream>
using namespace std;

class Employee {
private:
    int id;
    string name;
    int deptid;

protected:
    double basicSalary;

public:
    Employee() {
        id = 0;
        name = "Default";
        deptid = 0;
        basicSalary = 0;
    }
    Employee(int id, string name, int deptid, double basicSalary) {
        this->id = id;
        this->name = name;
        this->deptid = deptid;
        this->basicSalary = basicSalary;
    }

    virtual double computeNetSalary() {   // ✅ make virtual
        cout << "\t\tEmployee Salary: " << basicSalary << endl;
        return basicSalary;
    }

    virtual void display() {
        cout << "Employee Details" << endl;
        cout << "ID = " << id << endl;
        cout << "Name = " << name << endl;
        cout << "DeptID = " << deptid << endl;
        cout << "Basic Salary = " << basicSalary << endl;
    }

    // virtual destructor (good practice with inheritance)
    virtual ~Employee() {}
};

class Manager : public Employee {
private:
    double perfBonus;

public:
    Manager() : Employee() {
        perfBonus = 0;
    }

    Manager(int id, string name, int deptid, double basicSal, double perfBonus)
        : Employee(id, name, deptid, basicSal) {
        this->perfBonus = perfBonus;
    }

    double computeNetSalary() override {   // ✅ override
        double ManagerSalary = basicSalary + perfBonus;
        cout << "\t\tManager Salary: " << ManagerSalary << endl;
        return ManagerSalary;
    }

    void display() override {
        Employee::display();
        cout << "Performance Bonus = " << perfBonus << endl;
    }
};

class Worker : public Employee {   // ✅ public inheritance
private:
    int hoursWorked;
    double hourlyRate;

public:
    Worker() : Employee() {
        hoursWorked = 0;
        hourlyRate = 0;
    }

    Worker(int id, string name, int deptid, double basicSal, int hoursWorked, double hourlyRate)
        : Employee(id, name, deptid, basicSal) {
        this->hoursWorked = hoursWorked;
        this->hourlyRate = hourlyRate;
    }

    double computeNetSalary() override {   // ✅ override
        double WorkerSalary = basicSalary + (hoursWorked * hourlyRate);
        cout << "\t\tWorker Salary: " << WorkerSalary << endl;
        return WorkerSalary;
    }

    double getHourlyRate() {   // ✅ worker-specific method
        return hourlyRate;
    }

    void display() override {
        Employee::display();
        cout << "Hours Worked = " << hoursWorked << endl;
        cout << "Hourly Rate = " << hourlyRate << endl;
    }
};


int main(){
    Employee emp1(101,"Vaishali","it",45000);
    emp1.display();
    emp1.computeNetSalary();

    cout<<"\n\n\t\t------------Manager----------"<<endl;

    Manager mgr(201,"Rahul",60000,"IT",30000);
    mgr.computeNetSalary();//parent class
    // mgr.assignTask();
    mgr.display();//Employee

    cout<<"\n\n\t\t------------SalesPerson----------"<<endl;
    Worker wk(303,"Manoj",40000,5000,5000,5000);
    wk.computeNetSalary();
    wk.display();
    // sp.sendReport();

}

*/












/*

2:Create cpp application for bank account handling.
2.1. Create a class BankAccount -- acct no(int),customer name(string),balance(double)
Add  constr. (2 constrs : first to accept all details )

2.2 Add Business logic methods
Methods
public void withdraw(double amt) 
public void deposit(double amt)

2.3: Create object of account class and test withdraw and deposit methods.
---------------------------------------------------------------------------------

*/

#include<iostream>
using namespace std;

class BankAccount {
        private:
            int accNo;
            string custName;
            double balance;

        public:
            BankAccount() {
                accNo = 0;
                custName = "xyz";
                balance = 0.0;
            }

            
            BankAccount(int accNo, string custName, double balance) {
                this->accNo = accNo;
                this->custName = custName;
                this->balance = balance;
            }

            
            void withdraw(double amt) {
                if (amt > 0 && amt <= balance) {
                    balance = balance - amt;
                    cout << "Withdrawn: " << amt << " Balance: " << balance << endl;
                } else {
                    cout << "Invalid withdrawal amount or Insufficient funds!" << endl;
                }
            }

            
            void deposit(double amt) {
                if (amt > 0) {
                    balance = balance + amt;
                    cout << "Deposited: " << amt << "  Balance: " << balance << endl;
                } else {
                    cout << "Invalid" << endl;
                }
            }


            // Display account info
            void display() {
                cout << "\n\t\t-------Account Details-------" << endl;
                cout << "Account No: " << accNo << endl;
                cout << "Customer Name: " << custName << endl;
                cout << "Balance: " << balance << endl;
            }
};

int main() {
    BankAccount acc1(101, "Akshat", 500000.0);

    acc1.display();

    
    acc1.deposit(2000);
    acc1.withdraw(3000);

    acc1.withdraw(100000000);

    return 0;
}
