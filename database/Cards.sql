CREATE TABLE Cards (
    number int not null,
    name varchar(200) not null,
    -- For card 101, Rank/Limit D-70, this field is D
    rank varchar(2) not null,
    -- For card 101, Rank/Limit D-70, this field is 70
    limit int not null,
    description varchar(500) not null,
    -- Specified Slot / Unspecified Slot / Spell
    class varchar(20) not null,
    -- 2 letter short form: ex (SR) => Short Range
    spellType varchar(20),
    -- Cards each have a number thats unique to that card
    primary key (number)
);

CREATE TABLE CardMarket(
    number int not null,
    --Current going price of the card
    sellingPrice REAL not null,
    --quanitity remaining in inventory
    stock INT not null,
    FOREIGN KEY (number) REFERENCES Cards(number),
    CONSTRAINT notNegative CHECK (sellingPrice>=0 AND stock>=0)
);