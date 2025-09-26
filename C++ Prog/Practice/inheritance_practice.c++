#include<iostream>
using namespace std;

class Emp {
private:
    int id;
    string name;
    int deptid;

protected:
    double basicSalary;

public: 
    Emp() {
        id = 0;
        name = "xyz";
        deptid = 0;
        basicSalary = 0.0;
    }

    Emp(int id, string name, int deptid, double basicSalary) {
        this->id = id;
        this->name = name;
        this->deptid = deptid;
        this->basicSalary = basicSalary;
    }

    // Virtual so that child methods override at runtime
    virtual void display() {
        cout << "Details" << endl;
        cout << "id = " << id << endl;
        cout << "name = " << name << endl;
        cout << "dept id = " << deptid << endl;
        cout << "Salary = " << basicSalary << endl;
    }

    virtual double computeNetSalary() {
        cout << "Salary = " << basicSalary << endl;
        return basicSalary;
    }

    // Always good to have a virtual destructor
    virtual ~Emp() {}
};

// Manager class
class Mgr : public Emp {
private:
    double perfBonus;

public:
    Mgr() : Emp() {
        perfBonus = 0.0;
    }

    Mgr(int id, string name, int deptid, double basicSalary, double perfbBonus)
        : Emp(id, name , deptid, basicSalary) {
        this->perfBonus = perfbBonus;
    }

    void display() override {
        Emp::display();
        cout << "Performance Bonus = " << perfBonus << endl;
    }

    double computeNetSalary() override {
        double managerSalary = basicSalary + perfBonus;
        cout << "Manager's Salary = " << managerSalary << endl;
        return managerSalary;
    }
};

// Worker class
class Wkr : public Emp {
private:
    double hours_Worked;
    double hourly_Rate;

public:
    Wkr() : Emp() {
        hours_Worked = 0.0;
        hourly_Rate = 0.0;
    }

    Wkr(int id, string name, int deptid, double basicSalary, double hours_Worker, double hourly_Rate)
        : Emp(id, name, deptid, basicSalary) {
        this->hours_Worked = hours_Worker;
        this->hourly_Rate = hourly_Rate;
    }

    double computeNetSalary() override {
        double worker_Salary = basicSalary + (hours_Worked * hourly_Rate);
        cout << "Worker's Salary = " << worker_Salary << endl;
        return worker_Salary;
    }

    void display() override {
        Emp::display();
        cout << "Hours Worked = " << hours_Worked << endl;
        cout << "Hourly Rate = " << hourly_Rate << endl;
    }
};

int main() {
    // Array of base class pointers
    Emp* arr[3];
    int n = 0;  // number of objects created

    arr[n++] = new Emp(101, "Akshat", 100, 85000);
    arr[n++] = new Mgr(201, "Rahul", 120, 90000, 30000);
    // arr[n++] = new Wkr(303, "Manoj", 102, 20000, 12, 100);

    cout << "\n\n\t\t------------ Employee Details ------------" << endl;

    for (int i = 0; i < n; i++) {
        arr[i]->display();
        arr[i]->computeNetSalary();
        cout << "------------------------------------\n";
    }

    // Clean up
    for (int i = 0; i < n; i++) {
        delete arr[i];
    }

    return 0;
}
