#include <stdio.h>
#include <stdlib.h>
#define AC_BLACK "\x1b[30m"
#define AC_RED "\x1b[31m"
#define AC_GREEN "\x1b[32m"
#define AC_YELLOW "\x1b[33m"
#define AC_BLUE "\x1b[34m"
#define AC_MAGENTA "\x1b[35m"
#define AC_CYAN "\x1b[36m"
#define AC_WHITE "\x1b[37m"
#define AC_NORMAL "\x1b[m"

int heaps[] = {3, 5, 7};

int playerMove() {

    int heap, num;
    printf("Heaps contain %d, %d, %d\n", heaps[0], heaps[1], heaps[2]);
    printf("Which heap would you like to take from? (1, 2, 3)\n");
    scanf("%d", &heap);
    
    if (heap < 1 || heap > 3) {
        printf("Invalid heap\n");
        playerMove();
    }
    heap -= 1;  // convert to 0-indexed

    printf("How many would you like to take?\n");
    scanf("%d", &num);

    if (num < 1 || num > heaps[heap]) {
        printf("Invalid number\n");
        playerMove();
    }

    heaps[heap] -= num;
    printf("You took %d from heap %d\n", num, heap + 1);
    printf("Nimsum is now %d\n", heaps[0] ^ heaps[1] ^ heaps[2]);
    printf("Heaps contain now %d, %d, %d\n\n", heaps[0], heaps[1], heaps[2]);
    printf("------------------------------------------------------------\n\n");
    return 0;
}

void computerMove() {
    int nimSum = heaps[0] ^ heaps[1] ^ heaps[2];
    // printf("Heaps contain %d, %d, %d\n", heaps[0], heaps[1], heaps[2]);
    // printf("Current nim sum is %d\n", nimSum);

    if (nimSum == 0) { 
        // computer loses if human plays perfectly, so choose random move
        int heap = rand() % 3;
        int num = rand() % heaps[heap] + 1;
        heaps[heap] -= num;
        printf("Computer took %d from heap %d\n", num, heap + 1);
        printf("Heaps contain now %d, %d, %d\n\n", heaps[0], heaps[1], heaps[2]);        
        printf("------------------------------------------------------------\n\n");
        return;
    } else {
        // nim sum is not 0, so computer will win if it plays perfectly
        // winning strategy: make nim sum 0 at each move --> check every possible move, if nim sum is 0, take that move
        // this algorithm could be optimized by only checking moves that reduce nim sum, but this is easier to implement
        int heap, num;
        for (int i = 0; i < 3; i++) {
            for (int j = heaps[i]; j > 0; j--) {
                heaps[i] -= j;  // make test move
                if ((heaps[0] ^ heaps[1] ^ heaps[2]) == 0) {  // found a winning move
                    heap = i;
                    num = j;
                    printf("Computer took %d from heap %d\n", num, heap + 1);
                    // printf("Nimsum is now %d\n", heaps[0] ^ heaps[1] ^ heaps[2]);
                    printf("Heaps contain now %d, %d, %d\n\n", heaps[0], heaps[1], heaps[2]);
                    printf("------------------------------------------------------------\n\n");
                    return;
                } else {    // not a winning move, so undo it
                    heaps[i] += j;
                }
            }
        }
    }
    return;
}

void resetColors() {
    printf("%s", AC_NORMAL);
}

int main() {

    // goal is to simulate a game of nim
    // 3 heaps of 3, 5, and 7
    char playerTurn;

    printf("%s\n\n\n\n\n**********    Welcome to Nim!    **********\n\n\n", AC_GREEN);
    printf("%sHeaps contain %d, %d, %d\n", AC_NORMAL, heaps[0], heaps[1], heaps[2]);
    printf("%sWould you like to go first? ", AC_NORMAL);
    printf("%s(y/n)\n\n", AC_RED);
    resetColors();
    scanf("%c", &playerTurn);
    printf("------------------------------------------------------------\n\n");

    while (heaps[0] + heaps[1] + heaps[2] > 0) {
        if (playerTurn == 'y') {
            playerMove();
            playerTurn = 'n';
        } else {
            computerMove();
            playerTurn = 'y';
        }
    }

    if (playerTurn == 'y') {
        printf("%sYou lose!\n\n", AC_RED);
    } else {
        printf("%sYou win!\n\n", AC_GREEN);
    }

    resetColors();
    return 0;
}