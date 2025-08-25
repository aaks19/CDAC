// Create a class Book with data members as bname,id,author,price. Write getters and setters for all the 
// data members. Also add the display function. Create Default and Parameterized constructors. Create 
// the object of this class in main method and invoke all the methods in that class. 

#include <iostream>
using namespace std;

class Book{

    private:
            string bname;
            int id;
            string author;
            double price;
    public: 
            Book(){
                bname = "default";
                id = 0;
                author = "default";
                price = 0;
            }

            Book(string bname, int id, string author, double price){
                this->bname = bname;
                this->id = id;            
                this->author = author;
                this->price = price;
            }

            void display(){
                cout<<"\t\t--------------Book Details----------------"<<endl;
                cout<<"book name = "<<bname<<endl;
                cout<<"book id = "<<id<<endl;
                cout<<"book author = "<<author<<endl;
                cout<<"book price = "<<price<<endl;
            }

            //getter

            string getbookName(){
                return bname;
            }
            int getbookId(){
                return id;
            }
            string getAuthorName(){
                return author;
            }
            double getbookPrice(){
                return price;
            }

            //setter

            void setbookName(string newbookName){
                this->bname = newbookName;
            }
            void setbookId(int newbookId){
                this->id = newbookId;
            }
            void setAuthorName(string newAuthor){
                this->author = newAuthor;
            }
            void setbookPrice(double newbookPrice){
                this->price = newbookPrice;
            }

};


int main(){

    Book b1;
    cout<<"\t\t---------------Default values--------------"<<endl;
    b1.display();
    cout<<endl;
    
    cout<<"\t\t---------------Parameterized constructor--------------"<<endl;
    Book b2("mera book",101,"Akshat",1000000);
    b2.display();

    cout<<"Enter new book name : ";
    string newbookName;
    cin>>newbookName;

    cout<<"Enter new book Id : ";
    int newbookId;
    cin>>newbookId;

    cout<<"Enter new author name : ";
    string newAuthorName;
    cin>>newAuthorName;

    cout<<"Enter new book price : ";
    double newbookPrice;
    cin>>newbookPrice;

    b2.setbookName(newbookName);
    b2.setbookId(newbookId);
    b2.setAuthorName(newAuthorName);
    b2.setbookPrice(newbookPrice);
    cout<<"\n\n\t\t------------updated details------------"<<endl;
    b2.display();
}
