package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.mock.Flight;
import main.mock.FlightSearchMock;
import main.mock.HotelFinderMock;
import main.mock.TourSearchMock;
import main.service.FlightSearchService;
import main.service.HotelFinderService;
import main.service.TourSearchService;

import java.time.LocalDate;
import java.util.List;

public class Main extends Application {

    FlightSearchService flightSearch;
    HotelFinderService hotelSearch;
    TourSearchService tourSearch;
    TripPlanner tripPlanner;


    // TABLE MOCK OBJECTS
    public ObservableList<Flights> getFlights(){
        ObservableList<Flights> flights = FXCollections.observableArrayList();
        flights.add(new Flights("Casablanca","Isafjordur","Isafjordur","Isafjordur",1000));
        flights.add(new Flights("Egilsstadir","Keflavik","Isafjordur","Isafjordur",8000));

        return flights;
    }

    public ObservableList<Hotels> getHotels(){
        ObservableList<Hotels> hotels = FXCollections.observableArrayList();
        hotels.add(new Hotels("Eyjahótel", "Hilton Hotels", "Reykjavík", 50000));
        hotels.add(new Hotels("Peyja", "SK Hotels", "Egilsstadir", 20000));
        return hotels;
    }

    public ObservableList<Tours> getTours(){
        ObservableList<Tours> tours = FXCollections.observableArrayList();
        tours.add(new Tours("Rafting ehf.", "River rafting", "Reykjavík",3, 20000));
        tours.add(new Tours("Paint ehf.", "Paintball", "Egilsstadir", 2,15000));
        return tours;
    }

    // Create tables
    TableView<Flights> tableViewFlights;
    TableView<Hotels> tableViewHotels;
    TableView<Tours> tableViewTours;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Top menu
        HBox topMenu = new HBox();

        Image imageFlights = new Image(getClass().getResourceAsStream("iflights.png"));
        Image imageHotels = new Image(getClass().getResourceAsStream("ihotels.png"));
        Image imageTours = new Image(getClass().getResourceAsStream("itours.png"));
        Image imageCart = new Image(getClass().getResourceAsStream("icart.png"));
        Image imageSearch = new Image(getClass().getResourceAsStream("isearch.png"));

        Button buttonFlights = new Button("Flight search",new ImageView(imageFlights));
        Button buttonHotels = new Button("Hotel search",new ImageView(imageHotels));
        Button buttonTours = new Button("Tour search", new ImageView(imageTours));
        Button buttonCart = new Button("View cart", new ImageView(imageCart));
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

        Button searchButton = new Button("Search", new ImageView(imageSearch));

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
        TableColumn<Flights, String> columnFlightDepartureTime = new TableColumn<>("Departure");
        columnFlightDepartureTime.setCellValueFactory(new PropertyValueFactory<>("flightDepartureTime"));
        // flights from column setup
        TableColumn<Flights, String> columnFlightArrivalTime = new TableColumn<>("Arrival");
        columnFlightArrivalTime.setCellValueFactory(new PropertyValueFactory<>("flightArrivalTime"));
        // flights from column setup
        TableColumn<Flights, String> columnFlightAirportFrom = new TableColumn<>("Airport dep.");
        columnFlightAirportFrom.setCellValueFactory(new PropertyValueFactory<>("flightAirportFrom"));
        // flights from column setup
        TableColumn<Flights, String> columnFlightAirportTo = new TableColumn<>("Airport arr.");
        columnFlightAirportTo.setCellValueFactory(new PropertyValueFactory<>("flightAirportTo"));
        // flights from column setup
        TableColumn<Flights, Integer> columnFlightPrice = new TableColumn<>("Price");
        columnFlightPrice.setCellValueFactory(new PropertyValueFactory<>("flightPrice"));

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
        TableColumn<Hotels, String> columnHotelName = new TableColumn<>("Hotel Name");
        columnHotelName.setCellValueFactory(new PropertyValueFactory<>("HotelName"));
        // hotels from column setup
        TableColumn<Hotels, String> columnHotelChain = new TableColumn<>("Hotel Chain");
        columnHotelChain.setCellValueFactory(new PropertyValueFactory<>("HotelChain"));
        // hotels from column setup
        TableColumn<Hotels, String> columnHotelLocation = new TableColumn<>("Hotel Location");
        columnHotelLocation.setCellValueFactory(new PropertyValueFactory<>("HotelLocation"));
        // flights from column setup
        TableColumn<Hotels, Integer> columnHotelPrice = new TableColumn<>("Price");
        columnHotelPrice.setCellValueFactory(new PropertyValueFactory<>("HotelPrice"));


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
        TableColumn<Tours, String> columnTourName = new TableColumn<>("Tour Name");
        columnTourName.setCellValueFactory(new PropertyValueFactory<>("TourName"));
        // Tours from column setup
        TableColumn<Tours, String> columnTourType = new TableColumn<>("Tour Type");
        columnTourType.setCellValueFactory(new PropertyValueFactory<>("TourType"));
        // Tours from column setup
        TableColumn<Tours, String> columnTourDeparture = new TableColumn<>("Tour Departure");
        columnTourDeparture.setCellValueFactory(new PropertyValueFactory<>("TourDeparture"));
        // Tours from column setup
        TableColumn<Tours, Integer> columnTourDuration = new TableColumn<>("Tour Duration");
        columnTourDuration.setCellValueFactory(new PropertyValueFactory<>("TourDuration"));
        // Tours from column setup
        TableColumn<Tours, String> columnTourPrice = new TableColumn<>("Tour Price");
        columnTourPrice.setCellValueFactory(new PropertyValueFactory<>("TourPrice"));

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
        });
        /*


                         Tooltips


        */


        Tooltip.install(buttonCart, new Tooltip("View a list of items you have added to the cart"));
        Tooltip.install(buttonFlights, new Tooltip("Search for flights matching your criteria"));
        Tooltip.install(buttonHotels, new Tooltip("Search for hotels matching your criteria"));
        Tooltip.install(buttonTours, new Tooltip("Search for tours matching your criteria"));


        /*


                         Style


        */
        mainFlights.setId("main");
        mainHotels.setId("main");
        mainTours.setId("main");
        mainCart.setId("main");

        topMenu.setId("topMenu");

        buttonCart.setId("menuButton");
        buttonFlights.setId("menuButton");
        buttonHotels.setId("menuButton");
        buttonTours.setId("menuButton");

        menuFlights.setId("leftMenu");




        //setup

        flightsLocationFrom.setMaxWidth(Double.MAX_VALUE);
        flightsLocationTo.setMaxWidth(Double.MAX_VALUE);
        flightsPassengersNumber.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);

        primaryStage.setTitle("Trip Planner 3000");

        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("main/style.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
