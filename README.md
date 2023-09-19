# Project Title: Shopping Cart Pricing Calculator

Welcome to the Shopping Cart Pricing Calculator project. This project aims to calculate the total price of items in a shopping cart considering discounts, taxes, promotions, currency exchange rates, and inventory availability. It has been designed to test your understanding of code quality, programming standards, design principles, and language syntax in Python and Java.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Expectations](#expectations)
3. [Getting Started](#getting-started)
4. [Working on the Code](#working-on-the-code)
5. [Submission](#submission)
6. [Evaluation](#evaluation)
7. [License](#license)

## Project Structure <a name="project-structure"></a>

Here is what the current project repository contains:

1. The main script `shopping_cart.py` (for Python) and `ShopCalculator.java` (for Java), which calculates the total bill for items added to the shopping cart. 
2. These scripts read data stored in various CSV files like `discounts.csv`, `tax_rates.csv`, `promotions.csv`, `currency_rates.csv`, `inventory.csv` and `shopping_cart.csv` located in the `data` folder.

## Expectations <a name="expectations"></a>

1. **Code Quality**: Your code should be easy to read, properly indented, have informative variable names and insightful comments. Follow the [PEP8](https://www.python.org/dev/peps/pep-0008) style guide for Python and [Oracle Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-introduction.html) for Java.
2. **Code Efficiency**: The code should avoid redundancy and perform the calculations in the most efficient way possible. 
3. **Error Handling**: The program should robustly handle potential errors such as insufficient inventory.
4. **'Code Smells' Resolution**: Find and eliminate potential [code smells](https://en.wikipedia.org/wiki/Code_smell), which are symptoms in the code that could indicate a deeper problem.

## Getting Started <a name="getting-started"></a>

To run this project locally, follow the instructions below.

### Prerequisites

You must have either Python or Java JDK installed. You can download Python from [here](https://www.python.org/downloads/) and Java JDK from [here](https://www.oracle.com/java/technologies/javase-downloads.html).

### Running the program

#### For Python

```bash
python shopping_cart.py
```

#### For Java 

```bash
javac ShopCalculator.java
java ShopCalculator
```
If the inventory for any item in the shopping cart is insufficient, the program will terminate with a message indicating the same.

## Working on the Code <a name="working-on-the-code"></a>

Follow these steps to work on this project:

1. Clone the repository:
```bash
git clone <repository_url>
cd project_directory
```
2. Switch to a new branch. Replace `{student_id}` with your personal student ID. 
```bash
git checkout -b answers_{student_id}
```
3. Make your changes.
4. Stage and commit those changes:
```bash
git add .
git commit -m "commit message"
```
5. Push the changes to your branch:
```bash
git push -u origin answers_{student_id}
```
It's crucial that all changes are pushed to a branch named 'answers_{student_id}'.

## Submission <a name="submission"></a>

Once you've committed and pushed all your changes, submit your branch's URL for evaluation.

## Evaluation <a name="evaluation"></a>

Your task will be evaluated on the following parameters:

1. Quality of code.
2. Effectiveness of improvements.
3. Code standard compliance.
4. Clarity and usefulness of comments.

## License <a name="license"></a>

This project is licensed under the MIT License. Refer to `LICENSE.md` for more details.

The use of this project is purely for educational purposes.


## Disclaimer

The use of this project is for educational purposes. The developer of this project and all associated parties are not responsible for any damages or liabilities direct or indirect, that result from the use of this software. All information in the software, including all data, diagrams, outputs, and reports, are not guaranteed to be accurate, timely, or complete, and are provided "as is" without warranty of any kind, express or implied, including warranties of performance, merchantability, and fitness for a particular purpose.
