package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.mock.*;
import main.service.FlightSearchService;
import main.service.HotelFinderService;
import main.service.TourSearchService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    FlightSearchService flightSearch;
    HotelFinderService hotelSearch;
    TourSearchService tourSearch;
    TripPlanner tripPlanner;

    List<Flight> flightResults;
    List<HotelWrapper> hotelResults;
    List<Tour> tourResults;

    String departureDate;
    String returnDate;
    String departLocation;
    String destLocation;
    int numPeople;



    // TABLE MOCK OBJECTS
    public ObservableList<Flight> getFlights(){
        flightSearch = new FlightSearchMock();
        flightResults = flightSearch.searchFlightByCriteria(departureDate, departLocation, destLocation, numPeople);
        // TODO: filter
        return FXCollections.observableArrayList(flightResults);
    }

    public ObservableList<HotelWrapper> getHotels(){
        hotelSearch = new HotelFinderMock(departureDate, returnDate);
        ArrayList<Hotel> hotels = hotelSearch.getFreeRoomsFromAnyHotel();
        hotelResults = HotelWrapper.wrapList(hotels);
        // TODO: filter
        return FXCollections.observableArrayList(hotelResults);
    }

    public ObservableList<Tour> getTours(){
        tourSearch = new TourSearchMock();
        tourResults = tourSearch.createList();
        // TODO: filter
        return FXCollections.observableArrayList(tourResults);
    }

    // Create tables
    TableView<Flight> tableViewFlights;
    TableView<HotelWrapper> tableViewHotels;
    TableView<Tour> tableViewTours;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Top menu
        HBox topMenu = new HBox();
        Button buttonFlights = new Button("Flight search");
        Button buttonHotels = new Button("Hotel search");
        Button buttonTours = new Button("Tour search");
        Button buttonCart = new Button("View cart");
        topMenu.getChildren().addAll(buttonFlights,buttonHotels,buttonTours,buttonCart);

        /*
                                                #
                                                #
                         Flights                #
                                                #
                                                #
        */

        // flights left search menu
        VBox menuFlights = new VBox();

        Label flightsLocationFromLabel = new Label("Flying from:");
        Label flightsLocationToLabel = new Label("Flying to:");
        Label flightsDateFromLabel = new Label("From:");
        Label flightsDateToLabel = new Label("To:");
        Label flightsPassengersLabel = new Label("Passengers:");

        // # date pickers
        DatePicker flightsDateFrom = new DatePicker();
        DatePicker flightsDateTo = new DatePicker();

        // # date picker - events
        flightsDateFrom.setOnAction(event -> {
            LocalDate date = flightsDateFrom.getValue();
            System.out.println("Flight departure date: " + date);
        });
        flightsDateTo.setOnAction(event -> {
            LocalDate date = flightsDateTo.getValue();
            System.out.println("Flight arrival date: " + date);
        });

        // # combo boxes

        // # combo locations from
        ComboBox<String> flightsLocationFrom = new ComboBox<String>();
        flightsLocationFrom.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur","Casablanca");
        flightsLocationFrom.setValue("Reykjavik");
        flightsLocationFrom.setOnAction(event -> {
            String locationFrom = flightsLocationFrom.getValue();
            System.out.println("Flying from: " + locationFrom);
        });
        // # combo locations to
        ComboBox<String> flightsLocationTo = new ComboBox<String>();
        flightsLocationTo.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur","Casablanca");
        flightsLocationTo.setValue("Reykjavik");
        flightsLocationTo.setOnAction(event -> {
            String locationTo = flightsLocationFrom.getValue();
            System.out.println("Flying to: " + locationTo);
        });
        // # combo passengers
        ComboBox<String> flightsPassengersNumber = new ComboBox<String>();
        flightsPassengersNumber.getItems().addAll("1","2","3","4","5");
        flightsPassengersNumber.setValue("1");
        flightsPassengersNumber.setOnAction(event -> {
            String passengers = flightsPassengersNumber.getValue();
            System.out.println("Number of passengers: " + passengers);
        });

        Button searchButton = new Button("Search");

        // flights menu setup
        menuFlights.getChildren().addAll(
                flightsLocationFromLabel,
                flightsLocationFrom,
                flightsLocationToLabel,
                flightsLocationTo,
                flightsDateFromLabel,
                flightsDateFrom,
                flightsDateToLabel,
                flightsDateTo,
                flightsPassengersLabel,
                flightsPassengersNumber,
                searchButton);

        // FLIGHTS COLUMN SETUP

        // flights from column setup
        TableColumn<Flight, String> columnFlightDepartureTime = new TableColumn<>("Departure");
        columnFlightDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightArrivalTime = new TableColumn<>("Arrival");
        columnFlightArrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightAirportFrom = new TableColumn<>("Airport dep.");
        columnFlightAirportFrom.setCellValueFactory(new PropertyValueFactory<>("departureLoc"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightAirportTo = new TableColumn<>("Airport arr.");
        columnFlightAirportTo.setCellValueFactory(new PropertyValueFactory<>("arrivalLoc"));
        // flights from column setup
        TableColumn<Flight, Integer> columnFlightPrice = new TableColumn<>("Price");
        columnFlightPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewFlights = new TableView<>();
        tableViewFlights.setItems(getFlights());
        tableViewFlights.getColumns().addAll(
                columnFlightDepartureTime,
                columnFlightArrivalTime,
                columnFlightAirportFrom,
                columnFlightAirportTo,
                columnFlightPrice);

        // flights VIEW

        HBox mainFlights = new HBox();
        mainFlights.getChildren().addAll(tableViewFlights);

        /*
                                                #
                                                #
                         Hotels                 #
                                                #
                                                #
        */
        VBox menuHotels = new VBox();
        Label label2 = new Label("HOTELS");
        menuHotels.getChildren().addAll(label2);


        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelName = new TableColumn<>("Hotel Name");
        columnHotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelChain = new TableColumn<>("Hotel Chain");
        columnHotelChain.setCellValueFactory(new PropertyValueFactory<>("chain"));
        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelLocation = new TableColumn<>("Hotel Location");
        columnHotelLocation.setCellValueFactory(new PropertyValueFactory<>("city"));
        // flights from column setup
        TableColumn<HotelWrapper, Integer> columnHotelPrice = new TableColumn<>("Price");
        columnHotelPrice.setCellValueFactory(new PropertyValueFactory<>("rate"));


        tableViewHotels = new TableView<>();
        tableViewHotels.setItems(getHotels());
        tableViewHotels.getColumns().addAll(
                columnHotelName,
                columnHotelChain,
                columnHotelLocation,
                columnHotelPrice);

        HBox mainHotels = new HBox();
        Label dataHotels = new Label("HOTEL DATA WILL BE DISPLAYED HERE");
        mainHotels.getChildren().addAll(tableViewHotels);

        /*


                         Tours


        */
        VBox menuTours = new VBox();
        Label label3 = new Label("TOURS");
        menuTours.getChildren().addAll(label3);


        // TOURS TABLES

        // Tours from column setup
        TableColumn<Tour, String> columnTourName = new TableColumn<>("Tour Name");
        columnTourName.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourType = new TableColumn<>("Tour Type");
        columnTourType.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourDeparture = new TableColumn<>("Tour Departure");
        columnTourDeparture.setCellValueFactory(new PropertyValueFactory<>("departureLocation"));
        // Tours from column setup
        TableColumn<Tour, Integer> columnTourDuration = new TableColumn<>("Tour Duration");
        columnTourDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourPrice = new TableColumn<>("Tour Price");
        columnTourPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewTours = new TableView<>();
        tableViewTours.setItems(getTours());
        tableViewTours.getColumns().addAll(
                columnTourName,
                columnTourType,
                columnTourDeparture,
                columnTourDuration,
                columnTourPrice);

        HBox mainTours = new HBox();
        Label dataTours = new Label("TOUR DATA WILL BE DISPLAYED HERE");
        mainTours.getChildren().addAll(tableViewTours);

        /*


                         Cart


        */
        VBox menuCart = new VBox();
        Label label4 = new Label("CART");
        Slider slider = new Slider(0,1,0.5);
        menuCart.getChildren().addAll(label4);

        HBox mainCart = new HBox();
        Label dataCart = new Label("CART WILL BE DISPLAYED HERE");
        mainCart.getChildren().addAll(dataCart,slider);

        /*


                         Border pane


        */
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(menuFlights);
        borderPane.setCenter(mainFlights);


        // menu button actions
        buttonFlights.setOnAction(e -> {
            // borderPane.setLeft(menuFlights);
            borderPane.setCenter(mainFlights);
        });
        buttonHotels.setOnAction(e -> {
            // borderPane.setLeft(menuHotels);
            borderPane.setCenter(mainHotels);
        });
        buttonTours.setOnAction(e -> {
            // borderPane.setLeft(menuTours);
            borderPane.setCenter(mainTours);
        });
        buttonCart.setOnAction(e -> {
            // borderPane.setLeft(menuCart);
            borderPane.setCenter(mainCart);
        });

        searchButton.setOnAction(e -> {
            System.out.println("button");

            Flight flight = tableViewFlights.getSelectionModel().getSelectedItem();
            System.out.println("id is " + flight.getArrAirportId());
        });

        //setup

        flightsLocationFrom.setMaxWidth(Double.MAX_VALUE);
        flightsLocationTo.setMaxWidth(Double.MAX_VALUE);
        flightsPassengersNumber.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);

        primaryStage.setTitle("Trip Planner 3000");
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
