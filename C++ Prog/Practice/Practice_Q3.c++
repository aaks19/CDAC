/*

Q3. Write a program to read and write on a binary file
Create a class student with data members (rollno, name, marks [3],percent)
Accept the details of student rollno,name and marks of 3 subject.
Calculate percentage of student.
Save that record in file.(read file,append record,write file).
Store at least 3 students

IMP NOTE:-Practise Problem statements which are based on String and Array.

*/


#include<iostream>
#include<fstream>
using namespace std;

class Student{
    private:
            int rollno;
            string name;
            double marks[3];
            double percent;

    public:
            Student(){
                rollno = 0;
                name = "xyz";
                percent = 0.0;
            }

            Student(int rollno, string name, double marks[3]){
                this->rollno = rollno;
                this->name = name;
                for(int i=0; i<3; i++){
                    this->marks[i] = marks[i];
                }
                percentCalc();
            }

            void percentCalc(){
                double sum = 0;
                for(int i=0; i<3; i++){
                    sum = sum + marks[i];
                }
                percent = sum / 3.0;
            }

            void display(){
                cout<<"roll no - "<<rollno<<endl;
                cout<<"name - "<<name<<endl;
                for(int i=0; i<3; i++){
                    cout<<"marks["<<i+1<<"]"<<marks[i]<<endl;
                }
                cout<<"percent - "<<percent<<"%"<<endl;
            }
};

int main(){
    int n;
    cout<<"Enter number of students - "<<endl;
    cin>>n;
    Student *s = new Student[n];

    for(int i=0; i<n; i++){
        int rollno;
        string name;
        double marks[3];
        cout<<"enter";
    }
}




