// Create a class Date with data members as dd, mm, yy. Write getters and setters for all the data members. Also add the display function. 
// Create Default and Parameterized constructors. Create the object of this class in main method and invoke all the methods in that class. 


#include<iostream>
using namespace std;

class Date{
    private:
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

int main(){
    Date obj_d;
    cout<<"Default date"<<endl;
    obj_d.display();
    cout<<endl;

    cout<<"parameterized date"<<endl;
    Date obj_d1(19,6,2003);
    obj_d1.display();

    cout<<endl;

    cout<<"Enter new date: ";
    int newDate;
    cin>>newDate;
    cout<<"Enter new month: ";
    int newMonth;
    cin>>newMonth;
    cout<<"Enter new year: ";
    int newYear;
    cin>>newYear;

    obj_d1.setDate(newDate);
    obj_d1.setMonth(newMonth);
    obj_d1.setYear(newYear);

    cout<<"update using setter"<<endl;

    obj_d1.display();

    return 0;
}