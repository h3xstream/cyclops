# cyclops-functions

[USER GUIDE](http://gist.asciidoctor.org/?github-aol/cyclops//user-guide/lambdas.adoc)

## Getting cyclops-functions

* [![Maven Central : cyclops-functions](https://maven-badges.herokuapp.com/maven-central/com.aol.cyclops/cyclops-functions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.aol.cyclops/cyclops-functions)


## Gradle

where x.y.z represents the latest version

compile 'com.aol.cyclops:cyclops-functions:x.y.z'

## Maven

```xml
<dependency>
    <groupId>com.aol.cyclops</groupId>
    <artifactId>cyclops-functions</artifactId>
    <version>x.y.z</version>
</dependency>
```

# Features

Light weight module (no dependencies) which adds the following features for Java 8 Functions, Consumers and Suppliers 

* Caching / Memoization
* Currying
* Partial Application
* Type inference

## Caching / Memiozation

Use static Memoize methods on Memoize utility class to create a cachable reference to a function, suppler or method. Provide custom cache interfaces via the Cachable functional interface

## Currying

Cyclops can convert any function (with up to 8 inputs) or method reference into a chain of one method functions (Currying). This technique is a useful (and more safe) alternative to Closures. The Curried function can be created and values explicitly passed in rather than captured by the compiler (where-upon they may change).

#### Currying method references 
```java
	  import static com.aol.cyclops.functions.Curry.*;
	  
	  
      assertThat(curry2(this::mult).apply(3).apply(2),equalTo(6));
      
      public Integer mult(Integer a,Integer b){
		return a*b;
	 }
```	 

#### Currying in place

```java
      		assertThat(Curry.curry2((Integer i, Integer j) -> "" + (i+j) +   "hello").apply(1).apply(2),equalTo("3hello"));
 ```    		

#### Uncurry

```java
      assertThat(Uncurry.uncurry3((Integer a)->(Integer b)->(Integer c)->a+b+c)
								.apply(1,2,3),equalTo(6));
```								

#### Type inferencing help

```java
      import static com.aol.cyclops.functions.Lambda.*;
	 
	 
	   Mutable<Integer> myInt = Mutable.of(0);
		
		Lambda.l2((Integer i)-> (Integer j)-> myInt.set(i*j)).apply(10).apply(20);
		
		assertThat(myInt.get(),
				is(200));
```
#### Curry Consumer

```java
     		CurryConsumer.curry4( (Integer a, Integer b, Integer c,Integer d) -> value = a+b+c+d).apply(2).apply(1).apply(2).accept(3);
     		
		assertThat(value,equalTo(8));
```	
#### Uncurry Consumer 

```java
     UncurryConsumer.uncurry2((Integer a)->(Integer b) -> value = a+b ).accept(2,3);
	 assertThat(value,equalTo(5));
```
	 
# Dependencies

none


