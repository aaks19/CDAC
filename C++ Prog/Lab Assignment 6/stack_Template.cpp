#include<iostream>
#include<string>
using namespace std;

template <class T>
class Stack {
    private:
        int top;
        T arr[10];
    public:
        Stack() {
            top = -1;
        }
        void push(T val) {
            if (top >= 9) {
                cout << "Stack overflow" << endl;
            } else {
                top = top + 1;
                arr[top] = val;
                cout << val << " is added in stack." << endl;
            }
        }

        void pop() {
            if (top == -1) {
                cout << "Stack underflow" << endl;
            } else {
                cout << arr[top] << " is popped out from stack" << endl;
                top--;
            }
        }

        T peek() {
            if (isEmpty()) {
                cout << "Stack is empty" << endl;
                return T();
            }
            cout << "Topmost element of stack = " << arr[top] << endl;
            return arr[top];
        }

        bool isEmpty() {
            return (top == -1);
        }

        // void display() {
        //     if (isEmpty()) {
        //         cout << "Stack is empty" << endl;
        //         return;
        //     }
        //     cout << "Stack elements:" << endl;
        //     for (int i = top; i >= 0; i--) {
        //         cout << "[" << arr[i] << "]" << endl;
        //     }
        // }
};

int main() {
    Stack<int> mystack;
    int ch;
    do {
        cout << "\n1.Push\n2.Pop\n3.Peek\n4.IsEmpty\n5.Display\n6.Exit\n";
        cout << "Enter your choice:" << endl;
        cin >> ch;
        switch (ch) {
            case 1: {
                int val;
                cout << "Enter element to push: ";
                cin >> val;
                mystack.push(val);
                break;
            }
            case 2:
                mystack.pop();
                break;
            case 3:
                mystack.peek();
                break;
            case 4:
                if (mystack.isEmpty())
                    cout << "Stack is empty" << endl;
                else
                    cout << "Stack is not empty" << endl;
                break;
            // case 5:
            //     mystack.display();
            //     break;
            case 6:
                cout << "Exit" << endl;
                break;
            default:
                cout << "Invalid choice" << endl;
        }
    } while (ch != 6);

    return 0;
}
