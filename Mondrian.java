// Ava Nunes
// 02/08/24
// CSE 123
// TA: Ben Wang
// this class represents a mondrian art generator based on the inputted canvas proportions from the client

import java.util.*;
import java.awt.*;

public class Mondrian {
    private Random random;

    // behavior: randomizes mondrian art based on the canvas proportions inputted by the user,
    //           only uses four colors: red, yellow, cyan, and white, blocks are separated by 
    //           black lines
    // parameters: pixels - 2d array denoting canvas size
    // returns: n/a, void method
    public void paintBasicMondrian(Color[][] pixels) {
        random = new Random();
        paintBasicMondrian(pixels, 0, pixels.length, 0, pixels[0].length);
    }

    // behavior: randomizes mondrian art based on the canvas proportions inputted by the user,
    //           only uses four colors: red, yellow, cyan, and white, blocks are separated by 
    //           black lines
    // parameters: pixels - 2d array denoting canvas size
    //             rowStart - int denoting the start of a horizontal section
    //             endRow - int denoting the end of a horizontal section
    //             colStart - int denoting the start of a vertical section
    //             endCol - int denoting the end of a vertical section
    // returns: n/a, void method
    private void paintBasicMondrian(Color[][] pixels, int rowStart, int endRow, int colStart, int endCol) {
        // area
        if (endRow - rowStart >= (pixels.length / 4) && endCol - colStart >= (pixels[0].length / 4)) {
            int newRow = random.nextInt(rowStart, endRow);
            int newCol = random.nextInt(colStart, endCol);
            paintBasicMondrian(pixels, rowStart, newRow, colStart, newCol);
            paintBasicMondrian(pixels, newRow, endRow, colStart, newCol);
            paintBasicMondrian(pixels, rowStart, newRow, newCol, endCol);
            paintBasicMondrian(pixels, newRow, endRow, newCol, endCol);
        }

        // height
        else if (endRow - rowStart >= (pixels.length / 4)) {
            int newRow = random.nextInt(rowStart, endRow);
            paintBasicMondrian(pixels, rowStart, newRow, colStart, endCol);
            paintBasicMondrian(pixels, newRow, endRow, colStart, endCol);
        }

        // width
        else if (endCol - colStart >= (pixels[0].length / 4)) {
            int newCol = random.nextInt(colStart, endCol);
            paintBasicMondrian(pixels, rowStart, endRow, colStart, newCol);
            paintBasicMondrian(pixels, rowStart, endRow, newCol, endCol);
        }

        else {
            Color color = randColor();
            for (int i = rowStart + 1; i < endRow; i++) {
                for (int j = colStart + 1; j < endCol; j++) {
                    pixels[i][j] = color;
                }
            }
        }
    }

    // behavior: randomizes mondrian art based on the canvas proportions inputted by the user,
    //           only uses four colors: red, yellow, cyan, and white, blocks are separated by 
    //           black lines, randomness of certain colors is determined on the region's position on the
    //           canvas
    // parameters: pixels - 2d array denoting canvas size
    // returns: n/a, void method
    public void paintComplexMondrian(Color[][] pixels) {
        random = new Random();
        paintComplexMondrian(pixels, 0, pixels.length, 0, pixels[0].length);
    }

    // behavior: randomizes mondrian art based on the canvas proportions inputted by the user,
    //           only uses four colors: red, yellow, cyan, and white, blocks are separated by 
    //           black lines, randomness of certain colors is determined on the region's position on the
    //           canvas
    // parameters: pixels - 2d array denoting canvas size
    //             rowStart - int denoting the start of a horizontal section
    //             endRow - int denoting the end of a horizontal section
    //             colStart - int denoting the start of a vertical section
    //             endCol - int denoting the end of a vertical section
    // returns: n/a, void method
    private void paintComplexMondrian(Color[][] pixels, int rowStart, int endRow, int colStart, int endCol) {
        // area
        if (endRow - rowStart >= (pixels.length / 4) && endCol - colStart >= (pixels[0].length / 4)) {
            int newRow = random.nextInt(rowStart, endRow);
            int newCol = random.nextInt(colStart, endCol);
            paintComplexMondrian(pixels, rowStart, newRow, colStart, newCol);
            paintComplexMondrian(pixels, newRow, endRow, colStart, newCol);
            paintComplexMondrian(pixels, rowStart, newRow, newCol, endCol);
            paintComplexMondrian(pixels, newRow, endRow, newCol, endCol);
        }

        // height
        else if (endRow - rowStart >= (pixels.length / 4)) {
            int newRow = random.nextInt(rowStart, endRow);
            paintComplexMondrian(pixels, rowStart, newRow, colStart, endCol);
            paintComplexMondrian(pixels, newRow, endRow, colStart, endCol);
        }

        // width
        else if (endCol - colStart >= (pixels[0].length / 4)) {
            int newCol = random.nextInt(colStart, endCol);
            paintComplexMondrian(pixels, rowStart, endRow, colStart, newCol);
            paintComplexMondrian(pixels, rowStart, endRow, newCol, endCol);
        }

        else {
            Color color = complexRandColor(pixels, rowStart, colStart);
            for (int i = rowStart + 1; i < endRow; i++) {
                for (int j = colStart + 1; j < endCol; j++) {
                    pixels[i][j] = color;
                }
            }
        }
    }

    // behavior: generates a random color, either red, white, yellow, or blue, to paint a region
    // parameters: n/a
    // returns: color - random color to paint the mondrian section with
    private Color randColor() {
        int colorNum = random.nextInt(4);
        if(colorNum == 1) {
            return Color.RED;
        }

        else if (colorNum == 2) {
            return Color.CYAN;
        }

        else if (colorNum == 3) {
            return Color.YELLOW;
        }

        return Color.WHITE;
    }

    // behavior: generates a random color, either red, white, yellow, or blue, to paint a region
    //           depending on where the region is located on the canvas
    // parameters: pixels - 2d array denoting canvas size
    //             rowStart - row denoting a portion of the region's location
    //             colStart - column denoting a portion of the region's location
    // returns: color - random color to paint the mondrian section with
    private Color complexRandColor(Color[][] pixels, int rowStart, int colStart) {
        int colorNum = random.nextInt(5);
        if(rowStart <= (pixels.length / 2) && colStart <= (pixels[0].length / 2)) {
            if (colorNum % 2 == 0) {
                return Color.RED;
            }

            return Color.CYAN;
        }

        else if (rowStart <= (pixels.length / 2) && colStart >= (pixels[0].length / 2)) {
            if (colorNum % 2 == 0) {
                return Color.WHITE;
            }

            return Color.YELLOW;
        }

        else if (rowStart >= (pixels.length / 2) && colStart <= (pixels[0].length / 2)) {
            if (colorNum % 2 == 0) {
                return Color.YELLOW;
            }
            
            return Color.WHITE;
        }

        else {
            if (colorNum % 2 == 0) {
                return Color.CYAN;
            }

            return Color.RED;
        }
    }
}

