# Vending_Machine

This project was made for Mountain State Software Solutions(MS3). This is a Maven project and accepts JSON objects from input.json file. Once run it will parse through the JSON object and create a Config class and JsonInput class. With these classes built out the project will build a table (Vending Machine) in the terminal and allow for users input to "Purchase" and item.

## Getting Started

  1. Clone the project to your machine.
  2. Open your IDE and select open project
  3. Build and Run

**Make Sure All Dependencies Are In pom.xml**

Here is a list of all the dependency's that should be in pom.xml

-**junit**

-**json-simple**

-**jackson-core**

-**jackson-databind**

-**jackson-mapper-asl**

## My Approach to the Project

Coming into this project I tried to keep in mind how the code would look while someone was reviewing it. So I tried to keep it as neat and well organized as possible.

I tried to keep each class method simple and preforming only one task. Once that task was completed the method would then call on another to continue.

I tried to implement test cases for different scenarios for the users input. However, I'm sure there are still some bugs that could be worked out. Other than that the project is functional and reacts as a vending machine would.

When running this project you will be able to insert "Money", Select which item to "Purchase" that is displayed on the table, and choose to take your "change" out of the machine or keep on buying the items until there is none left.

I was not able to finish a logging functionality in this project, which would be my next step. Making sure to log every action that is being taken in a seperate file.

## Built With

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - An IDE I was testing out
* [Jackson](https://github.com/FasterXML/jackson-core) - I used this to convert JSON objects into class instances

## Versioning
v1.0 will be this final build

***I hope you enjoy the project, let me know if you have any feedback!***
