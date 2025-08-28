#include<iostream>
using namespace std;

template <class T>
class Stack {
	private:
		int top;
		T arr[100];
	public:
		Stack() {
			top = -1;
		}
		void push(T value) {
			if (top >= 99) {
				cout << "Stack Overflow" << endl;
			} else {
				arr[++top] = value;
				cout << value << " pushed to stack" << endl;
			}
		}
		void pop() {
			if (isEmpty()) {
				cout << "Stack Underflow" << endl;
			} else {
				cout << arr[top--] << " popped from stack" << endl;
			}
		}
		T peek() {
			if (isEmpty()) {
				cout << "Stack is empty" << endl;
				return T();
			} else {
				return arr[top];
			}
		}
		bool isEmpty() {
			return top == -1;
		}
};

int main() {
	int choice;
	do {
		cout << "\nMenu:\n";
		cout << "1. Division with Exception Handling\n";
		cout << "2. Stack Operations (int)\n";
		cout << "3. Stack Operations (string)\n";
		cout << "0. Exit\n";
		cout << "Enter your choice: ";
		cin >> choice;
		switch(choice) {
			case 1: {
				int a, b, result;
				cout << "Enter a number: ";
				cin >> a;
				cout << "Enter another number: ";
				cin >> b;
				try {
					if (b == 0) {
						throw "Division by zero!";
					} else {
						result = a / b;
						cout << "Result: " << result << endl;
					}
				} catch (const char* msg) {
					cout << msg << endl;
				}
				break;
			}
			case 2: {
				Stack<int> intStack;
				int stackChoice, val;
				do {
					cout << "\nInt Stack Menu:\n";
					cout << "1. Push\n2. Pop\n3. Peek\n4. Is Empty\n0. Back\n";
					cout << "Enter your choice: ";
					cin >> stackChoice;
					switch(stackChoice) {
						case 1:
							cout << "Enter value to push: ";
							cin >> val;
							intStack.push(val);
							break;
						case 2:
							intStack.pop();
							break;
						case 3:
							cout << "Top element is: " << intStack.peek() << endl;
							break;
						case 4:
							cout << (intStack.isEmpty() ? "Stack is empty" : "Stack is not empty") << endl;
							break;
						case 0:
							break;
						default:
							cout << "Invalid choice!" << endl;
					}
				} while(stackChoice != 0);
				break;
			}
			case 3: {
				Stack<string> stringStack;
				int stackChoice;
				string sval;
				do {
					cout << "\nString Stack Menu:\n";
					cout << "1. Push\n2. Pop\n3. Peek\n4. Is Empty\n0. Back\n";
					cout << "Enter your choice: ";
					cin >> stackChoice;
					cin.ignore();
					switch(stackChoice) {
						case 1:
							cout << "Enter value to push: ";
							getline(cin, sval);
							stringStack.push(sval);
							break;
						case 2:
							stringStack.pop();
							break;
						case 3:
							cout << "Top element is: " << stringStack.peek() << endl;
							break;
						case 4:
							cout << (stringStack.isEmpty() ? "Stack is empty" : "Stack is not empty") << endl;
							break;
						case 0:
							break;
						default:
							cout << "Invalid choice!" << endl;
					}
				} while(stackChoice != 0);
				break;
			}
			case 0:
				cout << "Exiting..." << endl;
				break;
			default:
				cout << "Invalid choice!" << endl;
		}
	} while(choice != 0);
	return 0;
}
