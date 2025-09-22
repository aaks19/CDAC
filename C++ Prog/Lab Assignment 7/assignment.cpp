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
//         User(int id, string name, string email, int pwd) {
//         this->id = id;
//         this->name = name;
//         this->email = email;
//         this->pwd = pwd;
//     }

//     void display() {
//         cout << "User id = " << id << endl;
//         cout << "Name = " << name << endl;
//         cout << "Email = " << email << endl;
//         cout << "Password = " << pwd << endl;
//         cout << "-----------------------------" << endl;
//     }

//     // getters
//     int getid() { return id; }
//     string getname() { return name; }
//     string getemail() { return email; }
//     int getpwd() { return pwd; }

//     // setter
//     void setpwd(int pwd) { this->pwd = pwd; }
// };

// int main() {
//     vector<User> usr;
//     int id, pwd;
//     string name, email;

//     int ch;
//     do {
//         cout << "\nEnter choice:\n"
//              << "1. Add user\n2. Display all users\n3. Search User\n"
//              << "4. Change password\n5. Delete all\n6. Exit\n";
//         cin >> ch;

//         switch (ch) {
//         case 1:
//             cout << "Enter id: ";
//             cin >> id;
//             cout << "Enter name: ";
//             cin >> name;
//             cout << "Enter email: ";
//             cin >> email;
//             cout << "Enter password: ";
//             cin >> pwd;
//             usr.push_back(User(id, name, email, pwd));
//             cout << "User added successfully.\n";
//             break;

//         case 2:
//             for (User &u : usr) {
//                 u.display();
//             }
//             break;

//         case 3:
//             cout << "Enter name to search: ";
//             cin >> name;
//             for (User &u : usr) {
//                 if (u.getname() == name) {
//                     u.display();
//                 }
//             }
//             break;

//         case 4:
//             cout << "Enter id to change password: ";
//             cin >> id;
//             for (User &u : usr) {
//                 if (u.getid() == id) {
//                     cout << "Enter new password: ";
//                     cin >> pwd;
//                     u.setpwd(pwd);
//                     cout << "Password changed successfully.\n";
//                 }
//             }
//             break;

//         case 5:
//             usr.clear();
//             cout << "All users deleted.\n";
//             break;

//         case 6:
//             cout << "Exiting program...\n";
//             break;

//         default:
//             cout << "Invalid choice! Try again.\n";
//             break;
//         }
//     } while (ch != 6);

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


#include<iostream>
#include<map>
using namespace std;

class Account{
    private: 
                int actid;
                string name;
                double balance;

    public:
            Account(){
                actid = 0;
                name = "xyz";
                balance = 0.0;
            }

            Account(int id, string name, double balance):
            actid(id),name(name),balance(balance){}

            void display(){
                cout<<"Account id: "<<actid<<endl;
                cout<<"Name: "<<name<<endl;
                cout<<"Balance: "<<balance<<endl;
            }
};

int main(){
    map<int, Account> accMap;
    int ch;
    cout<<"Enter choice: "<<endl;
    cout << "\n===== Menu =====" << endl;
    cout << "1. Add Account" << endl;
    cout << "2. Display All Accounts" << endl;
    cout << "3. Search Account by ID" << endl;
    cout << "4. Remove All Accounts" << endl;
    cout << "5. Exit" << endl;

    do
    {
      cin>>ch;
        if (ch==1)
        {
            int id;
            string name;
            double balance;

            cout<<"Enter id: "<<endl;
            cin>>id;
            cout<<"Enter name: "<<endl;
            cin>>name;
            cout<<"Enter balance: "<<endl;
            cin>>balance;

            accMap.insert({id, Account(id, name, balance)});
        }else if(ch == 2){
            if (accMap.empty()) {
                    cout << "No accounts available." << endl;
            }else {
                for (auto &p : accMap) {
                    p.second.display();
                }
            }
        }else if(ch == 3){
            int searchid;
            cout<<"Enter id to search";
            cin>>searchid;
            auto it = accMap.find(searchid);
            if(it != accMap.end()){
                it->second.display();
            }else{
                cout<<"not found"<<endl;
            }
        }else if (ch == 4) {
            accMap.clear();
            cout << "All accounts removed successfully." << endl;

        } else if (ch == 5) {
            cout << "Exiting..." << endl;

        } else {
            cout << "Invalid choice. Try again!" << endl;
        }
    } while (ch!=5);
    
    return 0;
}







// 5: Create an File IO application for basic operation 
//    1:Write file:accept data from user and store in file
//    2:Read file:display line by line
//    3:copy data from one file into another file

#include<iostream>
#include<fstream>
using namespace std;

void writeFile(){
    cout<<"Writing in file"<<endl;
    string filename = "myFile.txt";
    ofstream outfile(filename, ios::app);
    cout<<"enter name of user:"<<endl;
    string name;
    cin>>name;
    outfile<<name<<endl;

    outfile.close();
}


void readFile(){
    cout<<"read data from file"<<endl;
    string filename = "myFile.txt";

    ifstream inputfile(filename);
    string line;
    if(inputfile.fail()){
        cout<<"file not found!"<<endl;
    }else{
        cout<<"read from file"<<endl;
        
        while(getline(inputfile, line)){
            cout<<line<<endl;
        }
    }

    inputfile.close();
}


void copyFile(){
    cout << "Copying data from one file into another" << endl;
    string sourceFile = "myFile.txt";
    string destFile = "copyFile.txt";

    ifstream src(sourceFile);
    ofstream dest(destFile);

    if(src && dest){
        string line;
        while(getline(src, line)){
            dest << line << "\n";
        }
        cout << "Copy Finished" << endl;
    } else {
        cout << "Error: Cannot open source or destination file!" << endl;
    }
    src.close();
    dest.close();
}


int main(){
    int ch;
    do {
        cout << "\n===== Menu =====" << endl;
        cout << "1. Write File" << endl;
        cout << "2. Read File" << endl;
        cout << "3. Copy File" << endl;
        cout << "0. Exit" << endl;
        cout << "Enter choice: ";
        cin >> ch;

        switch (ch){
            case 1: writeFile(); break;
            case 2: readFile(); break;
            case 3: copyFile(); break;
            case 0: cout << "Exiting..." << endl; break;
            default: cout << "Invalid choice!" << endl;
        }
    } while(ch != 0);

    return 0;
}




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



#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
using namespace std;

class Product {
private:
    int prdid;
    char name[30];
    int qty;
    double price;

public:
    Product() {
        prdid = 0;
        qty = 0;
        price = 0.0;
        strcpy(name, "NA");
    }

    void input() {
        cout << "Enter Product ID: ";
        cin >> prdid;
        cout << "Enter Product Name: ";
        cin >> name;
        cout << "Enter Quantity: ";
        cin >> qty;
        cout << "Enter Price: ";
        cin >> price;
    }

    void display() const {
        cout << "----------------------------------\n";
        cout << "Product ID: " << prdid << endl;
        cout << "Product Name: " << name << endl;
        cout << "Quantity: " << qty << endl;
        cout << "Price: " << price << endl;
    }

    int getId() const { return prdid; }
};

// Add Product
void addProduct() {
    Product p;
    p.input();
    ofstream fout("product.dat", ios::binary | ios::app);
    fout.write((char*)&p, sizeof(Product));
    fout.close();
    cout << "Product added successfully!\n";
}

// Display Products
void displayProducts() {
    ifstream fin("product.dat", ios::binary);
    if (!fin) {
        cout << "No records found!\n";
        return;
    }
    Product p;
    while (fin.read((char*)&p, sizeof(Product))) {
        p.display();
    }
    fin.close();
}

// Search Product by ID
void searchProduct(int id) {
    ifstream fin("product.dat", ios::binary);
    Product p;
    bool found = false;
    while (fin.read((char*)&p, sizeof(Product))) {
        if (p.getId() == id) {
            p.display();
            found = true;
            break;
        }
    }
    fin.close();
    if (!found) cout << "Product not found!\n";
}

// Update Product by ID
void updateProduct(int id) {
    fstream fio("product.dat", ios::binary | ios::in | ios::out);
    Product p;
    bool found = false;
    while (fio.read((char*)&p, sizeof(Product))) {
        if (p.getId() == id) {
            cout << "Old details:\n";
            p.display();

            cout << "\nEnter new details:\n";
            p.input();

            long pos = -1 * (long)sizeof(Product);
            fio.seekp(pos, ios::cur);
            fio.write((char*)&p, sizeof(Product));
            cout << "Product updated successfully!\n";
            found = true;
            break;
        }
    }
    fio.close();
    if (!found) cout << "Product not found!\n";
}

// Delete Product by ID
void deleteProduct(int id) {
    ifstream fin("product.dat", ios::binary);
    ofstream fout("temp.dat", ios::binary);

    Product p;
    bool found = false;
    while (fin.read((char*)&p, sizeof(Product))) {
        if (p.getId() != id) {
            fout.write((char*)&p, sizeof(Product));
        } else {
            found = true;
        }
    }
    fin.close();
    fout.close();

    remove("product.dat");
    rename("temp.dat", "product.dat");

    if (found) cout << "Product deleted successfully!\n";
    else cout << "Product not found!\n";
}

int main() {
    int choice, id;

    do {
        cout << "\n===== Shop CRUD Application =====\n";
        cout << "1. Add Product\n";
        cout << "2. Display Products\n";
        cout << "3. Search Product\n";
        cout << "4. Update Product\n";
        cout << "5. Delete Product\n";
        cout << "6. Exit\n";
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {
            case 1: addProduct(); break;
            case 2: displayProducts(); break;
            case 3:
                cout << "Enter product ID to search: ";
                cin >> id;
                searchProduct(id);
                break;
            case 4:
                cout << "Enter product ID to update: ";
                cin >> id;
                updateProduct(id);
                break;
            case 5:
                cout << "Enter product ID to delete: ";
                cin >> id;
                deleteProduct(id);
                break;
            case 6: cout << "Exiting...\n"; break;
            default: cout << "Invalid choice!\n";
        }
    } while (choice != 6);

    return 0;
}