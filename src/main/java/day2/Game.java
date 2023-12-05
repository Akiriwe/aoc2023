package day2;

import java.util.List;

class Game {
    private int id;
    private List<Subset> subsets;

    public void setId(int id) {
        this.id = id;
    }

    public void setSubsets(List<Subset> subsets) {
        this.subsets = subsets;
    }

    public int getId() {
        return id;
    }

    public List<Subset> getSubsets() {
        return subsets;
    }

    public boolean isCompliantWithNumbers(Subset subsetToComplyWith) {
        for (Subset subset : subsets) {
            if (subsetToComplyWith.getBlue() < subset.getBlue() ||
                subsetToComplyWith.getGreen() < subset.getGreen() ||
                subsetToComplyWith.getRed() < subset.getRed()) {
                return false;
            }
        }

        return true;
    }

    public Subset getTheMinimalSubsetToRunTheGame() {
        Subset minimalSubset = new Subset(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

        subsets.forEach(subset -> {
            if (subset.getRed() > minimalSubset.getRed()) minimalSubset.setRed(subset.getRed());
            if (subset.getGreen() > minimalSubset.getGreen()) minimalSubset.setGreen(subset.getGreen());
            if (subset.getBlue() > minimalSubset.getBlue()) minimalSubset.setBlue(subset.getBlue());
        });

        return minimalSubset;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", subsets=" + subsets +
                '}';
    }
}