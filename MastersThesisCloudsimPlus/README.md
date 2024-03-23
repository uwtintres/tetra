## Thesis Project: TETRA: Time- and Energy-Aware TOPSIS-based Resource Allocation

This repository contains the Java code for my thesis project titled "TETRA: Time- and Energy-Aware TOPSIS-based Resource Allocation".

## Abstract:

<p style="text-align: justify;">
With the exponential growth of IoT devices, there has been an increasing demand for distributed 
computing paradigms such as edge computing and fog computing to address the limitations of 
cloud computing. Resource scheduling is a critical aspect across the different layers, as it ensures 
that the available resources are efficiently utilized and allocated to different tasks. Most of the 
existing resource scheduling algorithms for fog computing environments focus primarily on 
performance metrics such as makespan, resource utilization, and cost separately. However, there 
is a need for dynamic multi-objective optimization techniques that can be energy-aware while not 
compromising on makespan. In this thesis, we introduce a novel resource scheduling algorithm for 
fog computing environments that optimizes time and energy consumption, which ensures higher 
performance and lower data center costs. The algorithm considers all the available Virtual 
Machines (VM) in the fog computing environment. Then, it uses the Technique for Order of 
Preference by Similarity to Ideal Solution (TOPSIS), which is a multi-criteria decision analysis 
(MCDA) method, to identify the optimal resources. Our algorithm considers multiple 
computational parameters such as Million Instructions Per Second (MIPS), the number of 
processing cores, and thermal design power (TDP) to rank available resources. We conducted a 
series of experiments, and our algorithm achieves a multi-objective optimization for scheduling 
IoT tasks on higher-ranked resources resulting in a 7%, 19% and 25% optimization rates in
makespan over Best-Fit, Greedy and First-Fit algorithms respectively. In addition, the 
optimizations in energy consumption over the Best-Fit, Greedy and First-Fit algorithms from our 
experiments were 1%, 41% and 27%, respectively.
<p>

## Table of Contents

- [Project Requirements](#project-requirements)
- [Building the Experiment](#building-the-experiment)
- [Running the Project](#running-the-project)
- [Documentation](#documentation)
- [License](#license)

# Project Requirements

TETRA is a Java 17 project that uses maven for build and dependency management. To build and run the project, you need JDK 17+ installed.

All project dependencies are download automatically by maven (including CloudSim Plus jars).

# Building the Experiment

TETRA builds on top of the [CloudSim Plus Examples](https://github.com/cloudsimplus/cloudsimplus-examples) which is a maven project, therefore to build it, please your favorite IDE and click the build button. 

# Running the Project

The easiest way to run the examples is relying on some IDE. Below are required steps:

- Open/import the project in your IDE:
    - For NetBeans, just use the "Open project" menu and select the directory where the project was downloaded/cloned. Check a NetBeans tutorial here.
    - For Eclipse or IntelliJ IDEA, you have to import the project selecting the folder where the project was cloned. Check an Eclipse tutorial here.
- The experiment is in the org.tetra.simulation package. 
- If you want to build your own experiments, please watch the [tutorial](https://youtu.be/IUTKl0Q2Av8) video. 

# Documentation

[Thesis Defense Video](https://www.youtube.com/watch?v=ofzeWV9Jv80) - Watch the video of the thesis defense for a detailed explanation of the experiment.

# License 

This project is licensed under the GNU General Public License v3.0 license. You can find the license details in the LICENSE file.

