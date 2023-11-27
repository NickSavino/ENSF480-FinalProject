-- ENSF 480 Final Project Database

DROP DATABASE IF EXISTS AIRLINEDATABASE;
CREATE DATABASE AIRLINEDATABASE;
USE AIRLINEDATABASE;

-- 1. Add table for employees
-- 1.1 Field for employeeId
-- 1.2 Field for password
-- 1.3 Field for employeeType ("Flight Attendant", "Admin", or "Airline")
-- 1.4 Field for firstName
-- 1.5 Field for lastName
-- 1.6 Field for houseNumber
-- 1.7 Field for street
-- 1.8 Field for city
-- 1.9 Field for province
-- 1.10 Field for country
-- 1.11 Field for email
-- 1.12 Field for flight crew ID

DROP TABLE IF EXISTS EMPLOYEES
CREATE TABLE EMPLOYEES (
      employeeId          INT,
      flightcrewID        INT DEFAULT NULL,
      password            VARCHAR(25),
      employeeType        VARCHAR(25),
      firstName           VARCHAR(25),
      lastName            VARCHAR(25),
      houseNumber         INT,
      street              VARCHAR(25),
      city                VARCHAR(25),
      province            VARCHAR(25),
      country             VARCHAR(25),
      email               VARCHAR(25)
      PRIMARY KEY (employeeId)
);

INSERT INTO EMPLOYEES (employeeId, flightcrewID, password, employeeType, firstName, lastName, houseNumber, street, city, province, country, email) VALUES
(101, NULL, 'password123', 'Admin', 'John', 'Doe', 123, 'Main St', 'Anytown', 'AB', 'Canada', 'john.doe@email.com'),
(102, 201, 'pw456', 'Flight Attendant', 'Jane', 'Smith', 456, 'Oak Ave', 'Anytown', 'BC', 'Canada', 'jane.smith@email.com'),
(103, 201, 'secure789', 'Airline', 'Alice', 'Johnson', 789, 'Pine Rd', 'Anytown', 'ON', 'Canada', 'alice.johnson@email.com');


-- 2. Add table for registered customers
-- 2.1 Field for customerId
-- 2.2 Field for status ("Unregistered", "Registered", "Airline Member")
-- 2.3 Field for username
-- 2.4 Field for password
-- 2.5 Field for credit card number 
-- 2.6 Field for credit card security code
-- 2.7 Field for isAirlineMember
-- 2.8 Field for Companion voucher usable (boolean)
-- 2.9 Field for firstName
-- 2.10 Field for lastName
-- 2.11 Field for houseNumber
-- 2.12 Field for street
-- 2.13 Field for city
-- 2.14 Field for province
-- 2.15 Field for country
-- 2.16 Field for email
-- 2.17 Add foreign key referencing PurchaseId

DROP TABLE IF EXISTS CUSTOMERS
CREATE TABLE CUSTOMERS (
      customerId          INT,
      status              VARCHAR(25),
      username            VARCHAR(25),
      password            VARCHAR(25),
      creditCardNumber    VARCHAR(25),
      creditCardSecurityCode INT,
      isAirlineMember     BOOLEAN,
      companionVoucherUsable BOOLEAN,
      firstName           VARCHAR(25),
      lastName            VARCHAR(25),
      houseNumber         INT,
      street              VARCHAR(25),
      city                VARCHAR(25),
      province            VARCHAR(25),
      country             VARCHAR(25),
      email               VARCHAR(25),
      purchaseId          VARCHAR(25),
      PRIMARY KEY (customerId)
);

INSERT INTO CUSTOMERS (customerId, status, username, password, creditCardNumber, creditCardSecurityCode, isAirlineMember, companionVoucherUsable, firstName, lastName, houseNumber, street, city, province, country, email, purchaseId) VALUES
(1001, 'Registered', 'user1', 'pass1', '1111222233334444', 123, FALSE, FALSE, 'Tom', 'Brown', 10, 'Maple St', 'CityA', 'ON', 'Canada', 'tom.brown@email.com', 'P001'),
(1002, 'Airline Member', 'user2', 'pass2', '4444333322221111', 456, TRUE, TRUE, 'Emily', 'White', 20, 'Elm St', 'CityB', 'AB', 'Canada', 'emily.white@email.com', 'P002'),
(1003, 'Unregistered', 'user3', 'pass3', '2222333344445555', 789, FALSE, FALSE, 'Kyle', 'Green', 30, 'Birch Ave', 'CityC', 'BC', 'Canada', 'kyle.green@email.com', 'P003');


-- 3. Add table for aircrafts
-- 3.1 Field for aircraftId
-- 3.2 Field for aircraft model
-- 3.3 Field for amount of ordinary seats
-- 3.4 Field for amount of business seats
-- 3.5 Field for amount of comfort seats
-- 3.6 Field for total amount of seats

DROP TABLE IF EXISTS AIRCRAFTS
CREATE TABLE AIRCRAFTS (
      aircraftId          INT,
      aircraftModel       VARCHAR(25),
      ordinarySeats       INT,
      businessSeats       INT,
      comfortSeats        INT,
      totalSeats          INT,
      PRIMARY KEY (aircraftId)
);

INSERT INTO AIRCRAFTS (aircraftId, aircraftModel, ordinarySeats, businessSeats, comfortSeats, totalSeats) VALUES
(1, 'Boeing 737', 100, 20, 10, 130),
(2, 'Airbus A320', 120, 30, 15, 165),
(3, 'Boeing 787', 200, 50, 25, 275);


-- 4. Add table for locations
-- 4.1 Add field for location name (String)
-- 4.2 Add field for locationID (String; e.g. YYC, YVR, etc.)

DROP TABLE IF EXISTS LOCATIONS
CREATE TABLE LOCATIONS (
      locationId          VARCHAR(25),
      locationName        VARCHAR(25),
      PRIMARY KEY (locationId)
);

INSERT INTO LOCATIONS (locationId, locationName) VALUES
('YYZ', 'Toronto Pearson International Airport'),
('YVR', 'Vancouver International Airport'),
('JFK', 'John F. Kennedy International Airport');


-- 5. Add table for purchases
-- 5.1 Add field for purchaseId (String)
-- 5.2 Add field for lounge access (boolean)
-- 5.3 Add field for credit card number (String)
-- 5.4 Add field for credit card security code (int)
-- 5.5 Add field for total purchase cost
-- 5.6 Add field for ticketInsurance (boolean)
-- 5.7 Add field for items purchased (String)
-- 5.8 Add foreign key reference for ticketId in tickets table

DROP TABLE IF EXISTS PURCHASES
CREATE TABLES (
      purchaseId          VARCHAR(25),
      loungeAccess        BOOLEAN,
      creditCardNumber    VARCHAR(25),
      creditCardSecurityCode INT,
      totalPurchaseCost   INT,
      ticketInsurance     BOOLEAN,
      itemsPurchased      VARCHAR(25),
      ticketId            INT,
      PRIMARY KEY (purchaseId)
);

INSERT INTO PURCHASES (purchaseId, loungeAccess, creditCardNumber, creditCardSecurityCode, totalPurchaseCost, ticketInsurance, itemsPurchased, ticketId) VALUES
('P001', TRUE, '1111222233334444', 123, 500, FALSE, 'Ticket, Meal', 'T001'),
('P002', FALSE, '4444333322221111', 456, 800, TRUE, 'Ticket, Extra Baggage', 'T002'),
('P003', TRUE, '2222333344445555', 789, 300, FALSE, 'Ticket', 'T003');


-- 6. Add table for tickets
-- 6.1 Add field for ticketId (String)
-- 6.2 Add field for seatNumber (int)
-- 6.3 Add field for flightNumber (int)
-- 6.4 Add field for flight day (int)
-- 6.5 Add field for flight month (int)
-- 6.6 Add field for flight year (int)
-- 6.7 Add field for flight hour (int)
-- 6.8 Add field for flight minute (int)

DROP TABLE IF EXISTS TICKETS
CREATE TABLE TICKETS (
      ticketId            VARCHAR(25),
      seatNumber          INT,
      flightNumber        INT,
      flightDay           INT,
      flightMonth         INT,
      flightYear          INT,
      flightHour          INT,
      flightMinute        INT,
      PRIMARY KEY (ticketId)
);

INSERT INTO TICKETS (ticketId, seatNumber, flightNumber, flightDay, flightMonth, flightYear, flightHour, flightMinute) VALUES
('T001', 12, 100, 15, 12, 2023, 10, 30),
('T002', 15, 101, 20, 12, 2023, 12, 45),
('T003', 18, 102, 25, 12, 2023, 14, 00);


-- 8. Add table for flights
-- 8.1 Add field for flightId (int)
-- 8.2 Add foreign key reference to aircraftId
-- 8.3 Add foreign key reference to locationId (for origin)
-- 8.4 Add foreign key reference to locationId (for destination)
-- 8.5 Add field for flight duration (int)
-- 8.6 Add field for flightCrewId (int)
-- 8.7 Add field for base flight cost (int)
-- 8.8 Add field for flight departure month
-- 8.9 Add field for flight departure day
-- 8.10 Add field for flight departure year
-- 8.11 Add field for flight departure hour
-- 8.12 Add field for flight departure minute

DROP TABLE IF EXISTS FLIGHTS
CREATE TABLE FLIGHTS (
      flightId            INT,
      aircraftId          INT,
      originId            VARCHAR(25),
      destinationId       VARCHAR(25),
      flightDuration      INT,
      flightCrewId        INT,
      baseFlightCost      INT,
      flightDepartureMonth INT,
      flightDepartureDay  INT,
      flightDepartureYear INT,
      flightDepartureHour INT,
      flightDepartureMinute INT,
      PRIMARY KEY (flightId)
);

INSERT INTO FLIGHTS (flightId, aircraftId, originId, destinationId, flightDuration, flightCrewId, baseFlightCost, flightDepartureMonth, flightDepartureDay, flightDepartureYear, flightDepartureHour, flightDepartureMinute) VALUES
(100, 1, 'YYZ', 'YVR', 240, 201, 300, 12, 15, 2023, 10, 30),
(101, 2, 'YVR', 'JFK', 300, 202, 400, 12, 20, 2023, 12, 45),
(102, 3, 'JFK', 'YYZ', 180, 203, 250, 12, 25, 2023, 14, 00);


-- 10. Add table for flights and seats (PK is flightId and seatId)
-- 10.1 Add field for flightId (int)
-- 10.2 Add field for seatId (int)
-- 10.3 Add field for seatIsBooked (boolean)
-- 10.4 Add field for seatType (String)
-- 10.5 Add field for passengerId

DROP TABLE IF EXISTS FLIGHTSEATS
CREATE TABLE FLIGHTSEATS (
      flightId            INT,
      seatId              INT,
      seatIsBooked        BOOLEAN,
      seatType            VARCHAR(25),
      passengerId         INT,
      PRIMARY KEY (flightId, seatId)
);

INSERT INTO FLIGHTSEATS (flightId, seatId, seatIsBooked, seatType, passengerId) VALUES
(100, 1, TRUE, 'Business', 1001),
(101, 2, FALSE, 'Ordinary', NULL),
(102, 3, TRUE, 'Comfort', 1002);

