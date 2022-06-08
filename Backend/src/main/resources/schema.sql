CREATE TABLE IF NOT EXISTS hardware
(
    hardware_id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    hardware_type VARCHAR(100) NOT NULL,
    stock_amount INT NOT NULL,
    PRIMARY KEY (hardware_id)
);

CREATE TABLE IF NOT EXISTS review
(
    review_id INT GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(100) NOT NULL,
    text VARCHAR(100) NOT NULL,
    rating_id VARCHAR(100) NOT NULL,
    hardware_id INT NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (hardware_id) REFERENCES hardware(hardware_id)
);

CREATE TABLE IF NOT EXISTS users_table (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
);

CREATE TABLE IF NOT EXISTS authority
(
    id identity,
    authority_name varchar(100) not null unique
);

CREATE TABLE IF NOT EXISTS user_authority
(
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references users_table(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);


