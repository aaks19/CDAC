// 2:Create an application for storing user information in vector.
//   (Hint:User class with data member userid,name,email,pwd)
//   Create Menu Driven app
//   1:add user
//   2:display all users
//   3:search user
//   4:change pwd
//   5:delete all

// #include<iostream>
// #include<vector>
// using namespace std;

// class User{
//     private: int id; string name; string email; int pwd;

//     public:
//         User(){
//             id = 0;
//             name = "unknown";
//             email = "unknown";
//             pwd = 0000;
//         }
//         User(int id, string name, string email, int pwd){
//             this->id = id;
//             this->name = name;
//             this->email = email;
//             this->pwd = pwd;
//         }

//         void display(){
//             cout<<"User id = "<<id<<endl;
//             cout<<"name = "<<name<<endl;
//             cout<<"email = "<<email<<endl;
//             cout<<"password = "<<pwd<<endl;
//             cout<<"\n\n\n\n"<<endl;
//         }

//         int getid(int id){
//             return this->id;
//         }
//         string getname(string name){
//             return this->name;
//         }
//         string getemail(string email){
//             return this->email;
//         }
//         int getpwd(int pwd){
//             return this->pwd;
//         }

//         void setpwd(int pwd){
//             this->pwd = pwd;
//         }


// };

// int main(){

//     vector<User> usr;
//     int id,pwd;
//     string name,email;

//     User *newUsr;

    
//     int ch;
    
//     do
//     {   
//         cout<<"\t\t\tEnter choice:\n1.Add user\n2.Display all user\n3.Search User\n4.Change pwd.\n5.Delete all.\n6.Exit\n\n";
//         cin>>ch;
//         switch (ch)
//         {
//         case 1:
//             cout<<"Enter id = "<<endl;
//             cin>>id;
//             cout<<"Enter name = "<<endl;
//             cin>>name;
//             cout<<"Enter email = "<<endl;
//             cin>>email;
//             cout<<"Enter pwd = "<<endl;
//             cin>>pwd;
//             newUsr = new User(id,name,email,pwd);
//             usr.push_back(*newUsr);
//             cout<<"Added\n\n"<<endl;
//             break;
//         case 2:
//             for(User u:usr){
//                 u.display();
//             }
//             break;
        
//         case 3:
//             cout<<"Enter name to search = "<<endl;
//             cin>>name;
//             for(User u:usr){
//                 if(u.getname(name) == name){
//                     u.display();
//                 }
//             }
//             break;
        
//         case 4:
//             cout<<"Enter id to change pwd = "<<endl;
//             cin>>id;
//             for(User u:usr){
//                 if(u.getid(id) == id){
//                     cout<<"Enter new pwd = "<<endl;
//                     cin>>pwd;
//                     u.setpwd(pwd);
//                     cout<<"Password changed\n\n"<<endl;
//                 }
//             }
//             break;
        
//         case 5:
//             usr.clear();
//             cout<<"All users deleted\n\n"<<endl;
//             break;
        
//         case 6:
//             cout<<"Exit"<<endl;
//             break;  
        
//         default:
//             break;
//         }
//     } while (ch!=6);


//     return 0;
// }










// 3:Create an application using set .
//   Accept name of city from user and store in set
//   Create Menu drivien app
//   1:add city
//   2:display  all city
//   3: serach city



// #include<iostream>
// #include<set>
// using namespace std;

// int main(){
//     set<string> citySet;
//     string city;
//     string findCity;
//     bool flag=false;

//     cout<<"1.Add city\n2.Display\n3.Search\n4.Exit"<<endl;
//     int ch;
    

//     do
//     {
//         cout<<"Enter choice: "<<endl;
//         cin>>ch;
//       switch(ch)
//         {
//         case 1:
            
//             cout<<"Enter city: ";
//             cin>>city;
//             //add
//             citySet.insert(city);
//             break;

//         case 2:
//             //display
//             for(auto i: citySet){
//                 cout<<i<<" "<<endl;
//             }
//             break;
        
//         case 3:
//             //search
            
//             cout<<"Enter city to search: "<<endl;
//             cin>>findCity;
//             flag=false;
//             for(string s : citySet)
//             {
//                 if(findCity==s)
//                 {   flag=true;
//                     cout<<"found"<<endl;
//                     break;
//                 }
//             }
//             if(flag==false)
//             {
//                 cout<<"not found"<<endl;
//             }

            
            
//             break;

//         case 4:
//             cout<<"Exit"<<endl;
//             break;

//         default:
//             cout<<"invalid cjoice"<<endl;
//         }  
//     } while (ch!=4);

//     return 0;
// }







// 4:Create an application using map for storing key and value
//    key:int
//    value:Account type
//    Create Account class with actid ,name,balance
//    Create Menu driven app
//    1:Add Account
//    2:Display all
//    3:Search account by actid;
//    4:Remove all


// #include<iostream>
// #include<map>
// using namespace std;

// class Account{
//     private: 
//                 int actid;
//                 string name;
//                 double balance;

//     public:
//             Account(){
//                 actid = 0;
//                 name = "xyz";
//                 balance = 0.0;
//             }

//             Account(int id, string name, double balance):
//             actid(id),name(name),balance(balance){}

//             void display(){
//                 cout<<"Account id: "<<actid<<endl;
//                 cout<<"Name: "<<name<<endl;
//                 cout<<"Balance: "<<balance<<endl;
//             }
// };

// int main(){
//     map<int, Account> accMap;
//     int ch;
//     cout<<"Enter choice: "<<endl;
//     cout << "\n===== Menu =====" << endl;
//     cout << "1. Add Account" << endl;
//     cout << "2. Display All Accounts" << endl;
//     cout << "3. Search Account by ID" << endl;
//     cout << "4. Remove All Accounts" << endl;
//     cout << "5. Exit" << endl;

//     do
//     {
//       cin>>ch;
//         if (ch==1)
//         {
//             int id;
//             string name;
//             double balance;

//             cout<<"Enter id: "<<endl;
//             cin>>id;
//             cout<<"Enter name: "<<endl;
//             cin>>name;
//             cout<<"Enter balance: "<<endl;
//             cin>>balance;

//             accMap.insert({id, Account(id, name, balance)});
//         }else if(ch == 2){
//             if (accMap.empty()) {
//                     cout << "No accounts available." << endl;
//             }else {
//                 for (auto &p : accMap) {
//                     p.second.display();
//                 }
//             }
//         }else if(ch == 3){
//             int searchid;
//             cout<<"Enter id to search";
//             cin>>searchid;
//             auto it = accMap.find(searchid);
//             if(it != accMap.end()){
//                 it->second.display();
//             }else{
//                 cout<<"not found"<<endl;
//             }
//         }else if (ch == 4) {
//             accMap.clear();
//             cout << "All accounts removed successfully." << endl;

//         } else if (ch == 5) {
//             cout << "Exiting..." << endl;

//         } else {
//             cout << "Invalid choice. Try again!" << endl;
//         }
//     } while (ch!=5);
    
//     return 0;
// }







// 5: Create an File IO application for basic operation 
//    1:Write file:accept data from user and store in file
//    2:Read file:display line by line
//    3:copy data from one file into another file

// #include<iostream>
// #include<fstream>
// using namespace std;

// void writeFile(){
//     cout<<"Writing in file"<<endl;
//     string filename = "myFile.txt";
//     ofstream outfile(filename, ios::app);
//     cout<<"enter name of user:"<<endl;
//     string name;
//     cin>>name;
//     outfile<<name<<endl;

//     outfile.close();
// }


// void readFile(){
//     cout<<"read data from file"<<endl;
//     string filename = "myFile.txt";

//     ifstream inputfile(filename);
//     string line;
//     if(inputfile.fail()){
//         cout<<"file not found!"<<endl;
//     }else{
//         cout<<"read from file"<<endl;
        
//         while(getline(inputfile, line)){
//             cout<<line<<endl;
//         }
//     }

//     inputfile.close();
// }


// void copyFile(){
//     cout << "Copying data from one file into another" << endl;
//     string sourceFile = "myFile.txt";
//     string destFile = "copyFile.txt";

//     ifstream src(sourceFile);
//     ofstream dest(destFile);

//     if(src && dest){
//         string line;
//         while(getline(src, line)){
//             dest << line << "\n";
//         }
//         cout << "Copy Finished" << endl;
//     } else {
//         cout << "Error: Cannot open source or destination file!" << endl;
//     }
//     src.close();
//     dest.close();
// }


// int main(){
//     int ch;
//     do {
//         cout << "\n===== Menu =====" << endl;
//         cout << "1. Write File" << endl;
//         cout << "2. Read File" << endl;
//         cout << "3. Copy File" << endl;
//         cout << "0. Exit" << endl;
//         cout << "Enter choice: ";
//         cin >> ch;

//         switch (ch){
//             case 1: writeFile(); break;
//             case 2: readFile(); break;
//             case 3: copyFile(); break;
//             case 0: cout << "Exiting..." << endl; break;
//             default: cout << "Invalid choice!" << endl;
//         }
//     } while(ch != 0);

//     return 0;
// }




/*

6: Create CRUD Shop Application Using  File
   Write class Product with data member prdid,name,qty,price;
    Menus:
    1:Add Prd
    2:Display Prds
    3:Search Prd
    4:Update/Modify prd
    5:delete prd

*/

#include<iostream>
#include<fstream>
using namespace std;

class Product{
    private: 
            int prdid;
            string name;
            int qty;
            double price;

    public:
            Product(){
                prdid = 0;
                name = "xyz";
                qty = 0;
                price = 0.0;
            }

            Product(int prdid, string name, int qty, double price){
                this->prdid = prdid;
                this->name = name;
                this-> qty = qty;
                this -> price = price;
            }

            void display(){
                cout<<"product details: "<<endl;
                cout<<"product id: "<<prdid<<endl;
                cout<<"product name: "<<name<<endl;
                cout<<"product quantity: "<<qty<<endl;
                cout<<"product price: "<<price<<endl;
            }
            int getid(){
                return prdid;
            }
};


//writing in file

void writeFile(){
    cout<<"Writing data in file"<<endl;
    string filename = "product_detail.dat";
    ofstream out_file(filename, ios::app);

    cout<<"product details: "<<endl;
    cout<<"------------------------------------------------------------------"<<endl;
    cout<<"product id: "<<endl;
    int prdid;
    cin>>prdid;
    out_file<<prdid<<endl;

    cout<<"product name: "<<endl;
    string name;
    cin>> name;
    out_file<<name<<endl;

    cout<<"product quantity: "<<endl;
    int qty;
    cin>> qty;
    out_file<<qty<<endl;

    cout<<"product price: "<<endl;
    double price;
    cin>> price;
    out_file<<price<<endl;

    out_file.close();

}


//reading file

void readFile(){
    cout<<"Product Details"<<endl;
    string filename = "product_detail.dat";

    ifstream in_file(filename);
    string line;

    if(in_file.fail()){
        cout<<"Error occured";
    }else{
        cout<<"Reading file"<<endl;
        while(getline(in_file, line)){
            cout<<line<<endl;
        }
    }


    in_file.close();
}


//Searching

void search(int prdid){
    ifstream fin;
    Product p ;
    fin.open("product_detail.dat",ios::binary);

    while(fin.read((char*)&p, sizeof(Product))){
        if(p.getid() == prdid){
            p.display();
        }
    }

    fin.close();
}




//update detail

void update(int prdid){
    fstream fin;
    Product p;
    fin.open("product_detail.dat",ios::binary | ios::in | ios::out);
    long no = -1L*static_cast<long>(sizeof(Product));

    while(fin.read((char*)&p, sizeof(Product))){
        if(p.getid()==prdid){
            cout<<"old value"<<endl;
            p.display();
            cout<<"-----------------------------------------------"<<endl;

            cout<<"Enter new product details: product_id, name, quantity, price"<<endl;
            int prdid;
            string name;
            int qty;
            double price;
            cin>>prdid>>name>>qty>>price;
            Product p1(prdid, name, qty, price);
            fin.seekg(no, ios::cur);

            fin.write((char*)&p1, sizeof(Product));
            cout<<"Updated successfully  "<<no<<endl;

            
        }
    }

    fin.close();
}



int main(){
    int ch,prdid;
    cout<<"\t1.1:Add Prd\n2:Display Prds\n3:Search Prd\n4:Update/Modify prd"<<endl;

    do
    {
        cout<<"Enter choice:"<<endl;
        cin>>ch;

        switch (ch)
        {
        case 1:
            writeFile();
            break;
        case 2:
            readFile();
            break;
        case 3:
            cout<<"enter id to search:  ";
            cin>>prdid;
            search(prdid);
            break;
        case 4:
            cout<<"update the details"<<endl;
            cin>>prdid;
			update(prdid);
			break;
        case 5:{
                int cnt = 0;
                fstream file("product_detail.dat", ios::out | ios::in | ios::binary);
                file.seekg(0, ios::end);
                long no = file.tellg();
                cnt = no / sizeof(Product);
                cout<<"---count--->"<<cnt<<endl;
                break;
      		    
            }
        case 6:
            cout<<"Exit"<<endl;

        default:
            break;
        }
    } while (ch!=6);
    

    return 0;
}