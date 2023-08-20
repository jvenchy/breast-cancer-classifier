# Breast Cancer Classifier using k-Nearest Neighbors (kNN)
This repository contains a Java implementation of a breast cancer cell clump classification model using the k-Nearest Neighbors (kNN) algorithm. The project focuses on accurately classifying cell clumps as either malignant or benign based on their features.

# Project Overview
Objective: Develop a breast cancer classifier using the kNN algorithm.
Key Features:
Distance calculation between data points.
Nearest neighbor search to find K closest neighbors.
Classification of instances as benign (2) or malignant (4) based on neighbor labels.
Accuracy calculation and result presentation.
Visualization of distances between training data instances.

# Getting Started
Clone this repository to your local machine.

shell
Copy code
git clone https://github.com/yourusername/breast-cancer-classifier.git
Open the project in your preferred Java development environment.

Run the BreastCancerClassify class to execute the classification model and view accuracy results.

# Project Structure
BreastCancerClassify.java: Main class containing the kNN implementation and accuracy evaluation.
InputHandler.java: Handles data loading and preprocessing.
Grapher.java: Creates graphical visualization of distances between data points.
datasets/: Directory containing training and testing data files (CSV format).

# Dependencies
Java SDK
Java Swing (for GUI components and visualization)

# Usage
Ensure you have Java and a compatible development environment installed.

Compile and run the BreastCancerClassify.java class.

The program will display a graphical visualization of distance relationships and print the model's accuracy on the provided test dataset.

# Acknowledgments
This project was done in my high school Data Structures and Algorithms course. Special thanks to Mrs. Kankelborg 🫡.
