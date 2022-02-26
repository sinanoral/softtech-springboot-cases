package Services;

import Buildings.Building;
import Buildings.Home;
import Buildings.SummerHouse;
import Buildings.Villa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingService {

    public BigDecimal totalPriceOfBuildings(List<? extends Building> buildings) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Building value : buildings)
            sum = sum.add(value.getPrice());

        return sum;
    }

    public BigDecimal totalPriceOfHomes(List<Home> homes) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Home value : homes)
            sum = sum.add(value.getPrice());

        return sum;
    }

    public BigDecimal totalPriceOfSummerHouses(List<SummerHouse> summerHouses) {
        BigDecimal sum = BigDecimal.ZERO;
        for (SummerHouse value : summerHouses)
            sum = sum.add(value.getPrice());

        return sum;
    }

    public BigDecimal totalPriceOfVillas(List<Villa> villas) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Villa value : villas)
            sum = sum.add(value.getPrice());

        return sum;
    }

    public BigDecimal AvgSquareMetersOfBuildings(List<? extends Building> buildings) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Building value : buildings)
            sum = sum.add(value.getSquareMeters());

        return sum.divide(BigDecimal.valueOf(buildings.size()), RoundingMode.DOWN);
    }

    public BigDecimal AvgSquareMetersOfHomes(List<Home> homes) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Home value : homes)
            sum = sum.add(value.getSquareMeters());

        return sum.divide(BigDecimal.valueOf(homes.size()), RoundingMode.DOWN);
    }

    public BigDecimal AvgSquareMetersOfSummerHouses(List<SummerHouse> summerHouses) {
        BigDecimal sum = BigDecimal.ZERO;
        for (SummerHouse value : summerHouses)
            sum = sum.add(value.getSquareMeters());

        return sum.divide(BigDecimal.valueOf(summerHouses.size()), RoundingMode.DOWN);
    }

    public BigDecimal AvgSquareMetersOfVillas(List<Villa> villas) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Villa value : villas)
            sum = sum.add(value.getSquareMeters());

        return sum.divide(BigDecimal.valueOf(villas.size()), RoundingMode.DOWN);
    }

    public List<Building> filterByRoomsAndLounges(List<? extends Building> buildings, int room, int lounge) {
        return buildings.stream().filter(b -> b.getRooms() == room && b.getLounges() == lounge).collect(Collectors.toList());
    }
}
