# Legacy Trust Bank ATM

A Java Swing desktop ATM application that connects to the Legacy Trust Bank shared SQLite database.

![ATM UI](screenshots/atm%20ui.png)

## Features
- PIN-based login
- Check balance (Current & Savings)
- Deposit & Withdraw
- Transaction history recorded automatically

## Tech Stack
- Java + Swing (NetBeans)
- SQLite via `sqlite-jdbc`
- Shared DB at `C:\ProgramData\LegacyTrustBank\bankdata.db`

## Requirements
- Java 11+
- The [LegacyTrustBank](https://github.com/Uwami-Mgxekwa) app must have initialized the database with user accounts

## Run
Open in NetBeans and run `AtmLogin.java`, or build the JAR and launch it directly.
