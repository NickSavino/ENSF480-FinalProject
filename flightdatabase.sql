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


-- 3. Add table for aircrafts
-- 3.1 Field for aircraftId
-- 3.2 Field for aircraft model
-- 3.3 Field for amount of ordinary seats
-- 3.4 Field for amount of business seats
-- 3.5 Field for amount of comfort seats
-- 3.6 Field for total amount of seats

-- 4. Add table for locations

-- 5. Add table for registeredCustomers

-- 6. Add table for purchases

-- 7. Add table for flight crews

-- 8. Add table for flights
