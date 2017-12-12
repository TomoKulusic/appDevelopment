# Milestone 5
## Name: Nick Fealey, Greg Ghiroli, Tomislav Kulusic, Jeff Schnurr, Jesus Christ 
        
1. During this refactoring stage we added a try/catch block as a safety measure in the event of the wrong file is read or if file read error occurs. We also choose to add Try and catches blocks to prevent errors from breaking the program due to bad output directories. This was an improvement because instead of causing a system.exit the user does not have to reopen the program.
We are going to add a System.exit for the wrong file directory or if a proper one doesn't exist. We decided to add this safety measure we noticed many errors during our testing and many errors from our users experiences as well. 

2. We also had the major goal of  refactoring the gui. We felt that this was necessary because the gui class is currently nearly 1,200 lines of code and is very difficult to read. We planned on separating this class into separate Listeners. We planned on separating the createDDLButtonListener, edgeMenuListener, edgeWindowListener, and the edgeRadioButtonListener from the GUI. However after we began the refactoring of the gui we further evaluated the gui and decided that it is not feasible to refactor the gui completely in the time we have. In order to properly separate out the methods, we would need to disentangle every loose variable in the edgeConvertGui.java file. This would require most likely packaging these variables into usable objects, and then swapping out calls to these variables with the corresponding object values whenever they are needed in various methods or separate classes.  Therefore the EdgeWindowListener is only viable to be refactored in a separate class. A refactor of this scope and scale would be better served as a full rewrite of the application in question. Also  However, we do not have the java expertise skills required to complete a full rewrite in the time allotted. We persisted in refactoring other areas of the application that do not require a rewrite.
In this stage we also fixed about fixed 20 bugs that were caused by Nullpoint errors due to the inheritance primarily in the gui components. These caused multiple issues in running the program properly, and had to be fixed to get a working program.


3. We also added the logic to allow for more robust edge files to sql in the createDDL class. This will enable our program to have more versatility, in sql creation . We refactored the logic inside the createDDL.java file and added this functionality that were not enabled for the user before.. This functionality covered editing variables, adding extra fields, primary keys, foreign keys, and default values. Previously the function did not allow us to edit SQL files using the Edit button provided in the GUI, and with this logic change we have enabled modification of the SQL file. This was core functionality of the GUI, and so we have fixed a main function inside our code. 


4. Some other small things we did during this milestone: 
Removed some unused imports, Removed some unused variables 










