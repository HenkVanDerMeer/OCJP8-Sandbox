package A.FunctionalInterfaceExample;

public class Animal {
    private String species;
    private boolean canHop;
    private boolean canSwim;
    public Animal(String speciesName, boolean hopper, boolean swimmer) {
        this.species = speciesName;
        this.canHop = hopper;
        this.canSwim = swimmer;
    }
    public boolean canHop() {
        return canHop;
    }
    public boolean canSwim() {
        return canSwim;
    }
    @Override
    public String toString() {
        return this.species;
    }
}
