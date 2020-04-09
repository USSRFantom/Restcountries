package space.kroha.restcountries.data;

public class Country {
    String name; //Страна
    String capital; //Столица
    String region; //расположение
    int population; //численность населения
    String flag; //картинка флага

    public Country(String name, String capital, String region, int population, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.flag = flag;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
