import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Country> countries = readCountries("Countries.csv");

        List<City> cities = readCities("Cities.csv");

        Map<String, City> mostPopulatedCityByCountry = highestCityByCountry(countries, cities);
        System.out.println("Highest City By Each Country");
        for (Map.Entry<String, City> theCities : mostPopulatedCityByCountry.entrySet()) {
            System.out.println(theCities.getKey() + ":" + theCities.getValue());
            System.out.println("---------------------");
        }

        Map<String, City> mostPopulatedCityByContinent = highestCityByContinent(countries, cities);
        System.out.println("Highest City By Each Continent");
        for (Map.Entry<String, City> theCities : mostPopulatedCityByContinent.entrySet()) {
            System.out.println(theCities.getKey() + ":" + theCities.getValue());
            System.out.println("---------------------");
        }


        Map<String, City> mostPopulatedCapitalCity = highestCapitalCity(countries, cities);
        System.out.println("Highest Capital City");
        if (mostPopulatedCapitalCity != null) {
            for (Map.Entry<String, City> theCities : mostPopulatedCapitalCity.entrySet()) {
                System.out.println(theCities.getKey() + ":" + theCities.getValue());
                System.out.println("---------------------");
            }
        }
    }


    public static List<Country> readCountries(String fileName) {

        List<Country> countries = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String Line = scanner.nextLine();
                String[] fields = Line.split(",");

                //Some Countries in the population Column has a double value not integer
                int population = (int) (Double.parseDouble(fields[4]) % 1 != 0 ?
                        Double.parseDouble(fields[4]) * 1000 : Double.parseDouble(fields[4]));

                Country country = new Country(fields[0], fields[1], fields[2], Double.parseDouble(fields[3]),
                        population, Double.parseDouble(fields[5]), Integer.parseInt(fields[6]));

                countries.add(country);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static List<City> readCities(String fileName) {
        List<City> cities = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String Line = scanner.nextLine();
                String[] fields = Line.split(",");

                City city = new City(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), fields[3]);

                cities.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public static Map<String, City> highestCityByCountry(List<Country> countries, List<City> cities) {
        Map<String, City> citiesByCountries = new HashMap<>();

        for (Country country : countries) {
            if (!citiesByCountries.containsKey(country.getName())) {
                cities.stream()
                        .distinct()
                        .filter(city -> city.getCountryCode().equals(country.getCode()))
                        .max(Comparator.comparingInt(City::getPopulation)).
                        ifPresent(mostPopulatedCity -> citiesByCountries.put(country.getName(), mostPopulatedCity));
            }
        }
        return citiesByCountries;
    }

    public static Map<String, City> highestCityByContinent(List<Country> countries, List<City> cities) {
        Map<String, City> citiesByContinent = new HashMap<>();

        for (Country country : countries) {
            if (!citiesByContinent.containsKey(country.getContinent())) {

                List<Country> countriesByContinent = new ArrayList<>();

                countriesByContinent = countries.stream()
                        .distinct()
                        .filter(country1 -> country1.getContinent().equals(country.getContinent()))
                        .collect(Collectors.toList());

                Map<String, City> citiesByCountriesByContinent = highestCityByCountry(countriesByContinent, cities);
                List<City> highestCities = new ArrayList<>(citiesByCountriesByContinent.values());

                highestCities.stream()
                        .distinct()
                        .max(Comparator.comparingInt(City::getPopulation)).
                        ifPresent(mostPopulatedCity -> citiesByContinent.put(country.getContinent(), mostPopulatedCity));
            }
        }
        return citiesByContinent;
    }
    public static Map<String, City> highestCapitalCity(List<Country> countries, List<City> cities){
        Map<String, City> capitals = new HashMap<>();
        Map<String, City> highestCapital = new HashMap<>();

        for (Country country : countries) {
            if (!capitals.containsKey(country.getName())) {
                cities.stream()
                        .distinct()
                        .filter(city -> city.getId()== country.getCapital())
                        .max(Comparator.comparingInt(City::getPopulation)).
                        ifPresent(mostPopulatedCity -> capitals.put(country.getName(), mostPopulatedCity));
            }
        }

        Optional<Map.Entry<String, City>> maxEntry=  capitals.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue(Comparator.comparingInt(City::getPopulation)));
        if (maxEntry.isPresent()){
            highestCapital.put(maxEntry.get().getKey(),maxEntry.get().getValue());
            return  highestCapital;
        }
        return null;

    }


}
