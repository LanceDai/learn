package cn.lancedai.learn.typelearn

class Grandparent

class Parent extends Grandparent

class Child extends Parent

class InvariantClass[A]

class CovariantClass[+A]

class ContravariantClass[-A]

class VarianceExamples {
  def invarMethod(x: InvariantClass[Parent]) {}

  def covarMethod(x: CovariantClass[Parent]) {}

  def contraMethod(x: ContravariantClass[Parent]) {}


//  invarMethod(new InvariantClass[Child])
  invarMethod(new InvariantClass[Parent])
//  invarMethod(new InvariantClass[Grandparent])

  covarMethod(new CovariantClass[Child])
  covarMethod(new CovariantClass[Parent])
//  covarMethod(new CovariantClass[Grandparent])

//  contraMethod(new ContravariantClass[Child])
  contraMethod(new ContravariantClass[Parent])
  contraMethod(new ContravariantClass[Grandparent])
}
