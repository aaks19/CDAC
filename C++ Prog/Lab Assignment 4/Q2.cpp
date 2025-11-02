// Create a class Person with data members as name, age, city. Write getters and setters for all the data 
// members. Also add the display function. Create Default and Parameterized constructors. Create the 
// object of this class in main method and invoke all the methods in that class. 

#include<iostream>
using namespace std;

class person{
    private:
        string name;
        int age;
        string city;
    
    public:
        person(){
            name = "abc";
            age = 0;
            city = "xyz";
        }
        person(string name, int age, string city){
            this->name = name;
            this->age = age;
            this->city = city;

        }
        int display(){
            cout<<"name: "<<name<<endl;
            cout<<"age: "<<age<<endl;
            cout<<"city: "<<city<<endl;
            return 0;
        }

        // Getter
        string getName(){
            return name;
        }
        int getAge(){
            return age;
        }
        string getCity(){
            return city;
        }

        // setter

        void setName(string newName){
            this->name = newName;
        }

        void setAge(int newAge){
            this->age = newAge;
        }

        void setCity(string newCity){
            this->city = newCity;
        }
};

int main(){
    person obj_p; 
    obj_p.display();

    person obj_p2("Akshat", 22, "Durg");
    obj_p2.display();

    obj_p.setName("Aryan");
    obj_p.setAge(21);
    obj_p.setCity("Bhilai");
    obj_p.display();
    
    
    return 0;
}