# Writing Awesome Java Code Workshop

Shippable status:
[![Build Status](https://api.shippable.com/projects/56597f4d1895ca4474247cd9/badge/master)](https://app.shippable.com/projects/56597f4d1895ca4474247cd9/builds/latest)

Travis status:
[![Build Status](https://travis-ci.org/stevenschwenke/WritingAwesomeJavaCodeWorkshop.svg?branch=master)](https://travis-ci.org/stevenschwenke/WritingAwesomeJavaCodeWorkshop)

## Meta: About this workshop

This workshop shows Java code that uses more advanced techniques of the language. Also, best practices are shown.
  
  Feel free to give feedback to steven@stevenschwenke.de

## How to conduct the workshop

### Preparation

Ask the participants to bring code from their projects into the workshop. This code is used in the exercises.

### Introduction

Usual introduction of participants. However, ask explicitly what "awesome Java Code" means for each participant. Maybe add topics like 

- clean code
- 4 rules of simple design

to the contents.

### Part 1: Possibilities of Java language, best practices and methods

Explain each chapter about the techniques and let the participants do the corresponding exercise. These need the code brought into the workshop by the participants. The goal here is to have time to apply the learned directly to the current project. After each exercise, a short discussion transfers the knowledge to all participants.

After those "isolated" exercises, introduce participants to the ugly trivia game and let them perform two sessions of a legacy code retreat: 45 minutes of pair programming with no specific goal. Just fiddle around with that code base. 

### Part 2: Methods for dealing with legacy code

Explain techniques listed in HowToDealWithLegacyCode.md. This is a more theoretical presentation.

After a short break, do a live coding session with the contents from Adrian Bolboaca. It's whise to print the markdown-file and let the printout guide you in your live coding.

### Part 3: Applying best practices and methods to legacy code
 
Use all the formerly explained knowledge to do a small scale legacy coderetreat. The participants have to write JUnit-tests for the code, doing pair programming. After each round of 45 minutes, a retrospective should be held. The participants explain to the group what they did and if it was successful. Delete all changes after each session and begin with new pairs. 

There are three codebases that can be used for this part:
 1. Ugly Trivia game in the legacy_ugly_trivia folder
 2. An extract from an real-world application in the legacy_real_code folder
 3. The codebase brought by the participants

Sessions could be:

1. Get to know the code: Just try to understand what's going on, what is OK and what is not. Try refactoring and writing tests.
2. After reflecting what the other groups did, try to refactor your codebase one more time, using your new insight. What would you do different now? Also possible: Use one of the other codebases this time.
3. Mikado Method. Develop a tree of refactorings and implement it using this method.
4. Golden Master. Challenges here are how to get rid of the random nature of the output and automate the creation and comparison of the output.
5. TDD. Often, developers don't know how to write tests. Try writing TDD as if you mean it!
  
## Meta: Copyright

All files in this repository are under Creative Commons 4.0 (see http://creativecommons.org/licenses/by/4.0/). 
  
You are free to:
  
- Share — copy and redistribute the material in any medium or format
- Adapt — remix, transform, and build upon the material for any purpose, even commercially.
  
The licensor cannot revoke these freedoms as long as you follow the license terms.
  
Under the following terms:
  
- Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
- No additional restrictions — You may not apply legal terms or technological measures that legally restrict others from doing anything the license permits.
