/**
Assignment: Inline Functions in C++
Task

1. Write a program using inline functions to calculate:

2. Area of a square (side × side)

3. Area of a rectangle (length × breadth)

4. Area of a circle (π × r × r, use π = 3.14159)

**/


#include<iostream>
using namespace std;

inline float square_area(float side){
    float area = side * side;
    return area;
}

inline float rectangle_area(float length, float breadth){
    float area = length * breadth;
    return area;
}

inline float circle_area(float radius){
    float area = 3.14 * radius * radius;
    return area;
}

int main(){
    float side, length, breadth, radius;
    cout<<"1. Area of square \n 2. Area of rectangle \n 3. Area of circle \n 4. Exit"<<endl;
    int ch;
    do{
        cout<<"Enter your choice : "<<endl;
    cin>>ch;
    switch(ch){
        case 1:
            cout<<"Enter side of square : ";
            cin>>side;
            cout<<"Area of square is : "<<square_area(side)<<endl;
            break;
        case 2:
            cout<<"Enter length and breadth of rectangle : ";
            cin>>length>>breadth;
            cout<<"Area of rectangle is : "<<rectangle_area(length,breadth)<<endl;
            break;
        case 3:
            cout<<"Enter radius of circle : ";
            cin>>radius;
            cout<<"Area of circle is : "<<circle_area(radius)<<endl;
            break;
        case 4:
            cout<<"Exit";
            break;
        default:
            cout<<"Invalid choice";
            break;
        }
    }while(ch!=4);
    
    return 0;
}

