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
      houseNumber         VARCHAR(25),
      street              VARCHAR(25),
      city                VARCHAR(25),
      province            VARCHAR(25),
      country             VARCHAR(25),
      email               VARCHAR(25)
      PRIMARY KEY (employeeId)
);

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
      houseNumber         VARCHAR(25),
      street              VARCHAR(25),
      city                VARCHAR(25),
      province            VARCHAR(25),
      country             VARCHAR(25),
      email               VARCHAR(25),
      purchaseId          INT,
      PRIMARY KEY (customerId)
);

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

-- 4. Add table for locations
-- 4.1 Add field for location name (String)
-- 4.2 Add field for locationID (String; e.g. YYC, YVR, etc.)

DROP TABLE IF EXISTS LOCATIONS
CREATE TABLE LOCATIONS (
      locationId          INT,
      locationName        VARCHAR(25),
      PRIMARY KEY (locationId)
);

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
      purchaseId          INT,
      loungeAccess        BOOLEAN,
      creditCardNumber    VARCHAR(25),
      creditCardSecurityCode INT,
      totalPurchaseCost   INT,
      ticketInsurance     BOOLEAN,
      itemsPurchased      VARCHAR(25),
      ticketId            INT,
      PRIMARY KEY (purchaseId)
);

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
      ticketId            INT,
      seatNumber          INT,
      flightNumber        INT,
      flightDay           INT,
      flightMonth         INT,
      flightYear          INT,
      flightHour          INT,
      flightMinute        INT,
      PRIMARY KEY (ticketId)
);

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

DROP TABLE IF EXISTS FLIGHTSEATS
CREATE TABLE FLIGHTS (
      flightId            INT,
      aircraftId          INT,
      originId            INT,
      destinationId       INT,
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




