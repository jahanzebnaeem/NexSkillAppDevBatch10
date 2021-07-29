CREATE TABLE Orders (
    OrderID int NOT NULL,
    OrderNumber int NOT NULL,
    CustomerID int,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(id)
)