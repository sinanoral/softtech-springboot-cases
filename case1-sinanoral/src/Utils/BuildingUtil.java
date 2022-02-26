package Utils;

import Buildings.Building;
import Buildings.Home;
import Buildings.SummerHouse;
import Buildings.Villa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BuildingUtil {
    public List<Home> getHomeList() {
        return new ArrayList<>(List.of(
                new Home(BigDecimal.valueOf(100_000), BigDecimal.valueOf(50), 1, 1),
                new Home(BigDecimal.valueOf(200_000), BigDecimal.valueOf(70), 2, 1),
                new Home(BigDecimal.valueOf(200_000), BigDecimal.valueOf(90), 2, 1),
                new Home(BigDecimal.valueOf(300_000), BigDecimal.valueOf(120), 3, 1)));
    }

    public List<SummerHouse> getSummerHouseList() {
        return new ArrayList<>(List.of(
                new SummerHouse(BigDecimal.valueOf(400_000), BigDecimal.valueOf(100), 2, 1),
                new SummerHouse(BigDecimal.valueOf(500_000), BigDecimal.valueOf(150), 3, 1),
                new SummerHouse(BigDecimal.valueOf(600_000), BigDecimal.valueOf(200), 4, 1)));
    }

    public List<Villa> getVillaList() {
        return new ArrayList<>(List.of(
                new Villa(BigDecimal.valueOf(700_000), BigDecimal.valueOf(300), 5, 2),
                new Villa(BigDecimal.valueOf(800_000), BigDecimal.valueOf(400), 6, 2),
                new Villa(BigDecimal.valueOf(900_000), BigDecimal.valueOf(500), 9, 3)));
    }

    public List<Building> getMixedBuildings() {
        return new ArrayList<>(List.of(
                new Home(BigDecimal.valueOf(200_000), BigDecimal.valueOf(90), 2, 1),
                new SummerHouse(BigDecimal.valueOf(500_000), BigDecimal.valueOf(150), 3, 1),
                new Villa(BigDecimal.valueOf(900_000), BigDecimal.valueOf(500), 9, 3)));
    }
}
