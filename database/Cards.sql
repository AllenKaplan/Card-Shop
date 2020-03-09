drop table if exists Cards;

create table Cards (
    number int not null,
    name varchar(20) not null,
    -- For card 101, Rank/Limit D-70, this field is D
    rank varchar(2) not null,
    -- For card 101, Rank/Limit D-70, this field is 70
    limit
        int not null,
        description varchar(200) not null,
        -- Specified Slot / Unspecified Slot / Spell
        class varchar(15) not null,
        -- 2 letter short form: ex (SR) => Short Range
        spellType varchar(10),
        -- Cards each have a number thats unique to that card
        primary key (number)
);

-- Specified Slot Cards
insert into
    Cards (
        number,
        name,
        rank,
        limit
, description, class, spellType
    )
values
    (
        000,
        'Rulers Blessing',
        'SS',
        1,
        'A castle given as a prize for winning the contest, town with population 10,000 included. Its residents will live according to whatever laws and commands you issue.',
        'Specified Slot',
        null
    ),
    (
        001,
        'Patch of Forest',
        'SS',
        3,
        'The entrance to the giant forest called the Mountain Gods Garden where many unique endemic species live. They are all tame and friendly. ',
        'Specified Slot',
        null
    );

-- Unspecified Slot Cards
insert into
    Cards (
        number,
        name,
        rank,
        limit
, description, class, spellType
    )
values
    (
        100,
        'Map of the Island empty',
        'G',
        400,
        'A map of the island. No towns or markers are currently shown. The user will automatically fill these in on their travels. ',
        'Unspecified Slot',
        null
    );

-- Spell Cards
insert into
    Cards (
        number,
        name,
        rank,
        limit
, description, class, spellType
    )
values
    (
        1001,
        'Peek',
        'G',
        200,
        'View contents of target players (previously met) free slots. ',
        'Spell',
        'LR,RS'
    );