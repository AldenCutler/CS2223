#include "functions.h"
#include <stdio.h>


int main() {

    printf("\n\nN-Queens Solver\n-------------------------------------------\n");
    printf("Make sure to run the program in a terminal with ANSI color support (as far as I know the only one that doesn't work is Windows Powershell), otherwise things might not look quite right.\n");
    printf("-------------------------------------------\n");
    printf("For example,\033[0;32m this text\033[0m should be green.\n\n");
    printf("Which function would you like to test?\n");
    printf("1. isLegalPosition\n");
    printf("2. nextLegalPosition\n");
    printf("3. Get first solution for n = 1 to 100\n");
    printf("4. Get number of solutions for n = 1 to 20\n");
    printf("5. All of the above (default if input is not valid)\n");
    printf("Enter your choice: ");

    int choice;
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            isLegalPositionTests();
            break;
        case 2:
            nextLegalPositionTests();
            break;
        case 3:
            findFirstSolution();
            break;
        case 4:
            findAllSolutions();
            break;
        default:
            isLegalPositionTests();
            nextLegalPositionTests();
            findFirstSolution();
            findAllSolutions();
            break;
    }

    return 0;    
}