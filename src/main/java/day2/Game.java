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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", subsets=" + subsets +
                '}';
    }
}