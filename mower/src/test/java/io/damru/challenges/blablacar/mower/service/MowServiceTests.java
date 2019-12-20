package io.damru.challenges.blablacar.mower.service;

public class MowServiceTests {

//    private static final String VISITED_PLANETS_DURING_THE_TRAVEL = "number of visited planets during the travel";
//    private static final String DAYS_SPENT_TRAVELING = "number of days spent traveling";
//    private static final String POSSIBLE_JOURNEYS = "number of possible journeys";
//    public static final String HUNTERS_MET = "number of hunters met on the way";
//
//    private static BlaBlaCarMowApplication.TravelService travelService;
//    private static List<Route> routes;
//
//    @BeforeAll
//    public static void init() {
//        travelService = new BlaBlaCarMowApplication.TravelService();
//        initEmpire();
//        routes = RouteFactory.initRoutes();
//    }
//
//    @Test
//    @Order(1)
//    public void should_return_all_planets_with_neighbors() {
//        // When
//        Set<Planet> planets = travelService.generatePlanets(routes);
//        Optional<Planet> tatooine = travelService.searchInSet(planets, "Tatooine");
//        Optional<Planet> bogden = travelService.searchInSet(planets, "Bogden");
//        Optional<Planet> naboo = travelService.searchInSet(planets, "Naboo");
//        Optional<Planet> jedha = travelService.searchInSet(planets, "Jedha");
//        Optional<Planet> kiros = travelService.searchInSet(planets, "Kiros");
//        Optional<Planet> endor = travelService.searchInSet(planets, "Endor");
//        Optional<Planet> telos = travelService.searchInSet(planets, "Telos");
//
//        // Then
//        assertNotNull(planets);
//        assertEquals(7, planets.size(), "total number of planets");
//        assertTrue(tatooine.isPresent());
//        assertTrue(bogden.isPresent());
//        assertTrue(naboo.isPresent());
//        assertTrue(jedha.isPresent());
//        assertTrue(kiros.isPresent());
//        assertTrue(endor.isPresent());
//        assertTrue(telos.isPresent());
//        assertEquals(3, tatooine.get().getReachablePlanets().size());
//        assertEquals(2, bogden.get().getReachablePlanets().size());
//        assertEquals(1, naboo.get().getReachablePlanets().size());
//        assertEquals(3, jedha.get().getReachablePlanets().size());
//        assertEquals(1, kiros.get().getReachablePlanets().size());
//        assertEquals(1, endor.get().getReachablePlanets().size());
//        assertEquals(1, telos.get().getReachablePlanets().size());
//    }
//
//    @Test
//    public void should_find_1_path() {
//        // Given
//        MillenniumFalcon.init(10, "Tatooine", "Kiros", routes);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(1, journeys.size(), POSSIBLE_JOURNEYS);
//        assertEquals(8, journeys.get(0).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//        assertEquals(2, journeys.get(0).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//    }
//
//    @Test
//    public void should_find_2_paths() {
//        // Given
//        MillenniumFalcon.init(6, "Tatooine", "Naboo", routes);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(2, journeys.size(), POSSIBLE_JOURNEYS);
//        assertEquals(4, journeys.get(0).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//        assertEquals(9, journeys.get(0).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//        assertEquals(3, journeys.get(1).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//        assertEquals(5, journeys.get(1).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//    }
//
//    @Test
//    public void should_find_no_path() {
//        // Given
//        MillenniumFalcon.init(10, "Tatooine", "Telos", routes);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(0, journeys.size(), POSSIBLE_JOURNEYS);
//    }
//
//    @Test
//    public void should_find_no_path_when_autonomy_fails() {
//        // Given
//        MillenniumFalcon.init(10, "Bogden", "Kiros", routes);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(2, journeys.size(), POSSIBLE_JOURNEYS);
//
//        // Given
//        MillenniumFalcon.init(5, "Bogden", "Kiros", routes);
//
//        // When
//        journeys = getJourneys();
//
//        // Then
//        assertEquals(0, journeys.size(), POSSIBLE_JOURNEYS);
//    }
//
//    @Test
//    public void should_find_1_path_with_delay_because_of_hunters() {
//        // Given
//        MillenniumFalcon.init(10, "Endor", "Telos", routes);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(1, journeys.size(), POSSIBLE_JOURNEYS);
//        assertEquals(3, journeys.get(0).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//        assertEquals(2, journeys.get(0).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//        assertEquals(0, journeys.get(0).getHuntersMet(), HUNTERS_MET);
//    }
//
//    @Test
//    public void should_find_1_path_with_1_hunter() {
//        // Given
//        MillenniumFalcon.init(10, "Endor", "Telos", routes);
//        List<BountyHunter> bountyHunters = new ArrayList<>();
//        bountyHunters.add(new BountyHunter("Telos", 2));
//        Empire.init(2, bountyHunters);
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(1, journeys.size(), POSSIBLE_JOURNEYS);
//        assertEquals(2, journeys.get(0).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//        assertEquals(2, journeys.get(0).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//        assertEquals(1, journeys.get(0).getHuntersMet(), HUNTERS_MET);
//    }
//
//    @Test
//    public void should_find_no_path_because_travel_took_too_long() {
//        // Given
//        MillenniumFalcon.init(10, "Endor", "Telos", routes);
//        Empire.init(2, new ArrayList<>());
//
//        // When
//        List<Journey> journeys = getJourneys();
//
//        // Then
//        assertEquals(1, journeys.size(), POSSIBLE_JOURNEYS);
//        assertEquals(2, journeys.get(0).getDaysToDestination(), DAYS_SPENT_TRAVELING);
//        assertEquals(2, journeys.get(0).getPlanets().size(), VISITED_PLANETS_DURING_THE_TRAVEL);
//
//        // Given
//        Empire.init(1, new ArrayList<>());
//
//        // When
//        journeys = getJourneys();
//
//        // Then
//        assertEquals(0, journeys.size(), POSSIBLE_JOURNEYS);
//    }
//
//    private List<Journey> getJourneys() {
//        Set<Planet> planets = travelService.generatePlanets(routes);
//        Optional<Planet> origin = travelService.searchInSet(planets, MillenniumFalcon.instance().getDeparture());
//        Optional<Planet> destination = travelService.searchInSet(planets, MillenniumFalcon.instance().getArrival());
//
//        // When
//        return travelService.getAllPossiblePaths();
//    }
//
//    private static void initEmpire() {
//        List<BountyHunter> bountyHunters = new ArrayList<>();
//        bountyHunters.add(new BountyHunter("Telos", 2));
//        Empire.init(100, bountyHunters);
//    }
}
