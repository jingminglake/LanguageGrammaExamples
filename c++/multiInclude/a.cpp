#include "a.h"
#include "b.h"
#include <iostream>
void A::f() { std::cout << b->g() << std::endl; }
void A::test() { std::cout << "1234"  << std::endl; }
