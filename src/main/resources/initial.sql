create table if not exists measure(
    id varchar(36) primary key,
    title varchar(255),
    main_symbol varchar(255),
    alternate_symbol varchar(255),
    definition varchar(255),
    dimension varchar(255)
);