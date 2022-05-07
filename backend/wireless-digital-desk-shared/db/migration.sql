-- Creates the hashes table
create sequence HASH_ID_SEQ;
create table hashes (
    id int4 not null default nextval('HASH_ID_SEQ'),
    hash_user varchar(300) not null,
    unique_hash varchar(30) not null unique,
    constraint hashes_pk primary key (id)
);
CREATE UNIQUE INDEX hashes_id_idx ON public.hashes USING btree (id);

-- Creates the shortcuts table
create sequence SHORTCUT_ID_SEQ;
create table shortcuts (
    id int4 not null default nextval('SHORTCUT_ID_SEQ'),
    modifiers varchar(300),
    shortcut_order int4 not null,
    shortcut_key varchar(1) not null,
    shortcut_name varchar(300) not null,
    shortcut_user varchar(300) not null,
    application_name varchar(300),
    constraint shortcuts_pk primary key (id)
);
CREATE UNIQUE INDEX shortcuts_id_idx ON public.shortcuts USING btree (id);