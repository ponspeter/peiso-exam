--liquibase formatted sql
--changeset Peter Pons:dbf7466f-6118-4ddd-993b-7a2272047a38
create table orders
(
    id          INT not null,
    order_id     INT null,
    status      TEXT  null,
    notes       TEXT  null,
    site_id    TEXT  null,
    register_id  TEXT  null,
    staff_id     TEXT  null,
    total       DECIMAL  null,
    total_tax    DECIMAL  null,
    paid        INT  null,
    tips         INT  null,
    deleted     BIT  null,
    created_at     DATETIME  null,
    updated_at     DATETIME  null,
    primary key (id)
);

create table products
(
    id          INT NOT NULL AUTO_INCREMENT,
    product_id  INT null,
    name        TEXT  null,
    description TEXT  null,
    code        TEXT  null,
    number      TEXT  null,
    is_modifier BIT  null,
    show_online BIT  null,
    friendly_name TEXT  null,
    always_prompt BIT  null,
    primary key (id)
);