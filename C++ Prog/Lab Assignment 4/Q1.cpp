// 1: Write a program to create student class with data members rollno, marks1,mark2,mark3.
// Accept data (acceptInfo()) and display  using display member function.
// Also display total,percentage and grade.

#include<iostream>
using namespace std;

class student{
    private:
        int rollno,marks1,marks2,marks3;

    public:
        void acceptInfo(){
            cout<<"Enter rollno: ";
            cin>>rollno;
            cout<<"Enter marks1: ";
            cin>>marks1;
            cout<<"Enter marks2: ";
            cin>>marks2;
            cout<<"Enter marks3: ";
            cin>>marks3;
        }

        int displayMember(){
            cout<<"Rollno: "<<rollno<<endl;
            cout<<"marks1: "<<marks1<<endl;
            cout<<"marks2: "<<marks2<<endl;
            cout<<"marks3: "<<marks3<<endl;

            float total = marks1+marks2+marks3;
            float perc = (total/300)*100;
            cout<<"Total: "<<total<<endl;
            cout<<endl;
            cout<<"Percentage: "<<perc<<endl;
            return 0;
        }
};

// void Student ::  acceptInfo(){
            // cout<<"Enter rollno: ";
            // cin>>rollno;
            // cout<<"Enter marks1: ";
            // cin>>marks1;
            // cout<<"Enter marks2: ";
            // cin>>marks2;
            // cout<<"Enter marks3: ";
            // cin>>marks3;
            
//         }

int main(){
    student stud;
    stud.acceptInfo();
    stud.displayMember();
}
