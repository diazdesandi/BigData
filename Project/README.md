# Introduction
Introduction
The evolution of machine learning is thanks to the development of new technologies and programming algorithms that facilitate and optimize each of the processes. These algorithms have specific or sometimes common functions, in which each algorithm has a different performance according to the way it is logically programmed. There are a lot of algorithms that in this document we will talk about and test in a theoretical and practical way in order to make a comparison according to the results obtained. We will talk and we will focus on the algorithms Decision tree, Logistic Regression, multiplayer perception,SVM. Machine Learning can be an incredibly beneficial for discovering information and predicting future trends

# Conceptual framework
## SVM
Support Vector Machines (SVM) are powerful learning tools
of machines for classification and data prediction (Vapnik, 1995). The problem of
the separation of two classes is resolved using a hyper plane that maximizes the
margin between classes. The data points in the margins are called support vectors.
The SVM algorithm seeks to find the hyper plane that creates the largest margin between the
training points of the two classes. It also penalizes the total distance of the
points that are on the wrong side of your margin whenever there is
overlap between the two types of data. This allows a number to be tolerated
limited misclassification near the margin. The other key feature in the
SVM is the use of the kernel functions and the penalty parameter for
convert the non-linear limits in the parameter space of the inputs into
linear boundaries in some transformed space of larger dimension. In the following
figure illustrates the representation of a problem of two classes in one space
two-dimensional using SVM.
Here, the demarcation of the boundaries between the red and blue classes (left panel)
shows a predominantly continuous space for the red class with blue bags
embedded.
3The embedded SVM model (right panel) also creates a diagonal pattern
dominant, although one where the blue class is continuous. The relative fraction of the
Blue space versus red space is very similar in both cases.
## Decision tree
The decision tree is a supervised machine learning technique for
induce a decision tree from the training data A tree of
decision (also called a classification tree or reduction tree) is a
predictive model that is a mapping from the observations on an element to
the conclusions on their objective value. In the structures of the trees, the leaves
represent classifications (also called labels), the nodes without leaves are
characteristics and the branches represent conjunctions of characteristics that lead
to the classifications.
Building a decision tree that is consistent with a data set
determined is easy. The challenge is to build good decision trees, which
usually means the smallest decision trees [2].
## Logistic Regression
Logistic regression analysis is used to examine the association of variables
independent with a dichotomous dependent variable. This contrasts with the
linear regression analysis in which the dependent variable is a continuous variable.
Consider an example where logistic regression could be used to examine
the screening question: "Is there a history of suicide attempts
partners with on
risk
from
a
attempt
further
(en
say,
observed
prospectively)? The logistic regression model compares the probabilities of a
prospective attempt on those with and without previous attempts. The proportion of those
probabilities is called the probability ratio. A logistic regression does not
analyzes the probabilities, but a natural logarithmic transformation of
probabilities, the logistical probabilities. Although the calculations are more
complicated when there are multiple independent variables, you can use
computer programs to perform the analyses. However, due to the
logarithmic transformation of the probability ratio, the interpretation of
results of the computer output is not necessarily simple. The
interpretation requires a transformation back to the original scale by taking
inverse of the natural logarithm of the regression coefficient, which is
exposure. The exponential regression coefficient represents the strength of the
association of the independent variable with the result. More specifically,
represents the increased (or decreased) risk of outcome associated with the
independent variable. The exponential regression coefficient represents the difference
risk of outcome (e.g., suicide attempt) for two subjects who differ
at a point in the independent variable. In this case, it is the difference between the
have and those without a history of attempts (i.e. when the history of attempts
is coded: 0 = no and 1 = yes). The logistic regression model can be extended to
include several independent variables (i.e., hypothetical risk factors) By
For example, is the history of attempts, the severity of the depression and the
risk factors for suicidal behavior, control of diagnosis, age
5and sex? Each odds ratio in such a model represents the change in
outcome risk (i.e., a suicide attempt) that is associated with the
independent, controlled by the other independent variables [3].

## Multilayer Perceptron
Multilayer perception (MLP) is a complement to the advancement neural network.
It consists of three types of layers: the input layer, the output layer, and the hidden layer.
The input layer receives the input signal to be processed. The task required,
as the prediction and classification, is performed by the output layer. An arbitrary number
of hidden layers that are placed between the input layer and the output layer are the
true computational engine of the MLP. Similar to a power supply network
forward in an MLP, data flows in the forward direction from the
input layer to output layer. Neurons in the MLP are trained with the
learning of retropropagation. [4]

# Implementation
For the development of these tests we have used the development environment of Visual
studio code as a code editor, to be able to execute each of the lines of
code in a "compiler" we have used spark-shell that allows us to execute
code so that it can even be compiled into different
computers. In order to execute all processes more efficiently
we use linux as operating system, although you can use any other
operating system, linux allows us to more efficiently run each of the
testing of the algorithms. The programming language used is Scala, which we
allows in a more efficient way internally the execution of the algorithms, since
which has a large number of libraries that allow you to run each of the
algorithms as well as being able to customize the way we are needed
compile. Although you have these libraries, it is also possible to modify them as
work and modify their compatibility and behavior according to the
problems we want to solve.

# Results
## Time
By analyzing the results of the execution times of each algorithm we can
note that the first iteration of each classifier is usually the one that takes the longest
required and that no "peaks" of time are observed in each of the following
iterations.
![](https://github.com/diazdesandi/BigData/blob/Unit-4/Project/tiempo.png)

## Accuracy
When analyzing the results of the accuracy of each algorithm we can notice that each
classifier maintains constant accuracy during each iteration, varying in
± 1%.
![](https://github.com/diazdesandi/BigData/blob/Unit-4/Project/Precision.png)
## Final results
As a final result we can observe that the decision tree is one
of the best raters as it gets 11.9 seconds and 89%.
![](https://github.com/diazdesandi/BigData/blob/Unit-4/Project/resultados.png)
# Conclusions
There are a lot of algorithms in computing, especially in the machine
learning, but the possibility of trying different algorithms allows us to realize
that are efficient all in different ways, in which as programmers
we can realize that each algorithm is as powerful as it needs to be for
solution of a problem, and therefore we must understand that they are developed to
be able to solve a large number of problems, so each is potentially
efficient according to what we want to solve.

# References
[1] ​ Support Vector Machine - an overview | ScienceDirect Topics
[2] ​ Decision Trees - an overview | ScienceDirect Topics
[3] ​ Logistic Regression Analysis - an overview | ScienceDirect Topics
[4] ​ Multilayer Perceptron - an overview
[5] ​ Decision Tree
[6] ​ ¿Cuáles son los tipos de algoritmos del machine learning?
