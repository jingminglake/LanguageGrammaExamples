#ifndef A__H
#define A__H

#include "b.h"
class B;

class A {
public:
  void f();
  void test();
private:
  B* b;
};
#endif
