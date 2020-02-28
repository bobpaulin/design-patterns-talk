# Scene 1

Narrator: We open at MaybeCoin the Quantum CryptoCurrency unicorn after a year in production.  We find the Greybeard and JR developer working to get the codebase ready for the anticipated 2.0 release.  Little do they know a crisis of code has been brewing and threatens to preven the 2.0 release from seeing the light of day.  Can our hero's overcome the new villian.... themselves.

JR: Beans....Beans....Beans...too many.

GB: I hope you're not talking about lunch greenhorn cause we'll have to start evacuating the office now!

JR: Mmm lunch.... I wish.  So we added Sonar to our build stack last week and it was spitting out all these reports about duplicate code in our project.  So I started tracking it all down and it's all Beans!

GB: Ah yeah we do have a lot of different types of data in our application so we created data structures to hold the different types of data.  These objects just have getters and sometimes setters if they're mutable.  But the idea is to be able to represent the data in an object oriented way.

JR: Ok I get why we have them but some of these are really big and there are just hundreds of lines of code just calling these getters and setters.  I'm worried that when we go to refactor this we're going to make mistakes.

GB: Yup your intuition is getter better!  And like many developers before you are realizing the faults of your previous work and looking for a better way. 
 
JR: Yeah maybe we can use reflection and create some kind of mapping framework and we can open source it!

GB: Well that's not really what I meant.  I'm saying that these are problems that occur frequently in fact so frequently that people started noticing patterns in the way they were solving them.  And after some time four computer scientist that people refer to as the Gang of Four wrote a book called Design Patterns to outline solutions to many of the common problems developers were having designing OO systems. 

JR: Books?  People still read those things?

GB: Ugg yes they do.  There are things that aren't on the internet....

JR: Oh here it is on wikipedia..... Design Patterns!  Oh but wow there's a bunch here.  How do I know which one to use?

GB: It comes with a little experence but each pattern has a motivation.  When you see one that sounds like it might be your problem dig in a little bit and see where it takes you.

JR: Ah here we go the Builder Pattern.  "The builder pattern is a design pattern designed to provide a flexible solution to various object creation problems in object-oriented programming. The intent of the Builder design pattern is to separate the construction of a complex object from its representation." That sounds like our problem how do I go about applying it?

GB: Ah this is one of the original patterns from the book.  Each pattern has a diagram with example implementations.

JR: UML?  Isn't that a dead language like Latin?

GB: Um no even though it's not quite as fashionable it can still be very useful in explaining systems.  So you see here there are 3 parts.  The Director which is your main program.  The Builder which is composed of both an interface and a concreate class responsible for building the complex object, and finally the Product which is the concrete of the complex object being built.



