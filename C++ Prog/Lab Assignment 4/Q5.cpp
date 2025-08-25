// 4. Create a class Point with data members as x,y. Create Default and Parameterized constructors. Write 
// getters and setters for all the data members. Also add the display function. Create the object of this 
// class in main method and invoke all the methods in that class. 

// #include<iostream>
// using namespace std;

// class Point{
//     private:
//             int x, y;
//     public:
//             Point(){
//                 x = 0;
//                 y = 0;
//             }

//             Point(int x, int y){
//                 this->x = x;
//                 this->y = y;
//             }

//             void display(){
//                 cout<<"X = "<<x<<endl;
//                 cout<<"Y = "<<y<<endl;
//             }

//             //getter

//             int get_x(int new_x){
//                 return x;
//             }
//             int get_y(int new_y){
//                 return y;
//             }

//             //setter

//             void set_x(int new_x){
//                 this->x = new_x;
//             }
//             void set_y(int new_y){
//                 this->y = new_y;
//             }

// };

// int main(){
//     Point pt;
//     cout<<"\t\t-------------default value----------"<<endl;
//     pt.display();

//     Point pt1(10,20);
//     cout<<"\t\t-------------Parameterized value----------"<<endl;
//     pt1.display();

//     cout<<"Enter new value of x = "<<endl;
//     int new_x;
//     cin>>new_x;

//     cout<<"Enter new value of y = "<<endl;
//     int new_y;
//     cin>>new_y;

//     pt1.set_x(new_x);
//     pt1.set_y(new_y);

//     cout<<"\n\t\t--------updated value-------------"<<endl;
//     pt1.display();
    
// }



// 5. Create a class ComplexNumber with data members real, imaginary. Create Default and Parameterized constructors. 
// Write getters and setters for all the data members. Also add the display function. 
// Create the object of this class in main method and invoke all the methods in that class.


#include<iostream>
using namespace std;

class ComplexNumber{
    private:
            int real, imaginary;
    public:
            ComplexNumber(){
                real = 0;
                imaginary = 0;
            }

            ComplexNumber(int real, int imaginary){
                this->real = real;
                this->imaginary = imaginary;
            }

            void display(){
                cout<<"Real = "<<real<<endl;
                cout<<"Imaginary = "<<imaginary<<endl;
            }

            //getter

            int get_real(int new_real){
                return real;
            }
            int get_imaginary(int new_imaginary){
                return imaginary;
            }

            //setter

            void set_real(int new_real){
                this->real = new_real;
            }
            void set_imaginary(int new_imaginary){
                this->imaginary = new_imaginary;
            }

};

int main(){
    ComplexNumber cn;
    cout<<"\t\t-------------default value----------"<<endl;
    cn.display();

    ComplexNumber cn1(10,20);
    cout<<"\t\t-------------Parameterized value----------"<<endl;
    cn1.display();

    cout<<"Enter new value of real = "<<endl;
    int new_real;
    cin>>new_real;

    cout<<"Enter new value of imaginary = "<<endl;
    int new_imaginary;
    cin>>new_imaginary;

    cn1.set_real(new_real);
    cn1.set_imaginary(new_imaginary);

    cout<<"\n\t\t--------updated value-------------"<<endl;
    cn1.display();
    
}