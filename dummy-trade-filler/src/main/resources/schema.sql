CREATE TABLE IF NOT EXISTS orders
(id INT NOT NULL AUTO_INCREMENT,
stock_ticker VARCHAR(255) NOT NULL,
price DOUBLE NOT NULL,
volume INT NOT NULL,
buy_or_sell VARCHAR(255) NOT NULL,
status_code INT NOT NULL,
PRIMARY KEY(id));

insert into stock(stock_ticker, price, volume, buy_or_sell, status_code) values("AMZN", 10.50, 50, "BUY", 0);
insert into stock(stock_ticker, price, volume, buy_or_sell, status_code) values("AAPL", 50.70, 75, "BUY", 0);
insert into stock(stock_ticker, price, volume, buy_or_sell, status_code) values("NFLX", 25.00, 20, "SELL", 0);