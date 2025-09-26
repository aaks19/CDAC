#include<iostream>
#include<exception>
using namespace std;

class MyCustomException : public std::exception{
    private:
            char *message;
    public:
            MyCustomException(char *msg):message(msg){}
                char * what(){
                    return message;
                }
            
                
};

int main(){
    try{
        throw MyCustomException("Custom Exception...");
    }
    catch(MyCustomException &mce){
        cout<<"custom exception..."<<mce.what()<<endl;
    }
}

