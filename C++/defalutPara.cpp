#include <iostream>
using namespace std;

void foo(int x = 1, int y = 2) {
    cout << x << "---" << y << endl;
}

int main() {
    foo (3);
    return 0;
}
