-- Create user table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    picture VARCHAR(255),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    user_type VARCHAR(255) NOT NULL,
    verification_status BOOLEAN NOT NULL DEFAULT FALSE,
    portfolio_id BIGINT,
    watchlist_id BIGINT,
    wallet_id BIGINT,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id) ON DELETE CASCADE,
    FOREIGN KEY (watchlist_id) REFERENCES watchlist(id) ON DELETE CASCADE,
    FOREIGN KEY (wallet_id) REFERENCES wallet(id) ON DELETE CASCADE
);
-- Create Wallet
CREATE TABLE IF NOT EXISTS wallet(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id NOT NULL,
    wallet_balance DECIMAL(20, 2) NOT NULL DEFAULT 0.00,
);

-- Create influencer table
CREATE TABLE IF NOT EXISTS influencer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    stock_symbol VARCHAR(10) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    influencer_type VARCHAR(50) NOT NULL,
    influencer_tier VARCHAR(50) NOT NULL,
    ethnicity VARCHAR(100) NOT NULL,
    language VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

--Create Investor table
CREATE TABLE IF NOT EXISTS investor(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id NOT NULL UNIQUE
);

-- Create stock table
CREATE TABLE IF NOT EXISTS stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    influencer_id BIGINT NOT NULL,
    current_price DECIMAL(20, 2) NOT NULL,
    price_change_today DECIMAL(20, 2) NOT NULL,
    weeklyPriceChange DECIMAL(20, 2) NOT NULL,
    market_cap DECIMAL(20, 2) NOT NULL,
    last_updated TIMESTAMP NOT NULL,
    FOREIGN KEY (influencer_id) REFERENCES influencer(id) ON DELETE CASCADE
);

--Create Watchlist table
CREATE TABLE IF NOT EXISTS watchlist(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id NOT NULL
);

--Create Watchlist_stock table
CREATE TABLE IF NOT EXISTS watchlist_stock(
    watchlist_id NOT NULL,
    stock_id NOT NULL,
    FOREIGN KEY watchlist_id REFERENCES watchlist(id) ON DELETE CASCADE,
    FOREIGN KEY stock_id REFERENCES stock(id) ON DELETE CASCADE
);

-- Create portfolio table
CREATE TABLE IF NOT EXISTS portfolio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,  -- Foreign key referencing the user table
    stock_value DECIMAL(20, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- Create portfolio_stock table
CREATE TABLE IF NOT EXISTS portfolio_stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    portfolio_id BIGINT NOT NULL,
    stock_id BIGINT NOT NULL,
    shares DECIMAL(20, 2) NOT NULL,       -- The number of shares (decimal for precision)
    total_price DECIMAL(20, 2) NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id) ON DELETE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);

--CREATE Transaction table
CREATE TABLE IF NOT EXISTS transaction(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NULL,
    type VARCHAR(255)  NOT NULL,
    amount DECIMAL (20, 2) NOT NULL,
    description VARCHAR(255),
    date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

--Create Stock transaction table
CREATE TABLE IF NOT EXISTS stock_transaction(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    user_id NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    stock_id NOT NULL,
    shares  DECIMAL (20,2) NOT NULL,
    unit_price DECIMAL (20,2) NOT NULL,
    amount DECIMAL (20,2) NOT NULL,
    status VARCHAR (255),
    FOREIGN KEY user_id REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY stock_id REFERENCES stock(id) ON DELETE CASCADE
);
-- Insert Users
INSERT INTO user (email, password,
                  picture, first_name,
                  last_name, user_type,
                  verification_status)
VALUES
('john.doe@example.com', 'password123', NULL, 'John', 'Doe', 'INVESTOR', TRUE),
('jane.smith@example.com', 'password456', 'https://example.com/pic.jpg', 'Jane', 'Smith', 'INFLUENCER', TRUE);

-- Insert Wallets
INSERT INTO wallet (user_id, wallet_balance)
VALUES
(1, 1000.00),
(2, 2000.00);

-- Insert Influencers
INSERT INTO influencer (user_id, stock_symbol,
                        phone_number, country,
                        state, city, gender,
                        influencer_type, influencer_tier,
                        ethnicity, language)
VALUES
(2, 'AAPL', '123-456-7890', 'USA', 'California', 'Los Angeles', 'Female', 'Tech', 'Gold', 'Caucasian', 'English');

-- Insert Investors
INSERT INTO investor (user_id)
VALUES
(1);

-- Insert Stocks
INSERT INTO stock (influencer_id, current_price, price_change_today, weeklyPriceChange, market_cap, last_updated)
VALUES
(1, 150.25, 2.50, 5.00, 2500000000.00, CURRENT_TIMESTAMP);

-- Insert Watchlists
INSERT INTO watchlist (user_id)
VALUES
(1),
(2);

-- Insert Watchlist_Stocks
INSERT INTO watchlist_stock (watchlist_id, stock_id)
VALUES
(1, 1),
(2, 1);

-- Insert Portfolios
INSERT INTO portfolio (user_id, stock_value)
VALUES
(1, 500.00);

-- Insert Portfolio_Stocks
INSERT INTO portfolio_stock (portfolio_id, stock_id, shares, total_price)
VALUES
(1, 1, 10.00, 1500.00);

-- Insert Transactions
INSERT INTO transaction (user_id, type, amount, description, date, status)
VALUES
(1, 'DEPOSIT', 1000.00, 'Initial deposit', CURRENT_TIMESTAMP, 'COMPLETED'),
(2, 'WITHDRAWAL', 500.00, 'Partial withdrawal', CURRENT_TIMESTAMP, 'COMPLETED');

-- Insert Stock Transactions
INSERT INTO stock_transaction (date, user_id, transaction_type, stock_id, shares, unit_price, amount, status)
VALUES
(CURRENT_TIMESTAMP, 1, 'BUY', 1, 10.00, 150.25, 1502.50, 'COMPLETED');

