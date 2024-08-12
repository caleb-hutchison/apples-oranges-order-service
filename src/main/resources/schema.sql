CREATE TABLE IF NOT EXISTS orders (
id                           VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
discountForApplesApplied     BOOLEAN      NOT NULL,
discountForOrangesApplied    BOOLEAN      NOT NULL,
numberOfApples               INTEGER      NOT NULL,
numberOfOranges              INTEGER      NOT NULL,
priceForAllApples            FLOAT        NOT NULL,
priceForAllOranges           FLOAT        NOT NULL,
priceTotal                   FLOAT        NOT NULL
);