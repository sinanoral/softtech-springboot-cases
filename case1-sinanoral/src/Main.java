import Utils.BuildingUtil;
import Buildings.Building;
import Services.BuildingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BuildingService buildingService = new BuildingService();
        BuildingUtil buildingUtil = new BuildingUtil();

        System.out.println("Total price of houses: " + buildingService.totalPriceOfHomes(buildingUtil.getHomeList()));
        System.out.println("Total price of summer houses: " + buildingService.totalPriceOfSummerHouses(buildingUtil.getSummerHouseList()));
        System.out.println("Total price of villas: " + buildingService.totalPriceOfVillas(buildingUtil.getVillaList()));

        System.out.println("Average square meters of houses: " + buildingService.AvgSquareMetersOfHomes(buildingUtil.getHomeList()));
        System.out.println("Average square meters of summer houses: " + buildingService.AvgSquareMetersOfSummerHouses(buildingUtil.getSummerHouseList()));
        System.out.println("Average square meters of villas: " + buildingService.AvgSquareMetersOfVillas(buildingUtil.getVillaList()));

        System.out.println("Test total price of buildings: " + buildingService.totalPriceOfBuildings(buildingUtil.getMixedBuildings()));
        System.out.println("Test average square meters of buildings: " + buildingService.AvgSquareMetersOfBuildings(buildingUtil.getMixedBuildings()));

        List<Building> filteredBuildings = buildingService.filterByRoomsAndLounges(buildingUtil.getHomeList(), 2, 1);
        System.out.println("Test filter method: " + filteredBuildings.size());
    }
}
