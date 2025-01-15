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
    total_price DECIMAL(20, 2) NOT NULL,  -- The total price for the shares owned
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id) ON DELETE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);

