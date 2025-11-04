package com.troy.gridracers;

public class RaceCar extends Car {

    /** Constructor: creates a car with a specific ID (like '1', '2', or '3'). */
    public RaceCar(char id) {
        super(id);
    }

    /**
     * Performs one move for this RaceCar on the given racetrack.
     * The movement algorithm checks all reachable cells and moves
     * toward the lowest weight (closer to the finish).
   
     */
    @Override
    public void move(Racetrack t) {
        // Start with the car's current position as the best known location.
        int bestRow = getRow();
        int bestCol = getCol();
        int bestWeight = Integer.MAX_VALUE;

        // Define how far the car can move vertically and horizontally.
        int maxRowDist = Math.max(1, getRowVelocity());
        int maxColDist = Math.max(1, getColVelocity());

        // Search all reachable cells within the velocity range.
        for (int dr = -maxRowDist; dr <= maxRowDist; dr++) {
            for (int dc = -maxColDist; dc <= maxColDist; dc++) {
                int newRow = getRow() + dr;
                int newCol = getCol() + dc;

                // Skip any blocked or out-of-bounds cells.
                if (t.isBlocked(newRow, newCol))
                    continue;

                // Check the weight of the potential cell.
                int weight = t.getWeight(newRow, newCol);

                // Choose the lowest weight cell available.
                if (weight < bestWeight) {
                    bestWeight = weight;
                    bestRow = newRow;
                    bestCol = newCol;
                }
            }
        }

        // If the chosen cell is a finish cell, mark this car as the winner.
        if (t.isFinish(bestRow, bestCol))
            setWinner(true);

        // Update the car’s stored position and print new board state.
        updateCarInfo(t, bestRow, bestCol);
    }
}

