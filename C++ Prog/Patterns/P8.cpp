/*         Pattern2

            * * * * * *
              * * * *
               * * *
                * *
                 *

*/


#include<bits/stdc++.h>
using namespace std;

void print(int n){
    for(int i=0;i<n;i++){
        //space 
        for(int j = 0; j < i; j++)
        {
            cout << " ";
        }
        //star
        for(int j = 0; j < 2*n-(2*i+1); j++)
        {
            cout << "*";
        }
        //space
        for(int j = 0; j < i; j++)
        {
            cout << " ";
        }

        cout << endl;
    }
}

int main(){
    int n;
    cout << "Enter the value of n: ";
    cin >> n;

    print(n);  //pattern 2
}