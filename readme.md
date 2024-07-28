<a name="readme-top"></a>


<div align="center">
  <img src="doctors Icon.png" alt="logo" width="140"  height="auto" />
  <br/>

  <h3><b>Doctors Reservations Back-End</b></h3>

</div>

<!-- TABLE OF CONTENTS -->

# 📗 Table of Contents

- [📗 Table of Contents](#-table-of-contents)
- [📖 LIFE PROGNOSIS GAHINGA 6 ](#-life-prognosis-gahinga-6-)
  - [🛠 Built With ](#-built-with-)
    - [Key Features ](#key-features-)
  - [💻 Getting Started ](#-getting-started-)
    - [Prerequisites](#prerequisites)
    - [Setup](#setup)
    - [Compilation](#compilation)
    - [Usage](#usage)
    - [Run tests](#run-tests)
    - [System Design](#system-design)
  - [👥 Authors ](#-authors-)
  - [🤝 Contributing ](#-contributing-)
  - [⭐️ Show your support ](#️-show-your-support-)
  - [🙏 Acknowledgments ](#-acknowledgments-)
  - [📝 License ](#-license-)

<!-- PROJECT DESCRIPTION -->

# 📖 LIFE PROGNOSIS GAHINGA 6 <a name="about-project"></a>


**Life Prognosis** is a a simple USSD-like command-line utility, that will collect patient (HIV) information, and provide them with how long their expected lifetime is.Its is a small project for the SIP Programming BootCamp for Carnergie Mellon University Africa. 

## 🛠 Built With <a name="built-with"></a>
- Java
- Bash
- Data store, .txt file
- Draw.io, System Design

<!-- Features -->

### Key Features <a name="key-features"></a>

- **Patient: A patient is a normal user who will be providing their health data so that the application can estimate their HIV survival rate**
- **Admin: The admin user is a privileged user who can do multiple operations on data like download/export, and user management**
- **This application accepts the record of a patient and predicts how much time they have left (or remaining lifespan) with the formula (LF_C - AGE) * 90% ^ ART where LF_C is the Life Expectancy of the patient's country of origin, AGE is the patient's age and ART is the number of year before the patient start taking ART drugs**

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->

## 💻 Getting Started <a name="getting-started"></a>


To get a local copy up and running, follow these steps.

### Prerequisites

In order to run this project you need:

- Install Java
- Install maven

### Setup

Clone this repository to your desired folder:

```sh
  cd my-folder
  git clone https://github.com/JonahKayizzi/life-prognosis-gahinga6
```

### Compilation

Checkout to the main branch, compile the program and package to create a JAR file

```sh
git checkout main
mvn clean compile
mvn package
```

### Usage

Use Java to run the JAR file

```sh
 java -jar .\target\cli-1.0-SNAPSHOT.jar
```

### Run tests

To run tests, run the following command:

JUnit is to be used for running tests

```sh
  to be updated
```

### System Design

Draw.io has been integrated into this repository

To use it effeciently,
- Add the Draw.io Integration extension to your Visual Studio Code to get Graphic View of the .io file that contains the system design diagrams
- To contribute to the design, create a new branch, open the .io file and make graphical adjustments to the design diagram using the tools availed to you by Draw.io

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## 👥 Authors <a name="authors"></a>

@JonahKayizzi
@tomtom260
@emmiduh
👤 **Thomas Mesfin Seleshi**

- GitHub: [@tomtom260](https://github.com/tomtom260)

👤 **Emmanuel Iduh**

- GitHub: [@emmiduh](https://github.com/emmiduh)

👤 **Jonathan Kayizzi**

- GitHub: [@JonahKayizzi](https://github.com/JonahKayizzi)
- Twitter: [@JonahKayizzi](https://twitter.com/JonahKayizzi)
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/jonathan-kayizzi/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->

## 🤝 Contributing <a name="contributing"></a>

Contributions, issues, and feature requests are welcome!

Feel free to check the [issues page](https://github.com/JonahKayizzi/life-prognosis-gahinga6/issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- SUPPORT -->

## ⭐️ Show your support <a name="support"></a>


If you like this project you can give me a ⭐️

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGEMENTS -->

## 🙏 Acknowledgments <a name="acknowledgements"></a>


I would like to thank [Carnegie Mellon University](https://www.linkedin.com/school/carnegie-mellon-university-africa/?originalSubdomain=rw) for arranging this challenge

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- LICENSE -->

## 📝 License <a name="license"></a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>
