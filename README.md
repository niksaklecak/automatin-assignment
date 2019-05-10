# Project Title

Automation assignment for ui and api and performance testing 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes. 


### Installing

Install latest jdk and jre 
Download eclipse 
In order to run tests locally on eclipse, run as testng test, you will need to install testng for eclipse. 
A good installation guide can be found on this link:

```
https://www.ecanarys.com/Blogs/ArticleID/169/How-to-Install-TestNG-framework-Step-by-Step-installation-process

```

Clone project 

```
git clone https://github.com/niksaklecak/automatin-assignment.git
```

Import frameworks into eclipse as Maven projects
Wait for projects to update and you are good to go.  
 

## Running the tests


For tests execution on jenkins maven-surefire-plugin is used. 


Parameters for running a test:
- env - environment on witch you are running a tests
- platform - ip of a local machine 
- browserName - name of a browser

Currently Parameters are used only on Class level. And they are passed as a variable from jenkins Job


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Niksa Klecak** 


