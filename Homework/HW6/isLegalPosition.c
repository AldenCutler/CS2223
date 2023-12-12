#include <stdlib.h>
#include <stdio.h>
#include "functions.h"

void printBoard(int n, int board[n][n]) {
    for (int i = 0; i < n; i++) {
        printf("|");
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 1) 
                printf(" \033[0;32m%d\033[0m |", board[i][j]);
            else
                printf(" %d |", board[i][j]);
            // printf(" %d |", board[i][j]);
        }
        printf("\n");
    }
}

int isThreatened(int n, int board[n][n], int row, int col) {
    int i, j;

    // check row
    for (i = 0; i < n; i++) {
        if (i != col && board[row][i] == 1 ) {
            return 1;
        }
    }

    // check column
    for (i = 0; i < row; i++) {
        if (board[i][col] == 1) {
            return 1;
        }
    }

    // check negative diagonal
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (i != row && board[i][j] == 1) {
            return 1;
        }
    }

    // check positive diagonal
    for (i = row, j = col; j < n && i >= 0; i--, j++) {
        if (i != row && board[i][j] == 1) {
            return 1;
        }
    }

    return 0;
}

int isLegalPosition(int n, int board[n][n]) {
    // loop over the board and see if any queens are threatened
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++){
            // if there is a queen and it is threatened, then the position is not legal
            if (board[i][j] == 1 && isThreatened(n, board, i, j)) {
                return 0;
            }
        }
    }

    // if we get here, no queens are threatened so the position is legal
    return 1;
}

void setBoard(int n, int board[n][n], int queens[n]) {
    // set the board to all 0s
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++){
            board[i][j] = 0;
        }
    }
    // set the queens on the board
    for (int i = 0; i < n; i++) {
        if (queens[i] != 0) 
            board[i][queens[i] - 1] = 1;
    }
}