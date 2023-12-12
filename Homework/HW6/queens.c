#include "functions.h"
#include <stdio.h>

int main() {

    printf("\n\n\t\tN-Queens Solver\n");
    printf("-------------------------------------------\n");
    printf("  Make sure to run the program in a terminal with ANSI color support, otherwise things might not look quite right. (The program will still work)\n");
    printf("  Windows CMD and PowerShell both have issues when running them as admin, but running them normally should work.\n");
    printf("-------------------------------------------\n");
    printf("  For example,\033[0;32m this text\033[0m should be green.\n\n");
    printf("  Which function would you like to test?\n");
    printf("\t1. isLegalPosition\n");
    printf("\t2. nextLegalPosition\n");
    printf("\t3. Get first solution for n = 1 to 100\n");
    printf("\t4. Get number of solutions for n = 1 to 20\n");
    printf("\t5. All of the above (default)\n");
    printf("  Enter your choice: ");

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